package com.zsl.YgForest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zsl.YgForest.entity.User;
import com.zsl.YgForest.service.UserService;

import priv.zsl.utils.httpclient.HttpClientUtils;
import priv.zsl.utils.jackson.JsonUtils;

@RestController
@RequestMapping(value = "/api/weixin")
public class ApiWeixinController {
	@Autowired
	private UserService userService;

	@PostMapping("/jscode2session")
	public Object jscode2session(@RequestBody JSONObject jsondata) {
		String code = jsondata.getString("code");
		System.out.println(code);
		String url = "yoursweixinapikey";
		System.out.println(url);
		JSONObject json=HttpClientUtils.httpGet(url);
		if(json.containsKey("openid")) {
			return JsonUtils.ok(json);
		}else {
			return JsonUtils.error("请检查信息", json);
		}
	}
	
	@PostMapping("/getUserInfo")
	public Object getUserInfo(@RequestBody User record) {
		System.out.println(record.toString());
		record=userService.selectOne(record);
		if(record==null) {
			return JsonUtils.error("未绑定");
		}
		System.out.println(record.toString());

		return JsonUtils.ok(record);
	}
	
	@PostMapping("/getUserInfoByPhone")
	public Object getUserInfoByPhone(@RequestBody User record) {
		if(record.getuTelephone()==null) {
			return JsonUtils.error();
		}
		record=userService.selectOne(record);
		if(record==null) {
			return JsonUtils.error();
		}
		return JsonUtils.ok(record);
	}
}
