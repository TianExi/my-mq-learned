package aster.wangxx.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName RabbitConfig
 * @Description TODO
 * @Author By wangxx
 * @Date 7:20 2021/9/22
 * @Version 1.0
 **/
@Configuration
@PropertySource("classpath:mymq.properties")
public class RabbitConfig {

    @Value("${com.wangxx.topicexchange}")
    private String topicExchange;

    @Value("${com.wangxx.fanoutexchange}")
    private String fanoutexchange;

    @Value("${com.wangxx.firstqueue}")
    private String firstQueue;

    @Value("${com.wangxx.secondqueue}")
    private String secondqueue;

    @Value("${com.wangxx.thirdqueue}")
    private String thirdqueue;

    @Value("${com.wangxx.fourthqueue}")
    private String fourthqueue;

    @Value("${com.wangxx.fifthqueue}")
    private String fifthqueue;

    // 创建3个队列
    @Bean("vipFirstQueue")
    public Queue getFirstQueue(){
        return new Queue(firstQueue);
    }
    @Bean("vipSecondQueue")
    public Queue getSecondQueue(){
        return new Queue(secondqueue);
    }
    @Bean("vipThirdQueue")
    public Queue getThirdQueue(){
        return new Queue(thirdqueue);
    }
    @Bean("vipFourthQueue")
    public Queue getFourthQueue(){
        return new Queue(fourthqueue);
    }
    @Bean("vipFifthQueue")
    public Queue getFifthQueue(){
        return new Queue(fifthqueue);
    }

    //创建2个交换机
    @Bean("vipTopicExchange")
    public TopicExchange getTopicExchange(){
        return new TopicExchange(topicExchange);
    }
    @Bean("vipFanoutexchange")
    public FanoutExchange getFanoutexchange(){
        return new FanoutExchange(fanoutexchange);
    }

    //创建3个绑定关系
    @Bean
    public Binding bindFirst(@Qualifier("vipFirstQueue") Queue queue, @Qualifier("vipTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("*.demo01.*");
    }
    @Bean
    public Binding bindSecond(@Qualifier("vipSecondQueue") Queue queue, @Qualifier("vipTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("*.demo02.*");
    }
    @Bean
    public Binding bindThird(@Qualifier("vipThirdQueue") Queue queue, @Qualifier("vipTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("*.demo03.*");
    }
    @Bean
    public Binding bindFourth(@Qualifier("vipFourthQueue") Queue queue, @Qualifier("vipFanoutexchange") FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }
    @Bean
    public Binding bindFifth(@Qualifier("vipFifthQueue") Queue queue, @Qualifier("vipFanoutexchange") FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }

    /**
     * 在消费端转换JSON消息
     * 监听类都要加上containerFactory属性
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setAutoStartup(true);
        return factory;
    }
}
