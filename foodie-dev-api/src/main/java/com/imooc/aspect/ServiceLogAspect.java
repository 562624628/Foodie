package com.imooc.aspect;

import com.imooc.utils.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ServiceLogAspect {
    /*
     * AOP通知：
     * 1.前置通知：在方法调用之前执行
     * 2.后置通知：在方法调用之后执行
     * 3.环绕通知：在方法调用之前和之后，都分别可以执行的通知
     * 4.异常通知：如果在方法调用过程中发生异常，则通知
     * 5.最终通知：在方法调用之后执行
     * */
    /*
     *   切面表达式
     *   execution代表所要执行的表达式的主体
     *   第一处* 代表方法返回类型 * 代表所有类型
     *   第二处 报名代表aop监控的类所在的包
     *   第三处 ..代表该包以及其子包下的所有类方法
     *   第四处 *代表类名 *代表所有类
     *   第五处*（..）*代表类中的方法名 （..）表示方法中的任何参数
     *
     * */
    @Around("execution(* com.imooc.service.impl..*.*(..))")
    @SneakyThrows
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) {
        log.info("=====开始执行{}.{}====", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());
        Long begin = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        log.info("=====开始执行request:{}", arrayToString(args));
        Object proceed = joinPoint.proceed();

        Long end = System.currentTimeMillis();
        Long takeTime = end - begin;
        if (takeTime > 3000) {
            log.error("=====执行结束 耗时:{}毫秒 ====", takeTime);
        }else {
            log.info("=====执行结束 耗时:{}毫秒 ====", takeTime);
        }
        return proceed;


    }

    private String arrayToString(Object[] array) {
        if (array == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
