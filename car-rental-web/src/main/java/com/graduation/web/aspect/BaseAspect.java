package com.graduation.web.aspect;

import com.graduation.common.constant.RedisConstant;
import com.graduation.common.context.LoginContext;
import com.graduation.domain.bo.OrderBO;
import com.graduation.domain.bo.UserBO;
import com.graduation.web.config.CheckLogin;
import com.graduation.web.config.annotation.LimitAccess;
import com.graduation.res.redis.RedisClient;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/18 12:32
 */
@Aspect
@Component
@Order(2)
public class BaseAspect {
    private static final Logger LOG = LoggerFactory.getLogger(BaseAspect.class);
    @Resource(name = "redisClient")
    private RedisClient redisClient;

    /**
     * @return java.lang.Object
     * @Author fuxiaoxiang2
     * @Description 限流切面
     * @Date 12:41 2019/1/18
     * @Param [joinPoint]
     **/
    @Around("execution(* com.graduation.web.controller ..*(..) )")
    public Object doAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        Class<?> targetClass = method.getDeclaringClass();
        //1.判断是否有登陆注解标识
        CheckLogin checkLogin = method.getAnnotation(CheckLogin.class);
        if (null != checkLogin && !checkLogin(targetClass, method, checkLogin, args)) {//需要登陆
            throw new Exception("请先登陆");
        }
        //2.判断是否有限流注解标识
        LimitAccess rateLimit = method.getAnnotation(LimitAccess.class);
        if (null != rateLimit && !checkLimit(targetClass, method, rateLimit, args)) {//需要限流
            throw new Exception("休息一下");
        }
        return joinPoint.proceed();
    }

    private boolean checkLogin(Class clazz, Method method, CheckLogin checkLogin, Object[] args) {
        //1.参数转换
        if (null == args || args.length < 1) {
            LOG.warn("empty args");
            return false;
        }
        UserBO userBO = (UserBO) redisClient.get(RedisConstant.USER_KEY_PRE + ((OrderBO) args[0]).getUserId());
        if (null != userBO && StringUtils.isNotBlank(userBO.getUsername())) {
            LoginContext.set("user", userBO);
            return true;
        }
        return false;
    }

    private boolean checkLimit(Class targetClass, Method method, LimitAccess limitAccess, Object[] args) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = getIpAddress(request);
        String key = ip + "-" + targetClass.getSimpleName() + "-" + method.getName();
        long accessTimes = redisClient.setAndIncre(key, 1, limitAccess.time());
        if (accessTimes < limitAccess.count()) {
            LOG.info("这是 ip:{},第:{}次访问,类:{},方法:{}", ip, accessTimes, targetClass.getSimpleName(), method.getName());
            return true;
        }
        return false;
    }

    /**
     * @return java.lang.String
     * @Author fuxiaoxiang2
     * @Description 获取IP信息，对ip限流
     * @Date 12:40 2019/1/18
     * @Param [request]
     **/
    private String getIpAddress(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }
}
