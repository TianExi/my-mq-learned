package aster.wangxx.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName KafkaConsumerApp
 * @Description TODO
 * @Author By wangxx
 * @Date 18:03 2021/9/23
 * @Version 1.0
 **/
@SpringBootApplication
@ComponentScan({"aster.wangxx"})
public class KafkaConsumerApp {

    public static void main (String [] args) {
        SpringApplication.run(KafkaConsumerApp.class, args);
    }
}
