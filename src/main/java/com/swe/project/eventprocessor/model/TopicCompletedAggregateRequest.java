package com.swe.project.eventprocessor.model;

import java.time.LocalDateTime;

public class TopicCompletedAggregateRequest {

    private String learnerId;
    private String topicId;
    private int clickedCount;
    private int totalLabels;
    private LocalDateTime completedAt;

    public TopicCompletedAggregateRequest() {
    }

    public TopicCompletedAggregateRequest(String learnerId,
                                          String topicId,
                                          int clickedCount,
                                          int totalLabels,
                                          LocalDateTime completedAt) {
        this.learnerId = learnerId;
        this.topicId = topicId;
        this.clickedCount = clickedCount;
        this.totalLabels = totalLabels;
        this.completedAt = completedAt;
    }

    public String getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(String learnerId) {
        this.learnerId = learnerId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public int getClickedCount() {
        return clickedCount;
    }

    public void setClickedCount(int clickedCount) {
        this.clickedCount = clickedCount;
    }

    public int getTotalLabels() {
        return totalLabels;
    }

    public void setTotalLabels(int totalLabels) {
        this.totalLabels = totalLabels;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}