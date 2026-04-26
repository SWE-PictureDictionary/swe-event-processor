package com.swe.project.eventprocessor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swe.project.eventprocessor.model.AggregateProgressResponse;
import com.swe.project.eventprocessor.model.TopicCompletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class EmailSummaryMockService {

    private static final Logger logger = LoggerFactory.getLogger(EmailSummaryMockService.class);

    private final ObjectMapper objectMapper;

    public EmailSummaryMockService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void logEmailSummary(TopicCompletedEvent event,
                                AggregateProgressResponse aggregate) {
        try {
            Map<String, Object> email = new LinkedHashMap<>();
            email.put("channel", "email-summary");
            email.put("to", "learner-" + event.getLearnerId() + "@mock.local");
            email.put("subject", "Topic completed: " + event.getTopicId());
            email.put("learnerId", event.getLearnerId());
            email.put("topicId", event.getTopicId());
            email.put("completedTopicCount", aggregate.getCompletedTopicCount());
            email.put("totalHotspotClicks", aggregate.getTotalHotspotClicks());
            email.put("sentAt", LocalDateTime.now().toString());

            logger.info(objectMapper.writeValueAsString(email));
        } catch (Exception e) {
            throw new RuntimeException("Failed to log mock email summary", e);
        }
    }
}
