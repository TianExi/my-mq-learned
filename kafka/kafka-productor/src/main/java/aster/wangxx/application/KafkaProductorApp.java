package aster.wangxx.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName KafkaProductorApp
 * @Description TODO
 * @Author By wangxx
 * @Date 17:48 2021/9/23
 * @Version 1.0
 **/
@SpringBootApplication
@ComponentScan({"aster.wangxx"})
public class KafkaProductorApp {

    public static void main (String [] args) {
        SpringApplication.run(KafkaProductorApp.class, args);

    }
}
