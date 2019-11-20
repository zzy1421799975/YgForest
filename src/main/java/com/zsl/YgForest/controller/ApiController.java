package com.zsl.YgForest.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.zsl.YgForest.configuration.shiro.ShiroUtils;
import com.zsl.YgForest.entity.User;
import com.zsl.YgForest.service.NoticeService;
import com.zsl.YgForest.service.UserService;
import com.zsl.YgForest.service.VersionUpdateService;

import priv.zsl.utils.aliyun.RandomNum;
import priv.zsl.utils.aliyun.SendCode;
import priv.zsl.utils.jackson.JsonUtils;
import priv.zsl.utils.sdk.RedisUtils;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private NoticeService noticeService;
	@Autowired
	private VersionUpdateService versionUpdateService;

	@Value("${local.upload-path}")
	private String local_path;

	@PostMapping(value = "/checklogin")
	public Object submitLogin(@RequestBody JSONObject jsonData, HttpServletRequest request) {
		String uTelephone = jsonData.getString("uTelephone");
		String uPassword = jsonData.getString("uPassword");
		Boolean rememberMe = jsonData.getBoolean("rememberMe");
		System.out.println(uTelephone + "," + uPassword + "," + rememberMe);
		try {
			ShiroUtils.login(uTelephone, uPassword, rememberMe);
			User user = ShiroUtils.getPrincipal();
			userService.updateLoginTime(user.getuId());
			return JsonUtils.ok(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return JsonUtils.error(e.getMessage());
		}
	}

	@PostMapping(value = "/sendSmsCode")
	public Object sendCode(@RequestBody User user) throws ClientException {
		String uTelephone = user.getuTelephone();
		String rdnum = RandomNum.getRandom();
		//System.out.println(uTelephone + "," + rdnum);
		SendCode.sendSmsCode(uTelephone, rdnum);
		RedisUtils.set("smscode" + uTelephone, rdnum);		
		return JsonUtils.ok(rdnum);
	}

	@PostMapping(value = "/getSmsCode")
	public Object getCode(@RequestBody User user) {
		String uTelephone = user.getuTelephone();
		String code = (String) RedisUtils.get("smscode" + uTelephone);
		return code == null ? JsonUtils.error("验证码为空，请先发送验证码。") : JsonUtils.ok(code);
	}

	@PostMapping(value = "/userReg")
	public Object userReg(@RequestBody JSONObject jsonData) {
		String uTelephone = jsonData.getString("uTelephone");
		String uPassword = jsonData.getString("uPassword");
		String code = jsonData.getString("code");
		
		String getCode = (String) RedisUtils.get("smscode" + uTelephone);
		if (getCode == null || !getCode.equals(code)) {
			return JsonUtils.error("验证码错误!");
		} else if (userService.insertReg(uTelephone, uPassword)) {
			RedisUtils.delete("smscode" + uTelephone);
			return JsonUtils.ok("注册成功!");
		} else {
			return JsonUtils.error("电话号码已被注册，如忘记密码请找回!");
		}
	}

	@PostMapping(value = "/uploadImage")
	public Object uploadImage(@RequestBody User user, HttpServletRequest request) {
		Integer uId = user.getuId();
		if (uId == null) {
			return JsonUtils.error("请登录");
		}
		SimpleDateFormat adf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat adf2 = new SimpleDateFormat("yyyyMM");
		String path = local_path;
		String filePath = "/headimage/" + adf2.format(new Date()) + "/";
		File f = new File(path + filePath);
		if (!f.exists()) {
			f.mkdirs();
		}
		filePath += adf.format(new Date()) + ".jpg";
		Part part;
		try {  
			part = request.getPart("file");
			part.write(path + filePath);

			User record = new User();
			record.setuId(uId);
			record.setuImagepath("https://www.xmhhs.top" + filePath);
			userService.updateByPrimaryKeySelective(record, true);
			return JsonUtils.ok(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.error(e.getMessage());
		}
	}

	@PostMapping(value = "/notice/selectAll")
	public Object selectAll() {
		return JsonUtils.ok(noticeService.selectAll());
	}

	@PostMapping(value = "/getVersion")
	public Object getVersion() {
		return JsonUtils.ok(versionUpdateService.selectNew());
	}

}
