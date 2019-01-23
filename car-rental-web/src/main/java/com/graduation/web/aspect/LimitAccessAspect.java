package com.graduation.web.aspect;

import com.graduation.web.config.annotation.LimitAccess;
import com.graduation.res.redis.RedisClient;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class LimitAccessAspect {
    private static final Logger LOG = LoggerFactory.getLogger(LimitAccessAspect.class);
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
    public Object limit(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        LimitAccess rateLimit = method.getAnnotation(LimitAccess.class);//获取标注有LimitAccess注解的方法
        if (null != rateLimit) {//需要限流
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String ip = getIpAddress(request);
            String key = ip + "-" + targetClass.getSimpleName() + "-" + method.getName();
            long accessTimes = redisClient.setAndIncre(key, 1, rateLimit.time());
            if (accessTimes < rateLimit.count()) {
                LOG.info("这是 ip:{},第:{}次访问,类:{},方法:{}", ip, accessTimes, targetClass.getSimpleName(), method.getName());
                return joinPoint.proceed();
            }
        } else {//不需要限流
            return joinPoint.proceed();
        }
        throw new Exception("休息一下吧!");
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
