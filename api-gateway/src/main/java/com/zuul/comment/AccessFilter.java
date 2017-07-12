package com.zuul.comment;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Created by NCP-605 on 2017/7/12.
 */
public class AccessFilter extends ZuulFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccessFilter.class);
	private String filterType;

	public AccessFilter(String filterType) {
		this.filterType = filterType;
	}

	@Override
	public String filterType() {
		//pre,routing,post,error
		return filterType;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		switch (filterType) {
			case "pre":
				RequestContext requestContext = RequestContext.getCurrentContext();
				HttpServletRequest request = requestContext.getRequest();
				LOGGER.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

				String token = request.getParameter("token");
				if (!"123456".equalsIgnoreCase(token)) {
					LOGGER.info("access fail....");
					requestContext.setSendZuulResponse(false);
					requestContext.setResponseStatusCode(8888);
				} else {
					LOGGER.info("access success....");
				}
				break;
			default:
				break;
		}

		return null;
	}
}
