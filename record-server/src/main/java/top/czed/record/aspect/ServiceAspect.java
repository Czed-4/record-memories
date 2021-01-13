package top.czed.record.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author Czed
 * @Date 2021-1-11
 * @Description
 * @Version 1.0
 */
@Slf4j
@Aspect
@Component
public class ServiceAspect {

    @Around("execution(* top.czed.record.service.impl..*.*(..))")
    public Object mark(ProceedingJoinPoint joinPoint) throws Throwable {
        //方法开始时间
        long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        //方法结束时间
        long endTime = System.currentTimeMillis();
        long takeTime = endTime - beginTime;
        log.info("方法{}.{} 耗时: {}ms", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), takeTime);
        return result;
    }

}
