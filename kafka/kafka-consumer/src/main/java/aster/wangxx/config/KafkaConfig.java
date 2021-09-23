package aster.wangxx.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

/**
 * @ClassName KafkaConfig
 * @Description TODO
 * @Author By wangxx
 * @Date 18:31 2021/9/23
 * @Version 1.0
 **/

@Configuration
@EnableConfigurationProperties({KafkaProperties.class})
@EnableKafka
@AllArgsConstructor
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());//监听的线程数量，多个线程入库，数据库的id是自增的话，可能导致死锁，建议使用UUID

        //表示创建1个KafkaMessageListenerContainer实例
        //如果设置为1的情况下, 这一个实例消费Topic的所有分区;
        //如果设置多个,那么会平均分配所有分区;
        //如果实例>分区数; 那么空出来的实例会浪费掉;
        //如果实例<=分区数 那么会有一部分实例消费多个实例,但也是均衡分配的
        //如果在分布式情况下, 那么总的KafkaMessageListenerContainer实例数= 服务器机器数量*concurrency
        factory.setConcurrency(1);
        /*消费者有两种消费模式，一种是kafka实例主动推送push模式，推送速度由kafka决定，很有可能导致消费端阻塞，
         *另一种就是 消费者主动拉取，poll模式
         * */
        factory.getContainerProperties().setPollTimeout(3000);
        //当使用手动提交时必须设置ackMode为MANUAL,否则会报错No Acknowledgment available as an argument, the listener container must have a MANUAL AckMode to populate the Acknowledgment.
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        // 下面两个条件哪个先满足，就会先使用那个
        factory.getContainerProperties().setAckCount(10);//达到10条数据的时候提交一次
        factory.getContainerProperties().setAckTime(10000);//10s提交一次
        return factory;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory(kafkaProperties.buildConsumerProperties());
    }
}
