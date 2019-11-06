package com.zsl.YgForest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zsl.YgForest.configuration.shiro.ShiroUtils;
import com.zsl.YgForest.entity.Message;
import com.zsl.YgForest.entity.Notice;
import com.zsl.YgForest.entity.Pi;
import com.zsl.YgForest.entity.User;
import com.zsl.YgForest.service.DataService;
import com.zsl.YgForest.service.ImageService;
import com.zsl.YgForest.service.MessageService;
import com.zsl.YgForest.service.NoticeService;
import com.zsl.YgForest.service.PiService;
import com.zsl.YgForest.service.UserService;

@Controller
@RequestMapping("/show")
public class ShowController {

	@Autowired
	private PiService piService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private DataService dataService;
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	

	@RequestMapping(value = { "/pilist" })
	public String piList(Model m) {
		User user = ShiroUtils.getPrincipal();
		m.addAttribute("user", user);
		m.addAttribute("pilist", piService.selectListByUid(user.getuId()));
		return "user/pi-list";
	}

	@RequestMapping(value = { "/noticelist" })
	public String noticelist(Model m) {
		m.addAttribute("noticelist", noticeService.selectAll());
		return "user/mail-noticelist";
	}

	@RequestMapping(value = { "/notice" })
	public String notice(Integer nId, Model m) {
		Notice record = noticeService.selectByNid(nId);
		m.addAttribute("sender", userService.selectByPrimaryKey(record.getuId()));
		m.addAttribute("notice", record);
		return "user/mail-notice-read";
	}

	@RequestMapping(value = { "/messagelist" })
	public String messagelist(Model m, Integer pageNum) {
		User user = ShiroUtils.getPrincipal();
		if (pageNum == null) {
			pageNum = 1;
		}
		m.addAttribute("messagelist", messageService.selectMessage(null, user.getuId(), null, 0, pageNum, 20));
		return "user/mail-inbox";
	}

	@RequestMapping(value = { "/read" })
	public String read(Model m, Integer mId) {
		Message message = messageService.selectByPrimaryKey(mId);
		message.setmBeread(1);
		messageService.update(message);
		m.addAttribute("sender", userService.selectByPrimaryKey(message.getmSender()));
		m.addAttribute("message", message);
		return "user/mail-message-read";
	}

	@RequestMapping(value = { "/message" })
	public String message(Model m, Integer mId) {
		Message message = messageService.selectByPrimaryKey(mId);
		m.addAttribute("sender", userService.selectByPrimaryKey(message.getmSender()));
		m.addAttribute("message", message);
		return "user/mail-message-read";
	}

	@RequestMapping(value = { "/mysend" })
	public String mysend(Model m, Integer pageNum) {
		User user = ShiroUtils.getPrincipal();
		if (pageNum == null) {
			pageNum = 1;
		}
		m.addAttribute("messagelist", messageService.selectMessage(user.getuId(), null, 0, null, pageNum, 20));
		return "user/mail-mysend";
	}

	@RequestMapping(value = { "/trash" })
	public String trash(Model m, Integer pageNum) {
		User user = ShiroUtils.getPrincipal();
		if (pageNum == null) {
			pageNum = 1;
		}
		m.addAttribute("messagelist", messageService.selectMessage(null, user.getuId(), null, 1, pageNum, 20));
		return "user/mail-trash";
	}

	@RequestMapping(value = { "/image" })
	public String showImage(Integer pId, Integer pageNum, Model m) {
		User user = ShiroUtils.getPrincipal();
		List<Pi> pilist = piService.selectListByUid(user.getuId());
		if (pId == null) {
			pId = pilist.get(0).getpId();
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		m.addAttribute("pId", pId);
		m.addAttribute("pilist", pilist);
		m.addAttribute("pagelist", imageService.selectListByPid(pId, pageNum, 10));
		return "user/pi-image";
	}

	@RequestMapping(value = { "/datalist" })
	public String showData(Integer pId, Integer pageNum, Model m) {
		User user = ShiroUtils.getPrincipal();
		List<Pi> pilist = piService.selectListByUid(user.getuId());
		if (pId == null) {
			pId = pilist.get(0).getpId();
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		m.addAttribute("pId", pId);
		m.addAttribute("pilist", pilist);
		m.addAttribute("pagelist", dataService.selectByPid(pId, pageNum, 50));
		return "user/data-table";
	}

	@RequestMapping(value = { "/datachart" })
	public String showChart(Integer pId, Model m) {
		User user = ShiroUtils.getPrincipal();
		List<Pi> pilist = piService.selectListByUid(user.getuId());
		if (pId == null) {
			pId = pilist.get(0).getpId();
		}
		m.addAttribute("pId", pId);
		m.addAttribute("pilist", pilist);

		return "user/data-chart";
	}

	@RequestMapping(value = { "/pichart" })
	public String pichart(Integer pId, Model m) {
		User user = ShiroUtils.getPrincipal();
		List<Pi> pilist = piService.selectListByUid(user.getuId());
		if (pId == null) {
			pId = pilist.get(0).getpId();
		}
		m.addAttribute("pId", pId);
		m.addAttribute("pilist", pilist);
		return "user/pi-chart";
	}

	@RequestMapping(value = { "/newfriend" })
	public String newfriend(Model m, String key) {
		User user = ShiroUtils.getPrincipal();
		m.addAttribute("newfriend", userService.selectFriending(user.getuId()));
		if (key != null) {
			m.addAttribute("searchfriend", userService.selectByKey(key));
		} else {
			m.addAttribute("searchfriend", null);
		}

		return "user/friend-new";
	}

	@RequestMapping(value = { "/friend" })
	public String friend(Model m) {
		User user = ShiroUtils.getPrincipal();
		m.addAttribute("friend", userService.selectFriend(user.getuId()));
		return "user/friend-list";
	}

	@RequestMapping(value = { "/compose" })
	public String compose(Model m, String recv) {
		m.addAttribute("recv", recv);
		return "user/mail-compose";
	}
}
