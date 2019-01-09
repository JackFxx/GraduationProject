package com.graduation.carrentalweb.aop;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @description controller层日志统一接口
 * @author fuxiaoxiang2
 * @create 2018/01/07
 */
@Component
@Aspect
@Order(99)
public class VisitLogAdvice {
    private static final Logger logger = LoggerFactory.getLogger(VisitLogAdvice.class);
    /**
     * 为RequestMapping添加日志拦截
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        MDC.clear();
        MDC.put("requestId", UUID.randomUUID().toString());
        Object args[] = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        logger.info("调用开始-->调用类:{} 方法:{} 请求参数:{} ", method.getDeclaringClass().getName(),method.getName(),JSONObject.toJSON(args));
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long timeConsuming = System.currentTimeMillis() - start;
        logger.info("调用结束--> 返回值:{} 耗时:{}ms", JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue), timeConsuming);
        return result;
    }
}
