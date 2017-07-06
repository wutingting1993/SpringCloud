package com.customer.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * Created by NCP-605 on 2017/7/6.
 */
public class HystrixRequestContextServletFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			filterChain.doFilter(servletRequest, servletResponse);
		} finally {
			context.close();
		}

	}

	@Override
	public void destroy() {

	}
}
