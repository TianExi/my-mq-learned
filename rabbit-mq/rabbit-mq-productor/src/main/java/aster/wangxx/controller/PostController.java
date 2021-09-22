package aster.wangxx.controller;

import com.alibaba.fastjson.JSONObject;
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
@PropertySource("classpath:gupaomq.properties")
public class PostController {

    @Value("${com.gupaoedu.topicexchange}")
    private String topicExchange;

    @Value("${com.gupaoedu.topicroutingkey}")
    private String topicRoutingKey;

    @Autowired
    AmqpTemplate gupaoTemplate;

    @PostMapping("/getResult")
    public Map getResult () {
        return new HashMap();
    }

    @PostMapping("/addMessage")
    public Map addMessage (@RequestBody JSONObject data) {
        gupaoTemplate.convertAndSend(topicExchange,topicRoutingKey, data.toJSONString());
        return new HashMap();
    }

}
