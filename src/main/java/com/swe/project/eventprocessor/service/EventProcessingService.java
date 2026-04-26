package com.swe.project.eventprocessor.service;

import com.swe.project.eventprocessor.client.ProgressClient;
import com.swe.project.eventprocessor.model.AggregateProgressResponse;
import com.swe.project.eventprocessor.model.ProgressUpdatedEvent;
import com.swe.project.eventprocessor.model.TopicCompletedAggregateRequest;
import com.swe.project.eventprocessor.model.TopicCompletedEvent;
import com.swe.project.eventprocessor.producer.ProgressUpdatedPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class EventProcessingService {

    private final ProgressClient progressClient;
    private final ProgressUpdatedPublisher progressUpdatedPublisher;
    private final BadgeNotificationService badgeNotificationService;
    private final EmailSummaryMockService emailSummaryMockService;

    @Value("${notifications.channels:badge,email}")
    private String notificationChannels;

    public EventProcessingService(ProgressClient progressClient,
                                  ProgressUpdatedPublisher progressUpdatedPublisher,
                                  BadgeNotificationService badgeNotificationService,
                                  EmailSummaryMockService emailSummaryMockService) {
        this.progressClient = progressClient;
        this.progressUpdatedPublisher = progressUpdatedPublisher;
        this.badgeNotificationService = badgeNotificationService;
        this.emailSummaryMockService = emailSummaryMockService;
    }

    public void process(TopicCompletedEvent event) {
        TopicCompletedAggregateRequest request = new TopicCompletedAggregateRequest(
                event.getLearnerId(),
                event.getTopicId(),
                event.getClickedCount(),
                event.getTotalLabels(),
                event.getCompletedAt()
        );

        AggregateProgressResponse aggregate = progressClient.recordTopicCompleted(request);

        if (aggregate == null) {
            return;
        }

        if (aggregate.isNewlyCompleted()) {
            if (channelEnabled("badge")) {
                badgeNotificationService.incrementBadge(event.getLearnerId());
            }

            if (channelEnabled("email")) {
                emailSummaryMockService.logEmailSummary(event, aggregate);
            }
        }

        ProgressUpdatedEvent updatedEvent = new ProgressUpdatedEvent(
                aggregate.getLearnerId(),
                aggregate.getCompletedTopicCount(),
                aggregate.getTotalHotspotClicks(),
                aggregate.getLastCompletedTopicId(),
                LocalDateTime.now()
        );

        progressUpdatedPublisher.publish(updatedEvent);
    }

    private boolean channelEnabled(String channel) {
        return Arrays.stream(notificationChannels.split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .anyMatch(value -> value.equals(channel.toLowerCase()));
    }
}