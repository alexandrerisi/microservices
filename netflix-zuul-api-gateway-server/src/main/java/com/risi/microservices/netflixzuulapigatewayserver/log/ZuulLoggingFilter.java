package com.risi.microservices.netflixzuulapigatewayserver.log;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() /*throws ZuulException*/ {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("request -> {" + request + "} request uri -> {" + request.getRequestURI() + "}");
        return null;
    }
}
