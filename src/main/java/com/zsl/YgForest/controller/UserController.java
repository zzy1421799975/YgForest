package com.zsl.YgForest.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zsl.YgForest.configuration.shiro.ShiroUtils;
import com.zsl.YgForest.entity.FeedBack;
import com.zsl.YgForest.entity.Message;
import com.zsl.YgForest.entity.Notice;
import com.zsl.YgForest.entity.Pi;
import com.zsl.YgForest.entity.User;
import com.zsl.YgForest.service.FeedBackService;
import com.zsl.YgForest.service.MessageService;
import com.zsl.YgForest.service.NoticeService;
import com.zsl.YgForest.service.PiService;
import com.zsl.YgForest.service.UserService;

import priv.zsl.utils.jackson.JsonUtils;
import priv.zsl.utils.sdk.GoeasyUtils;
import priv.zsl.utils.sdk.PushUtils;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private FeedBackService feedBackService;
	@Autowired
	private PiService piService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private NoticeService noticeService;

	@Value("${local.upload-path}")
	private String local_path;

	@PostMapping(value = "/update/user")
	public Object submitLogin(User record) {
		User u = ShiroUtils.getPrincipal();
		record.setuId(u.getuId());
		record.setuTelephone(u.getuTelephone());
		userService.updateByPrimaryKeySelective(record, false);
		if (record.getuPassword() != null && record.getuPassword().length() > 0) {
			return "redirect:/logout";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping(value = "/update/user/image")
	@ResponseBody
	public Object uploadImage(HttpServletRequest request) {
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
			User record = ShiroUtils.getPrincipal();
			if (record == null || record.getuId() == null) {
				ShiroUtils.logout();
				return JsonUtils.error("请登录");
			}
			User record2 = new User();
			record2.setuId(record.getuId());
			record2.setuImagepath("https://www.xmhhs.top"+filePath);
			userService.updateByPrimaryKeySelective(record2, false);
			request.getSession().setAttribute("user", userService.selectOne(record2));
			return JsonUtils.ok(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.error(e.getMessage());
		}
	}

	@PostMapping(value = "/api/user/checklogin")
	public Object submitLogin(String uTelephone, String uPassword, Boolean rememberMe, HttpServletRequest request,
			Model m) {
		try {
			ShiroUtils.login(uTelephone, uPassword, rememberMe);
			User user = ShiroUtils.getPrincipal();
			request.getSession().setAttribute("user", user);
			userService.updateLoginTime(user.getuId());
			return "redirect:/";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			m.addAttribute("errmsg", e.getMessage());
			return "user/login";
		}
	}

	@PostMapping(value = "/api/user/sendMessage")
	@ResponseBody
	public Object sendMessage(String name, String subject, String content, Integer beemail) {
		User user = ShiroUtils.getPrincipal();
		if (name.equals("管理员")) {
			FeedBack record = new FeedBack();
			record.setfSubject(subject);
			record.setfContent(content);
			record.setuId(user.getuId());
			feedBackService.insert(record);
			return JsonUtils.ok("反馈成功。");
		} else if (name.equals("发布公告")) {
			Notice record = new Notice();
			record.setuId(user.getuId());
			record.setnBeemail(beemail);
			record.setnSubject(subject);
			record.setnContent(content);
			PushUtils.pushAll(subject, content);
			GoeasyUtils.publishAll(content);
			return JsonUtils.judge(noticeService.insert(record), "发送失败,请确认信息是否正确!");
		} else {
			User r = new User();
			r.setuEmail(name);
			r = userService.selectOne(r);
			if (r != null) {
				Message record = new Message();
				record.setmSender(user.getuId());
				record.setmReceiver(r.getuId());
				record.setmSubject(subject);
				record.setmContent(content);
				record.setmBeemail(beemail);
				return JsonUtils.judge(messageService.insert(record), "发送失败,请确认收件人是否正确!");
			} else {
				return JsonUtils.error("发送失败,请确认收件人是否正确!");
			}
		}
	}

	@PostMapping(value = "/api/user/pi/add")
	@ResponseBody
	public Object addPi(@RequestBody JSONObject jsonData) {
		Integer uId = ShiroUtils.getPrincipal().getuId();
		String pPassword = jsonData.getString("pPassword");
		Integer pId = jsonData.getInteger("pId");
		System.out.println(uId + "," + pPassword + "," + pId);
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
		default:
			return JsonUtils.error("设备序列号错误，请检查序列号是否正确。", pId);
		}

	}

	@PostMapping(value = "/api/user/pi/update")
	@ResponseBody
	public Object updatePi(@RequestBody Pi record) {
		Integer uId = ShiroUtils.getPrincipal().getuId();
		System.out.println(record.toString());
		return JsonUtils.judgeOE(piService.updateByPassword(record, uId), "请确认参数是否正确。", record);
	}
}
