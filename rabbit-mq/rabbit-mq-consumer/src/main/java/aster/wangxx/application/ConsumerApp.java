package aster.wangxx.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName ConsumerApp
 * @Description TODO
 * @Author By wangxx
 * @Date 7:18 2021/9/22
 * @Version 1.0
 **/
@SpringBootApplication
@ComponentScan({"aster.wangxx"})
public class ConsumerApp {

    public static void main (String [] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }
}
