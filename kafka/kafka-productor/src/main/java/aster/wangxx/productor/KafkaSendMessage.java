package aster.wangxx.productor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @ClassName KafkaSendMessage
 * @Description TODO
 * @Author By wangxx
 * @Date 19:36 2021/9/23
 * @Version 1.0
 **/
@Component
public class KafkaSendMessage {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void Send(final String message){
        //向test主题发送消息
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("mytopic", message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            public void onFailure(Throwable throwable) {
                System.out.printf("消息：{} 发送失败，原因：{}", message, throwable.getMessage());
            }

            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                System.out.printf("成功发送消息：{}，offset=[{}]", message, stringStringSendResult.getRecordMetadata().offset());
            }
        });

    }
}
