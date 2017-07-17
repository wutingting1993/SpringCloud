import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletRequest

/**
 * Created by NCP-605 on 2017/7/13.
 */
class PreFilter extends ZuulFilter {
    Logger logger = LoggerFactory.getLogger(PreFilter.class);

    @Override
    String filterType() {
        return "pre"
    }

    @Override
    int filterOrder() {
        return 1000
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest()
        logger.warn("this is pre filter: send {} request to {}", request.getMethod(), request.getRequestURL().toString())
    }
}
