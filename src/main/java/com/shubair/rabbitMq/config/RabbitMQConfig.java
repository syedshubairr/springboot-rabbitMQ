package com.shubair.rabbitMq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.queueName}")
    private String queueName;
    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;
    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    //  Spring bean for Rabbit MQ.
    @Bean
    public Queue queue() {
        return new Queue(queueName, true); // Durable queue
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange, true, false); // Durable exchange, not auto-deleted
    }

    //    Binding between Queue and exchange of RabbitMQ.
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }
}
