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

        // Получаем или создаем счетчик для метода
        requestCounts.putIfAbsent(methodName, new AtomicInteger(0));
        AtomicInteger counter = requestCounts.get(methodName);

        // Получаем текущую минуту (время в минутах с начала эпохи)
        long currentMinute = System.currentTimeMillis() / 60000;

        // Получаем или устанавливаем последнюю минуту для метода
        lastMinuteTimestamps.putIfAbsent(methodName, currentMinute);
        long lastMinute = lastMinuteTimestamps.get(methodName);

        // Если текущая минута отличается от последней, сбрасываем счетчик
        if (currentMinute != lastMinute) {
            counter.set(0);
            lastMinuteTimestamps.put(methodName, currentMinute);
        }

        // Если превышено ограничение запросов, выбрасываем исключение
        if (counter.get() >= limitRequest.value()) {
            throw new LimitRequestException("The number of requests is limited to " + limitRequest.value()+ " per minute");
        }

        // Увеличиваем счетчик запросов
        counter.incrementAndGet();

        // Продолжаем выполнение метода
        return joinPoint.proceed();
    }
}
