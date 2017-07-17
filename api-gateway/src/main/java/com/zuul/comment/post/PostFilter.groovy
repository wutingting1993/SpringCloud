import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletResponse

/**
 * Created by NCP-605 on 2017/7/13.
 */
class PostFilter extends ZuulFilter {
    Logger logger = LoggerFactory.getLogger(PostFilter.class);

    @Override
    String filterType() {
        return "post"
    }

    @Override
    int filterOrder() {
        return 2000
    }

    @Override
    boolean shouldFilter() {
        return false
    }

    @Override
    Object run() {
        logger.warn("this is post filter: receive respose")
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
        response.getOutputStream().print(", i am zhangsan.....")
        response.flushBuffer()
    }
}
