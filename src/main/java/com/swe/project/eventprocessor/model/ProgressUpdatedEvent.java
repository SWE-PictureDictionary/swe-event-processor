package com.swe.project.eventprocessor.model;

import java.time.LocalDateTime;

public class ProgressUpdatedEvent {

    private String learnerId;
    private int completedTopicCount;
    private long totalHotspotClicks;
    private String lastCompletedTopicId;
    private LocalDateTime updatedAt;

    public ProgressUpdatedEvent() {
    }

    public ProgressUpdatedEvent(String learnerId,
                                int completedTopicCount,
                                long totalHotspotClicks,
                                String lastCompletedTopicId,
                                LocalDateTime updatedAt) {
        this.learnerId = learnerId;
        this.completedTopicCount = completedTopicCount;
        this.totalHotspotClicks = totalHotspotClicks;
        this.lastCompletedTopicId = lastCompletedTopicId;
        this.updatedAt = updatedAt;
    }

    public String getLearnerId() {
        return learnerId;
    }

    public int getCompletedTopicCount() {
        return completedTopicCount;
    }

    public long getTotalHotspotClicks() {
        return totalHotspotClicks;
    }

    public String getLastCompletedTopicId() {
        return lastCompletedTopicId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}