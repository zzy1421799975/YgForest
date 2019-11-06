package com.zsl.YgForest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zsl.YgForest.configuration.shiro.ShiroUtils;
import com.zsl.YgForest.entity.User;
import com.zsl.YgForest.service.LogService;
import com.zsl.YgForest.service.PiService;
import com.zsl.YgForest.service.UserService;

import priv.zsl.utils.crypto.SimpleHashUtils;

@Controller
public class RememberController {
	@Autowired
	private LogService logService;
	@Autowired
	private PiService piService;
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String userindex(Model m) {
		User user = ShiroUtils.getPrincipal();
		m.addAttribute("user", user);
		m.addAttribute("loglist", logService.selectByUid(user.getuId()));
		m.addAttribute("pilist", piService.selectListByUid(user.getuId()));
		return "user/index";
	}

	@RequestMapping(value = { "/set" })
	public String userset(Model m) {
		User user = ShiroUtils.getPrincipal();
		m.addAttribute("user", user);
		m.addAttribute("loglist", logService.selectByUid(user.getuId()));
		m.addAttribute("pilist", piService.selectListByUid(user.getuId()));
		return "user/set";
	}

	@RequestMapping(value = { "/relogin" })
	public String lock(String uPassword, HttpServletRequest request) {
		User user = ShiroUtils.getPrincipal();
		if (uPassword == null) {
			request.setAttribute("err", "1");
			request.setAttribute("user", user);
			return "user/lock-screen";
		} else if (!user.getuPassword().equals(SimpleHashUtils.toMd5Salt(user.getuTelephone(), uPassword))) {
			request.setAttribute("err", "0");
			request.setAttribute("user", user);
			return "user/lock-screen";
		} else {
			request.getSession().setAttribute("user", user);
			userService.updateLoginTime(user.getuId());
			String url = String.valueOf(request.getSession().getAttribute("url"));
			if (url != null&&!url.equals("")) {
				request.getSession().removeAttribute("url");
				return "redirect:"+url;
			}
			return "redirect:/";
		}
	}
}
