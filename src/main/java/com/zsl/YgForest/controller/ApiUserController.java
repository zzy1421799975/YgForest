package com.zsl.YgForest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zsl.YgForest.entity.User;
import com.zsl.YgForest.service.UserService;

import priv.zsl.utils.crypto.SimpleHashUtils;
import priv.zsl.utils.jackson.JsonUtils;
import priv.zsl.utils.sdk.RedisUtils;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
	@Autowired
	private UserService userService;

	@PostMapping(value = "/select")
	public Object select(@RequestBody User user) {

		User record = new User();
		record.setuTelephone(user.getuTelephone());
		record.setuPassword(SimpleHashUtils.toMd5Salt(user.getuTelephone(), user.getuPassword()));

		record = userService.selectOne(record);
		return record == null ? JsonUtils.error("帐号或密码错误。") : JsonUtils.ok(record);
	}

	@PostMapping(value = "/selectById")
	public Object selectById(@RequestBody User user) {
		return JsonUtils.ok(userService.selectByPrimaryKey(user.getuId()));
	}

	@PostMapping(value = "/update")
	public Object submitLogin(@RequestBody User record) {
		if (userService.updateByPrimaryKeySelective(record, true)) {
			return JsonUtils.ok(record);
		} else {
			return JsonUtils.error("更新错误，请在data中检查后台接收到的数据是否有误。", record);
		}
	}

	@PostMapping(value = "/qqbind")
	public Object bind(@RequestBody User record) {
		User user = userService.selectByTelephone(record.getuTelephone());

		String rpassword = SimpleHashUtils.toMd5Salt(record.getuTelephone(), record.getuPassword());

		if (user == null) {// 找不到
			return JsonUtils.error("该手机号未注册，请确认是否正确。", record);

		} else if (!rpassword.equals(user.getuPassword())) {// 密码不正确
			return JsonUtils.error("密码验证错误，请重新输入密码。", record);

		} else {
			record.setuId(user.getuId());
			user.setuUsername(record.getuUsername());
			if (userService.updateByPrimaryKeySelective(record, true)) {
				return JsonUtils.ok(user);
			} else {
				return JsonUtils.error("绑定错误，请重试。");
			}
		}
	}

	@PostMapping(value = "/recover")
	public Object recover(@RequestBody JSONObject jsonData) {
		String uTelephone = jsonData.getString("uTelephone");
		String uPassword = jsonData.getString("uPassword");
		String code = jsonData.getString("code");
		String getCode = (String) RedisUtils.get("smscode" + uTelephone);
		if (getCode == null || !getCode.equals(code)) {
			return JsonUtils.error("验证码错误!");
		} else if (uPassword == null || uPassword.length() < 3) {
			return JsonUtils.error("密码为空或密码长度太短!");
		} else {
			User user = userService.selectByTelephone(uTelephone);
			User record = new User();
			record.setuId(user.getuId());
			record.setuTelephone(uTelephone);
			record.setuPassword(uPassword);
			return JsonUtils.judgeO(userService.updateByPrimaryKeySelective(record, true),"未知更新错误，请重试。",user);
		}
	}

	@PostMapping(value = "/qqreg")
	public Object qqreg(@RequestBody JSONObject jsonData) {
		String uUsername = jsonData.getString("uUsername");
		String uTelephone = jsonData.getString("uTelephone");
		String uPassword = jsonData.getString("uPassword");
		String code = jsonData.getString("code");
		String getCode = (String) RedisUtils.get("smscode" + uTelephone);
		User user = userService.insert(uTelephone, uPassword, uUsername);
		if (getCode == null || !getCode.equals(code)) {
			return JsonUtils.error("验证码错误!");
		} else if (user != null) {
			RedisUtils.delete("smscode" + uTelephone);
			return JsonUtils.ok(user);
		} else {
			return JsonUtils.error("电话号码已被注册，如忘记密码请找回!");
		}
	}

	@PostMapping(value = "/qqlogin")
	public Object qqlogin(@RequestBody User record) {
		User user = userService.selectOne(record);
		if (user != null) {
			return JsonUtils.ok(user);
		} else {
			return JsonUtils.error("该QQ尚未绑定账号，请先注册或绑定。");
		}
	}

}
