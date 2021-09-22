package aster.wangxx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @ClassName CommonsConfig
 * @Description TODO
 * @Author By wangxx
 * @Date 17:39 2021/9/22
 * @Version 1.0
 **/
@Configuration
public class CommonsConfig {

    @Bean
    public SimpleDateFormat simpleDateFormat () {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }
}
