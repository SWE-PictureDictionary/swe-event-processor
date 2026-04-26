package com.swe.project.eventprocessor.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String TOPIC_COMPLETED_EXCHANGE = "topic-completed-exchange";
    public static final String TOPIC_COMPLETED_QUEUE = "topic-completed";
    public static final String TOPIC_COMPLETED_ROUTING_KEY = "completed.#";

    public static final String PROGRESS_UPDATED_EXCHANGE = "progress-updated-exchange";
    public static final String PROGRESS_UPDATED_ROUTING_KEY = "progress.updated";

    @Bean
    public Queue topicCompletedQueue() {
        return new Queue(TOPIC_COMPLETED_QUEUE, true);
    }

    @Bean
    public TopicExchange topicCompletedExchange() {
        return new TopicExchange(TOPIC_COMPLETED_EXCHANGE);
    }

    @Bean
    public Binding topicCompletedBinding(Queue topicCompletedQueue,
                                         TopicExchange topicCompletedExchange) {
        return BindingBuilder
                .bind(topicCompletedQueue)
                .to(topicCompletedExchange)
                .with(TOPIC_COMPLETED_ROUTING_KEY);
    }

    @Bean
    public TopicExchange progressUpdatedExchange() {
        return new TopicExchange(PROGRESS_UPDATED_EXCHANGE);
    }
}