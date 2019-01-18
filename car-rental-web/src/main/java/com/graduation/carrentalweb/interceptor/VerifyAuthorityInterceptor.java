package com.graduation.carrentalweb.interceptor;

import com.graduation.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 统一身份认证拦截器
 * @Author fuxiaoxiang2
 * @Create 2019/1/10 16:14
 */

public class VerifyAuthorityInterceptor implements HandlerInterceptor {
    //一些必须要登陆权限才可以操作的url
    private static final Map<String, Integer> URL = new HashMap<String, Integer>() {{
        put("personal", 1);
        put("buy", 1);
    }};
    private static final String JUMP_URL = "/user/index";
    @Resource(name = "userService")
    UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(VerifyAuthorityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        if (StringUtils.isBlank(url)) {
            return false;
        }
        if (URL.containsKey(url.split("/")[0])) {
            //验证是否登陆
            if (null == request.getSession().getAttribute("token")) {
                logger.warn("请先登陆!");
                response.sendRedirect(JUMP_URL);
                return false;
            }
            return true;
        }
        return true;
    }
}
