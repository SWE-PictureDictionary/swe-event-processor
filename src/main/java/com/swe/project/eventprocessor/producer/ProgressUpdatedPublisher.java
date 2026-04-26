package com.swe.project.eventprocessor.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swe.project.eventprocessor.config.RabbitConfig;
import com.swe.project.eventprocessor.model.ProgressUpdatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProgressUpdatedPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public ProgressUpdatedPublisher(RabbitTemplate rabbitTemplate,
                                    ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void publish(ProgressUpdatedEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);

            rabbitTemplate.convertAndSend(
                    RabbitConfig.PROGRESS_UPDATED_EXCHANGE,
                    RabbitConfig.PROGRESS_UPDATED_ROUTING_KEY,
                    json
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to publish ProgressUpdated event", e);
        }
    }
}