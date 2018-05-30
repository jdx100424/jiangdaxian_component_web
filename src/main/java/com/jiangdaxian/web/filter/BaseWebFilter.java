package com.jiangdaxian.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSONObject;
import com.jiangdaxian.common.base.BaseInfoContext;
import com.jiangdaxian.common.dubbo.filter.constant.DubboFilterConstant;

@WebFilter(urlPatterns = "/*")
public class BaseWebFilter implements Filter {
	private static final Logger LOG = LoggerFactory.getLogger(BaseWebFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("BaseWebFilter init");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		LOG.info("BaseWebFilter gogo");
		
		BaseInfoContext baseInfoContext = BaseInfoContext.get();
		baseInfoContext.setRequestId(servletRequest.getParameter("requestId"));
		
		Map<String,String> map = new HashMap<String,String>();
		map.put(DubboFilterConstant.BASE_INFO_CONTEXT, JSONObject.toJSONString(baseInfoContext));
		RpcContext.getContext().setAttachments(map);
		
		LOG.info("BaseWebFilter map is:{}",JSONObject.toJSONString(map));
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
