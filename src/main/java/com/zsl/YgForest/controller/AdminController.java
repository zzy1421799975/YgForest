package com.zsl.YgForest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zsl.YgForest.configuration.shiro.ShiroUtils;
import com.zsl.YgForest.entity.FeedBack;
import com.zsl.YgForest.entity.Notice;
import com.zsl.YgForest.entity.User;
import com.zsl.YgForest.service.FeedBackService;
import com.zsl.YgForest.service.LogService;
import com.zsl.YgForest.service.NoticeService;
import com.zsl.YgForest.service.PiService;
import com.zsl.YgForest.service.UserService;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	private LogService logService;
	@Autowired
	private PiService piService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private FeedBackService feedBackService;
	@Autowired
	private UserService userService;

	// 发布公告
	@RequestMapping(value = { "/compose" })
	public String compose(Model m) {
		return "admin/compose";
	}

	// 所有反馈
	@RequestMapping(value = { "/feedback" })
	public String feedback(Model m, Integer pageNum) {
		if (pageNum == null) {
			pageNum = 1;
		}
		m.addAttribute("page", feedBackService.selectAll(pageNum, 20));
		return "admin/feedback";
	}
	@RequestMapping(value = { "/read" })
	public String read(Integer fId, Model m) {
		FeedBack record = feedBackService.selectByPrimaryKey(fId);
		m.addAttribute("sender", userService.selectByPrimaryKey(record.getuId()));
		m.addAttribute("feedback", record);
		return "admin/feedback-read";
	}
	// 所有发布的公告
	@RequestMapping(value = { "/noticelist" })
	public String noticelist(Model m, Integer pageNum) {
		if (pageNum == null) {
			pageNum = 1;
		}
		m.addAttribute("page", noticeService.selectAll(pageNum, 20));
		return "admin/noticelist";
	}
	@RequestMapping(value = { "/notice" })
	public String notice(Integer nId, Model m) {
		Notice record = noticeService.selectByNid(nId);
		m.addAttribute("sender", userService.selectByPrimaryKey(record.getuId()));
		m.addAttribute("notice", record);
		return "admin/notice-read";
	}
	// 所有设备
	@RequestMapping(value = { "/allpi" })
	public String allpi(Model m) {
		User user = ShiroUtils.getPrincipal();
		m.addAttribute("loglist", logService.selectByUid(user.getuId()));
		m.addAttribute("pilist", piService.selectAll());
		return "admin/pi-list";
	}

	// 所有用户
	@RequestMapping(value = { "/alluser" })
	public String alluser(Model m) {
		m.addAttribute("userlist", userService.selectAll());
		return "admin/user-table";
	}
}
