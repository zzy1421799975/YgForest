package com.zsl.YgForest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zsl.YgForest.entity.Data;
import com.zsl.YgForest.entity.Pi;
import com.zsl.YgForest.entity.User;
import com.zsl.YgForest.entity.extend.Signal;
import com.zsl.YgForest.service.DataService;
import com.zsl.YgForest.service.PiService;
import com.zsl.YgForest.service.UserPiRelationService;

import priv.zsl.utils.jackson.JsonUtils;
import priv.zsl.utils.sdk.PushUtils;
import priv.zsl.utils.sdk.RedisUtils;

@RestController
@RequestMapping("/api/data")
public class ApiDataController {

	@Autowired
	private DataService dataService;
	@Autowired
	private UserPiRelationService userPiRelationService;

	@Autowired
	private PiService piService;

	@PostMapping(value = "/insert")
	public Object insert(@RequestBody Data record) {
		// System.out.println("/api/data/insert:\n" + record.toString());
		Integer pid = record.getpId();
		if (record.getdInfrared() == 1 && RedisUtils.get(pid + ":Infrared") == null) {
			RedisUtils.set(pid + ":Infrared", "请注意!检测到活动生物!", 120);
			List<User> list = userPiRelationService.selectUserByPid(pid);
			System.out.println(list.toString());
			for (int i = 0; i < list.size(); i++) {
				PushUtils.pushOne(list.get(i).getuCid(), "请注意!检测到活动生物!");
			}
		}
		if (record.getdFlame() == 1 && RedisUtils.get(pid + ":Flame") == null) {
			RedisUtils.set(pid + ":Flame", "请注意!检测到明火!请确认是否有火情发生!", 120);
			List<User> list = userPiRelationService.selectUserByPid(pid);
			for (int i = 0; i < list.size(); i++) {
				PushUtils.pushOne(list.get(i).getuCid(), "请注意!检测到明火!请确认是否有火情发生!");
			}
		}
		if (record.getdSmoke() == 1 && RedisUtils.get(pid + ":Smoke") == null) {
			RedisUtils.set(pid + ":Smoke", "请注意!检测到烟雾或有毒气体!请确认是否有火情发生!", 120);
			List<User> list = userPiRelationService.selectUserByPid(pid);
			for (int i = 0; i < list.size(); i++) {
				PushUtils.pushOne(list.get(i).getuCid(), "请注意!检测到烟雾或有毒气体!请确认是否有火情发生!");
			}
		}
		return JsonUtils.ok(dataService.insert(record));
	}

	@PostMapping(value = "/selectSignal")
	public Object insert(@RequestBody User user) {
		List<Pi> pi = piService.selectListByUid(user.getuId());
		List<Signal> list = new ArrayList<Signal>();
		for (int i = 0; i < pi.size(); i++) {
			Pi p = pi.get(i);
			Signal sig = new Signal();
			sig.setRecord(p);
			sig.setFlame(RedisUtils.get(p.getpId() + ":Flame"));
			sig.setSmoke(RedisUtils.get(p.getpId() + ":Smoke"));
			sig.setInfrared(RedisUtils.get(p.getpId() + ":Infrared"));
			if (!sig.judge()) {
				list.add(sig);
			}
		}
		return JsonUtils.ok(list);
	}

	@PostMapping(value = "/selectPageListByPid")
	public Object selectByPid(@RequestBody JSONObject jsonData) {
		Integer pageNum = jsonData.getInteger("pageNum");
		Integer pageSize = jsonData.getInteger("pageSize");
		Integer pId = jsonData.getInteger("pId");
		return JsonUtils.ok(dataService.selectByPid(pId, pageNum, pageSize));
	}

	@PostMapping(value = "/selectListDesc")
	public Object selectByPidDesc(@RequestBody JSONObject jsonData) {
		Integer pId = jsonData.getInteger("pId");
		Integer limitNum = jsonData.getInteger("limitNum");
		return JsonUtils.ok(dataService.selectByPidDesc(pId, limitNum));
	}
}
