package aster.wangxx.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.ManagedBean;

/**
 * @ClassName ProductorApp
 * @Description TODO
 * @Author By wangxx
 * @Date 17:02 2021/9/21
 * @Version 1.0
 **/
@SpringBootApplication
@ComponentScan({"aster.wangxx"})
public class ProductorApp {
    public static void main (String [] args) {
        SpringApplication.run(ProductorApp.class, args);
    }
}
