package aster.wangxx.controller;

import aster.wangxx.aspect.XLog;
import aster.wangxx.entity.Message;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName postcontroller
 * @Description TODO
 * @Author By wangxx
 * @Date 18:15 2021/9/21
 * @Version 1.0
 **/
@RestController
@PropertySource("classpath:mymq.properties")
public class PostController {

    @Value("${com.wangxx.topicexchange}")
    private String topicExchange;

    @Value("${com.wangxx.topicroutingkey}")
    private String topicRoutingKey;

    @Autowired
    AmqpTemplate gupaoTemplate;

    @PostMapping("/getResult")
    public Map getResult () {
        return new HashMap();
    }

    @PostMapping("/addMessage")
    @XLog
    public Map addMessage (@RequestBody Message data) {
        gupaoTemplate.convertAndSend(topicExchange,topicRoutingKey, data.getMessage());
        return new HashMap();
    }

}
