package aster.wangxx.controller;

import aster.wangxx.aspect.XLog;
import aster.wangxx.entity.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@PropertySource("classpath:mymq.properties")
public class PostController {

    @Value("${com.wangxx.topicexchange}")
    private String topicExchange;

    @Value("${com.wangxx.topicroutingkey01}")
    private String topicRoutingKey01;

    @Value("${com.wangxx.topicroutingkey02}")
    private String topicRoutingKey02;

    @Value("${com.wangxx.topicroutingkey03}")
    private String topicRoutingKey03;

    @Value("${com.wangxx.fanoutexchange}")
    private String fanoutexchange;

    @Autowired
    AmqpTemplate gupaoTemplate;

    @PostMapping("/getResult")
    public Map getResult () {
        return new HashMap();
    }

    /**
     * 根据上送的消息id分发消息到三个绑定队列
     * @param data
     * @throws JsonProcessingException
     */
    @PostMapping("/addMessage")
    @XLog
    public void addMessage (@RequestBody Message data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(data);
        if (1 == data.getId() % 3) {
            log.info(">>>>>>>>>>>>>>新增消息组1");
            gupaoTemplate.convertAndSend(topicExchange,topicRoutingKey01, json);
        } else if (2 == data.getId() % 3) {
            log.info(">>>>>>>>>>>>>>新增消息组2");
            gupaoTemplate.convertAndSend(topicExchange,topicRoutingKey02, json);
        } else {
            log.info(">>>>>>>>>>>>>>新增消息组3");
            gupaoTemplate.convertAndSend(topicExchange,topicRoutingKey03, json);
        }
    }

    /**
     * 将消息发布到广播交换机 交换机将消息广播到各个绑定的消息队列
     * @param data
     * @throws JsonProcessingException
     */
    @PostMapping("/broadcastMessage")
    @XLog
    public void broadcastMessage (@RequestBody Message data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(data);
        gupaoTemplate.convertAndSend(fanoutexchange, "", json);
    }

}
