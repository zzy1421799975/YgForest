package com.zsl.YgForest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zsl.YgForest.entity.Message;
import com.zsl.YgForest.entity.Relation;
import com.zsl.YgForest.entity.User;
import com.zsl.YgForest.entity.extend.Friend;
import com.zsl.YgForest.service.MessageService;
import com.zsl.YgForest.service.RelationService;
import com.zsl.YgForest.service.UserService;

import priv.zsl.utils.jackson.JsonUtils;
import priv.zsl.utils.sdk.GoeasyUtils;
import priv.zsl.utils.sdk.PushUtils;

@RestController
@RequestMapping("/api/message")
public class ApiMessageController {

	@Autowired
	private UserService userService;
	@Autowired
	private RelationService relationService;
	@Autowired
	private MessageService messageService;

	@PostMapping(value = "/selectFriend")
	public Object selectFriend(@RequestBody User record) {
		return JsonUtils.ok(userService.selectFriend(record.getuId()));
	}

	@PostMapping(value = "/selectList")
	public Object selectList(@RequestBody Message record) {
		return JsonUtils.ok(messageService.selectList(record));
	}

	@PostMapping(value = "/selectFriending")
	public Object selectFriending(@RequestBody User record) {
		return JsonUtils.ok(userService.selectFriending(record.getuId()));
	}

	@PostMapping(value = "/addFriend")
	public Object addFriend(@RequestBody Relation record) {
		return JsonUtils.judgeE(relationService.insert(record), "你们。", record);
	}

	@PostMapping(value = "/agreeFriend")
	public Object agreeFriend(@RequestBody Relation record) {
		return JsonUtils.judgeE(relationService.agree(record.getrSender(), record.getrReceiver()), "请确认信息是否正确。",
				record);
	}

	@PostMapping(value = "/deleteFriend")
	public Object deleteFriend(@RequestBody Relation record) {
		return JsonUtils.judgeE(relationService.notAgree(record.getrSender(), record.getrReceiver()), "请确认信息是否正确。",
				record);
	}

	@PostMapping(value = "/notAgreeFriend")
	public Object notAgreeFriend(@RequestBody Relation record) {
		return JsonUtils.judgeE(relationService.notAgree(record.getrSender(), record.getrReceiver()), "请确认信息是否正确。",
				record);
	}

	@PostMapping(value = "/sendMessage")
	public Object selectSendMessage(@RequestBody Message record) {
		if (record != null) {
			String mess = record.getmSender() + "-" + record.getmType() + "-";
			if (record.getmType() == 4) {
				mess += record.getmFilepath() + "-";
			}
			mess += record.getmContent();
			User Receiver=userService.selectByPrimaryKey(record.getmReceiver());
			System.out.println(Receiver.toString());
			PushUtils.pushOne(Receiver.getuCid(), mess);
			GoeasyUtils.publish(Receiver.getuCid(), mess);
		}
		boolean flag = messageService.insert(record);
		return JsonUtils.judgeO(flag, "发送失败", record);
	}

	@PostMapping(value = "/deleteMessage")
	public Object deleteMessage(@RequestBody Message record) {
		return JsonUtils.judge(messageService.delete(record.getmId()));
	}

	@PostMapping(value = "/beTrash")
	public Object beTrash(@RequestBody Message record) {
		return JsonUtils.judge(messageService.beTrash(record.getmId()));
	}

	@PostMapping(value = "/selectMessage")
	public Object selectMessage(@RequestBody Message record) {
		return JsonUtils.ok(messageService.selectMessage(record.getmSender(), record.getmReceiver(),
				record.getmBeread(), record.getmBetrash()));
	}

	@PostMapping(value = "/selectUserByKey")
	public Object selectUserByKey(@RequestBody JSONObject jsonData) {
		String key = jsonData.getString("key");
		return JsonUtils.ok(userService.selectByKey(key));
	}

	@PostMapping(value = "/selectExtend")
	public Object selectExtend(@RequestBody User user) {
		List<User> list = userService.selectFriend(user.getuId());
		List<Friend> list2 = new ArrayList<Friend>();
		for (int i = 0; i < list.size(); i++) {
			Friend r = new Friend();
			r.setFri(list.get(i));
			// 朋友发的未读信息的个数

			List<Message> mlist = messageService.selectMessage(list.get(i).getuId(), user.getuId(), 0, 0);
			r.setNoReadNum(mlist.size());
			if (mlist.size() > 0) {
				r.setLastMessage(mlist.get(mlist.size() - 1).getmContent());
			}
			list2.add(r);
		}
		return JsonUtils.ok(list2);
	}

	@PostMapping(value = "/selectMessageDesc")
	public Object selectMessageAll(@RequestBody JSONObject jsonData) {
		Integer myId = jsonData.getInteger("myId");
		Integer uId = jsonData.getInteger("uId");
		Integer pageNum = jsonData.getInteger("pageNum");
		Integer pageSize = jsonData.getInteger("pageSize");
		return JsonUtils.ok(messageService.selectListDesc(myId, uId, pageNum, pageSize));
	}

}
