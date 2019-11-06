package com.zsl.YgForest.configuration.exception;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import priv.zsl.utils.jackson.JsonUtils;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest reqest, HttpServletResponse response, Exception e) throws Exception {
		e.printStackTrace();
		System.out.println("捕获到:" + reqest.getRequestURL());
		//String url = reqest.getRequestURL().toString();
		return JsonUtils.error(e.getMessage());
	}

	/**
	 * 
	 * @param httpRequest
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest httpRequest) {
		return (httpRequest.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString()));
	}
}
