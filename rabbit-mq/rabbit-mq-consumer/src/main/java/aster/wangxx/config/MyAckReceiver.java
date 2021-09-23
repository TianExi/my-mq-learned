package aster.wangxx.config;

import aster.wangxx.aspect.XLog;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyAckReceiver
 * @Description TODO
 * @Author By wangxx
 * @Date 9:01 2021/9/23
 * @Version 1.0
 **/
@Slf4j
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {

    @Override
    @XLog(title = "消费")
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();//代表RabbitMQ向该Channel投递的这条消息的唯一标识
        try {
            log.info("收到消息：" + message.getBody());
            log.info("消费的主题消息来自："+message.getMessageProperties().getConsumerQueue());

            //判断确认消息逻辑
            if (true) {
                channel.basicAck(deliveryTag, false); //参数false 确认单条消息
                //channel.basicReject(deliveryTag, true);//参数true 可以一次性确认 delivery_tag 小于等于传入值的所有消息
            }
        } catch (Exception e) {
            log.info("确认消息失败：" + e.getMessage());
        }
    }

    /**
     * {key=value,key=value,key=value} 格式转换成map
     * @param str
     * @param entryNum
     * @return
     */
    private Map<String, String> mapStringToMap(String str, int entryNum ) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",",entryNum);
        Map<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key = string.split("=")[0].trim();
            String value = string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }
}
