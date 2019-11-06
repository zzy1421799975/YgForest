package com.zsl.YgForest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zsl.YgForest.entity.FeedBack;
import com.zsl.YgForest.service.FeedBackService;

import priv.zsl.utils.jackson.JsonUtils;

@RestController
@RequestMapping("/api/feedback")
public class ApiFeedBackController {

	@Autowired
	private FeedBackService feedBackService;

	@PostMapping("/insert")
	public Object insert(@RequestBody FeedBack record) {
		return JsonUtils.judgeOE(feedBackService.insert(record), "请确认数据是否有误，其中uId、fSubject、fContent不能为null。", record);
	}

	@PostMapping("/selectByUid")
	public Object selectByUid(@RequestBody  FeedBack record) {
		return JsonUtils.ok(feedBackService.selectByUid(record.getuId()));
	}
}
