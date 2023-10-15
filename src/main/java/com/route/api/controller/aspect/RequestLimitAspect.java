package com.route.api.controller.aspect;

import com.route.api.exception.LimitRequestException;
import com.route.api.util.LimitRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class RequestLimitAspect {
    private Map<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();
    private Map<String, Long> lastMinuteTimestamps = new ConcurrentHashMap<>();

    @Pointcut("@annotation(limitRequest)")
    public void rateLimitedMethods(LimitRequest limitRequest) {
    }

    @Around(value = "rateLimitedMethods(limitRequest)", argNames = "joinPoint,limitRequest")
    public Object checkRateLimit(ProceedingJoinPoint joinPoint, LimitRequest limitRequest) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        requestCounts.putIfAbsent(methodName, new AtomicInteger(0));
        AtomicInteger counter = requestCounts.get(methodName);

        long currentMinute = System.currentTimeMillis() / 60000;

        lastMinuteTimestamps.putIfAbsent(methodName, currentMinute);

        long lastMinute = lastMinuteTimestamps.get(methodName);

        if (currentMinute != lastMinute) {
            counter.set(0);
            lastMinuteTimestamps.put(methodName, currentMinute);
        }

        if (counter.get() >= limitRequest.value()) {
            throw new LimitRequestException("The number of requests is limited to " + limitRequest.value()+ " per minute");
        }

        counter.incrementAndGet();
        return joinPoint.proceed();
    }
}
