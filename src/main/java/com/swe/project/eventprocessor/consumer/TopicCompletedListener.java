package com.swe.project.eventprocessor.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swe.project.eventprocessor.config.RabbitConfig;
import com.swe.project.eventprocessor.model.TopicCompletedEvent;
import com.swe.project.eventprocessor.service.EventProcessingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicCompletedListener {

    private final ObjectMapper objectMapper;
    private final EventProcessingService eventProcessingService;

    public TopicCompletedListener(ObjectMapper objectMapper,
                                  EventProcessingService eventProcessingService) {
        this.objectMapper = objectMapper;
        this.eventProcessingService = eventProcessingService;
    }

    @RabbitListener(queues = RabbitConfig.TOPIC_COMPLETED_QUEUE)
    public void handle(String message) {
        try {
            TopicCompletedEvent event = objectMapper.readValue(message, TopicCompletedEvent.class);
            eventProcessingService.process(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}