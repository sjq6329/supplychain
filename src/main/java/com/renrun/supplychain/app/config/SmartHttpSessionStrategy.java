package com.renrun.supplychain.app.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.session.Session;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.accept.ContentNegotiationStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统中会话有两种方式保持
 * 1. Cookie里JSESSION
 * 2. http头里的x-auth-token
 *
 * 这个"智能会话策略"，会根据请求头中含有"x-auth-token"来判定是非浏览器访问（比如APP场景），其他情况则视为浏览器访问，识别cookie来保持会话
 * Created by swk on 2017/1/20.
 */
@Component
public class SmartHttpSessionStrategy implements HttpSessionStrategy {

    private Logger logger = Logger.getLogger(SmartHttpSessionStrategy.class);

    private HttpSessionStrategy browser;

    private HttpSessionStrategy api;

    private RequestMatcher browserMatcher;

    @Autowired
    public SmartHttpSessionStrategy(ContentNegotiationStrategy contentNegotiationStrategy) {
        this(new CookieHttpSessionStrategy(), new HeaderHttpSessionStrategy());
        RequestHeaderRequestMatcher headerToken = new RequestHeaderRequestMatcher("x-auth-token");
        this.browserMatcher = new OrRequestMatcher(headerToken);
    }

    public SmartHttpSessionStrategy(HttpSessionStrategy browser, HttpSessionStrategy api) {
        this.browser = browser;
        this.api = api;
    }

    @Override
    public String getRequestedSessionId(HttpServletRequest request) {
        String sid = getStrategy(request).getRequestedSessionId(request);
        logger.info(String.format("获取到SESSION_ID=%s", sid));
        return sid;
    }

    @Override
    public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {
        logger.info(String.format("创建新SESSION,ID=%s", session.getId()));
        getStrategy(request).onNewSession(session, request, response);
    }

    @Override
    public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
        logger.info(String.format("有SESSION无效,ID=%s", request.getSession().getId()));
        getStrategy(request).onInvalidateSession(request, response);
    }

    private HttpSessionStrategy getStrategy(HttpServletRequest request) {
        if(this.browserMatcher.matches(request)) {
            logger.info("匹配为HEADER SESSION");
            return this.api;
        } else {
            logger.info("匹配为COOKIE SESSION");
            return this.browser;
        }
    }

}
