package aster.wangxx.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyConsumer
 * @Description TODO
 * @Author By wangxx
 * @Date 7:38 2021/9/22
 * @Version 1.0
 **/

@Component
@PropertySource("classpath:mymq.properties")
@RabbitListener(queues = "${com.wangxx.fifthqueue}", containerFactory="rabbitListenerContainerFactory")
public class FifthConsumer {

    @RabbitHandler
    public void process(String data){
        System.out.println("Fifth Queue received msg : " + data);
    }
}
