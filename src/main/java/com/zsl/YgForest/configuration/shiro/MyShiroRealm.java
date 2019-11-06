package com.zsl.YgForest.configuration.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.zsl.YgForest.entity.User;
import com.zsl.YgForest.service.UserService;

import priv.zsl.utils.sdk.RedisUtils;

public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 认证信息.(身份验证) : Authentication 是用来验证用户身份
	 * 
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		String username = token.getUsername();
		User record = new User();
		record.setuTelephone(username);

		record = userService.selectOne(record);
		if (null == record) {
			throw new AccountException("此帐号不存在，请确认后重新输入！");
		} else if (record.getuBelogin() == 0) {
			throw new DisabledAccountException("此帐号已经设置为禁止登录，请联系邮箱xm_hhs@163.com ！");
		} else {
			String password = record.getuPassword();
			ByteSource credentialsSalt = ByteSource.Util.bytes(username);// 使用账号作为盐值
			System.out.println("doGetAuthenticationInfo:\n" + record.toString());
			return new SimpleAuthenticationInfo(record, password, credentialsSalt, getName());
		}
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("权限验证");
		User user = (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = (SimpleAuthorizationInfo) RedisUtils
				.get("SimpleAuthorizationInfo:" + user.getuId());
		
		if (info == null) {
			info = new SimpleAuthorizationInfo();
			Set<String> roleSet = new HashSet<String>();
			if(user.getuBeadmin()==1) {
				roleSet.add("ADMIN");
			}else {
				roleSet.add("USER");
			}
			info.setRoles(roleSet);
			RedisUtils.set("SimpleAuthorizationInfo:" + user.getuId(), info);
		}
		return info;
	}

}
