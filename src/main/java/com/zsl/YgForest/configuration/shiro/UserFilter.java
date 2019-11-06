package com.zsl.YgForest.configuration.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.web.filter.AccessControlFilter;
import com.zsl.YgForest.entity.User;

public class UserFilter extends AccessControlFilter {
	public String url;

	// 判断是否拦截，资源等不拦截
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// TODO Auto-generated method stub
		String url = getPathWithinApplication(request);
		if (url.contains("/fonts") || url.contains("/sign_in") || url.contains("/logout") || url.contains("/relogin")
				|| url.contains("/js/") || url.contains("/assets") || url.contains("/img/") || url.contains("/css/")
				|| url.contains("/myfile") || url.contains("/piimage") || url.contains("/headimage/")
				|| url.contains("/api/") || url.contains("/register") || url.contains("/recoverpw")
				|| url.contains("/goeasy") || url.contains("http://") || url.contains("rtmp://")) {
			return true;
		}
		this.url = url;
		System.out.println("now the url => " + url);
		return false;
	}

	// 进入拦截
	@Override
	protected boolean onAccessDenied(ServletRequest request2, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("in onAccessDenied");
		HttpServletRequest request = (HttpServletRequest) request2;
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			System.out.println("session is null,get user form shiro.");
			user = ShiroUtils.getPrincipal();
			if (user == null) {
				System.out.println("please login => sign_in");
				request.getRequestDispatcher("/sign_in").forward(request, response);
				return false;
			}
			request.getSession().setAttribute("url", this.url);
			request.getRequestDispatcher("/relogin").forward(request, response);
			return false;
		}
		System.out.println("out onAccessDenied");
		return true;
	}

}
