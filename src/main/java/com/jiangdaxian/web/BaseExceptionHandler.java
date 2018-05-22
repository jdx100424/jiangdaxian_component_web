package com.jiangdaxian.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
class BaseExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(BaseExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public Map<String, Object> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		LOG.error(e.getMessage(),e);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 500);
		map.put("message", "系统发生错误");
		return map;
	}
}
