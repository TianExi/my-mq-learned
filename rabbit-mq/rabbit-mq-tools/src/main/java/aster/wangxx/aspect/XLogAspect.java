package aster.wangxx.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName WirteLog
 * @Description TODO
 * @Author By wangxx
 * @Date 17:06 2021/9/22
 * @Version 1.0
 **/
@Aspect
@Component
@Slf4j
public class XLogAspect {

    String mode = "未知状态";

    @Autowired
    SimpleDateFormat SimpleDateFormat;

    @Pointcut("@annotation(aster.wangxx.aspect.XLog)")
    public void recordLog () {

    }

    @Around("@annotation(aster.wangxx.aspect.XLog)")
    public void recordLog (final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Class returnType = methodSignature.getReturnType();
        XLog xlog = method.getAnnotation(XLog.class);

        //执行处理
        this.mode = xlog.title();
        // 执行业务逻辑
        joinPoint.proceed();
    }

    @Before("recordLog()")
    public void printfDateLogBefore () {
        log.info(this.mode + "消息开始：" + SimpleDateFormat.format(new Date()));
    }

    @After("recordLog()")
    public void printfDateLogAfter () {
        log.info(this.mode + "消息结束：" + SimpleDateFormat.format(new Date()));
    }

}
