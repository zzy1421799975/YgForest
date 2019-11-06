package com.zsl.YgForest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zsl.YgForest.configuration.shiro.ShiroUtils;
import com.zsl.YgForest.entity.Pi;
import com.zsl.YgForest.entity.User;
import com.zsl.YgForest.service.PiService;

import priv.zsl.utils.jackson.JsonUtils;

@RestController
@RequestMapping("/api/pi")
public class ApiPiController {
	@Autowired
	private PiService piService;

	@PostMapping(value = "/add")
	public Object addPi(@RequestBody JSONObject jsonData) {
		Integer uId = jsonData.getInteger("uId");
		String pPassword = jsonData.getString("pPassword");
		Integer pId = jsonData.getInteger("pId");
		Integer state = piService.addPi(uId, pId, pPassword);
		switch (state) {
		case 1:
			return JsonUtils.ok(piService.selectByPid(pId));
		case -1:
			return JsonUtils.error("信息为空，请确认传参是否正确。", pId);
		case -2:
			return JsonUtils.error("密钥不正确。", pId);
		case -3:
			return JsonUtils.error("请勿重复绑定。", pId);
		case -4:
			return JsonUtils.error("设备序列号错误，请检查序列号是否正确。", pId);
		default:
			return JsonUtils.error("未知错误。", pId);
		}
	}

	@PostMapping(value = "/select")
	public Object selectPi(@RequestBody User user) {
		List<Pi> record = piService.selectListByUid(user.getuId());
		return JsonUtils.ok(record);
	}
	
	
	@PostMapping(value = "/update")
	public Object updatePi(@RequestBody JSONObject jsonData) {
		Pi record = jsonData.getObject("record", Pi.class);
		Integer uId = jsonData.getInteger("uId");
		return JsonUtils.judgeOE(piService.update(record, uId), "请确认参数是否正确。", record);
	}
	
	
	@PostMapping(value = "/delete")
	public Object deletePi(@RequestBody JSONObject jsonData) {
		Integer pId = jsonData.getInteger("pId");
		Integer uId = jsonData.getInteger("uId");
		return JsonUtils.judgeOE(piService.remove(uId, pId), "删除失败，请确认要删除的设备是否存在。",
				piService.selectByPid(pId));
	}

	@PostMapping(value = "/changeSwitchState")
	public Object changeSwitchState(@RequestBody Pi record) {
		Integer pId = record.getpId();
		Integer pSwitchstate = record.getpSwitchstate();
		if (pSwitchstate != 1 && pSwitchstate != 0) {
			return JsonUtils.error("请确认状态码是否正确。", pSwitchstate);
		}
		return JsonUtils.judgeOE(piService.changeSwitchState(pId, pSwitchstate), "请确认数据是否正确。", record);
	}

	@PostMapping(value = "/selectByPid")
	public Object selectByPid(@RequestBody Pi record) {
		return JsonUtils.ok(piService.selectByPid(record.getpId()));
	}

	@PostMapping(value = "/alive")
	public Object alive(@RequestBody Pi record) {
		record.setpBootstate(1);// 设为开机
		record.setpLivetime(new Date());// 存活时间
		return JsonUtils.judgeO(piService.upAlive(record), "请确认pId是否正确。", piService.selectByPid(record.getpId()));
	}

	@PostMapping(value = "/JSremove")
	public Object remove(@RequestBody Pi record) {
		Integer uId = ShiroUtils.getPrincipal().getuId();
		return JsonUtils.judgeO(piService.remove(uId, record.getpId()), "删除失败，请确认要删除的设备是否存在。",
				piService.selectByPid(record.getpId()));
	}
}
