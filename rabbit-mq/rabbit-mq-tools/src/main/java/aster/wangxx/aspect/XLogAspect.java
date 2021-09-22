package aster.wangxx.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    SimpleDateFormat SimpleDateFormat;

    @Pointcut("@annotation(aster.wangxx.aspect.XLog)")
    public void recordLog () {

    }

    @Before("recordLog()")
    public void printfDateLogBefore () {
        log.info("消息新增开始：" + SimpleDateFormat.format(new Date()));
    }

    @After("recordLog()")
    public void printfDateLogAfter () {
        log.info("消息新增结束：" + SimpleDateFormat.format(new Date()));
    }

}
