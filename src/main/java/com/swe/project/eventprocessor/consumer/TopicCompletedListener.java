package com.swe.project.eventprocessor.consumer;

import com.swe.project.eventprocessor.model.TopicCompletedEvent;
import com.swe.project.eventprocessor.service.EventProcessingService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicCompletedListener {

    @RabbitListener(queues = "topic-completed")
    public void handle(String message) {
        System.out.println(">>> RECEIVED EVENT: " + message);
    }
}