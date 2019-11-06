package com.zsl.YgForest.configuration.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.zsl.YgForest.entity.User;

import priv.zsl.utils.sdk.RedisUtils;

public class ShiroUtils {

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static User getPrincipal() {
		return (User) getSubject().getPrincipal();
	}

	public static void relogin() {
		User record = getPrincipal();
		UsernamePasswordToken token = (UsernamePasswordToken) RedisUtils.get("token" + record.getuTelephone());
		getSubject().login(token);
	}

	public static void login(String uTelephone, String uPassword, Boolean rememberMe) {
		UsernamePasswordToken token = new UsernamePasswordToken(uTelephone, uPassword, rememberMe);
		RedisUtils.set("token" + uTelephone, token);
		getSubject().login(token);
	}

	public static void logout() {
		getSubject().logout();
	}
}
