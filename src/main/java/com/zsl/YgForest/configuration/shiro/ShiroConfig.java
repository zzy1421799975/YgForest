package com.zsl.YgForest.configuration.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {
	
	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 *
	 * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 3、部分过滤器可指定参数，如perms，roles
	 *
	 */
	// @Autowired
	// SysPermissionInitService sysPermissionInitService;

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/sign_in");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/500");
		// 权限控制map.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 从数据库获取
		/*
		 * List<SysPermissionInit> list = sysPermissionInitService.selectAll(); for
		 * (SysPermissionInit sysPermissionInit : list) {
		 * filterChainDefinitionMap.put(sysPermissionInit.getUrl(),
		 * sysPermissionInit.getPermissionInit()); }
		 */
		// 登出配置
		filterChainDefinitionMap.put("/logout", "logout");
		// 公共资源
		filterChainDefinitionMap.put("/api/**", "anon");
		filterChainDefinitionMap.put("/error/**", "anon");
		filterChainDefinitionMap.put("/register", "anon");
		filterChainDefinitionMap.put("/myfile/**", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/assets/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/email-templates/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/headimage/**", "anon");
		filterChainDefinitionMap.put("/piimage/**", "anon");
		// 用户过滤器
		filterChainDefinitionMap.put("/relogin", "SetUser");
		filterChainDefinitionMap.put("/show/**", "SetUser");
		filterChainDefinitionMap.put("/compose", "SetUser");
		filterChainDefinitionMap.put("/about", "SetUser");
		// 超级管理员过滤器
		filterChainDefinitionMap.put("/admin/**", "roles[ADMIN],SetUser");
		
		filterChainDefinitionMap.put("/**", "SetUser");
	
		Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("SetUser", setUserFilter());

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		shiroFilterFactoryBean.setFilters(filterMap);
        //map里面key值要为authc才能使用自定义的过滤器
		return shiroFilterFactoryBean;
	}
	//自定义过滤器
    @Bean
    public UserFilter setUserFilter() {
        return new UserFilter();
    }
	/**
	 * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
	 * 
	 * @return
	 */
	@Bean
	public MyShiroRealm myShiroRealm(HashedCredentialsMatcher matcher) {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(matcher);
		return myShiroRealm;
	}

	@Bean
	public SecurityManager securityManager(HashedCredentialsMatcher matcher) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(myShiroRealm(matcher));
		// 自定义缓存实现 使用redis
		// securityManager.setCacheManager(cacheManager());
		// 自定义session管理 使用redis
		// securityManager.setSessionManager(SessionManager());
		// 注入记住我管理器;
		securityManager.setRememberMeManager(rememberMeManager());

		return securityManager;
	}

	@Bean
	public SimpleCookie rememberMeCookie() {
		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		// <!-- 记住我cookie生效时间30天 ,单位秒;-->
		simpleCookie.setMaxAge(7 * 24 * 60 * 60);
		simpleCookie.setHttpOnly(true);
		return simpleCookie;
	}

	/**
	 * cookie管理对象;
	 * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
	 * 
	 * @return rememberMeManager
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		// rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
		cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
		return cookieRememberMeManager;
	}

	/**
	 * thymeleaf使用shiro标签
	 * 
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	/**
	 * 密码匹配凭证管理器
	 *
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		// 采用MD5方式加密
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		// 设置加密次数
		hashedCredentialsMatcher.setHashIterations(1024);
		return hashedCredentialsMatcher;
	}
}