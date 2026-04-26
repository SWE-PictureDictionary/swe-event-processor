package com.swe.project.eventprocessor.model;

public class AggregateProgressResponse {

    private String learnerId;
    private boolean newlyCompleted;
    private int completedTopicCount;
    private long totalHotspotClicks;
    private String lastCompletedTopicId;

    public AggregateProgressResponse() {
    }

    public String getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(String learnerId) {
        this.learnerId = learnerId;
    }

    public boolean isNewlyCompleted() {
        return newlyCompleted;
    }

    public void setNewlyCompleted(boolean newlyCompleted) {
        this.newlyCompleted = newlyCompleted;
    }

    public int getCompletedTopicCount() {
        return completedTopicCount;
    }

    public void setCompletedTopicCount(int completedTopicCount) {
        this.completedTopicCount = completedTopicCount;
    }

    public long getTotalHotspotClicks() {
        return totalHotspotClicks;
    }

    public void setTotalHotspotClicks(long totalHotspotClicks) {
        this.totalHotspotClicks = totalHotspotClicks;
    }

    public String getLastCompletedTopicId() {
        return lastCompletedTopicId;
    }

    public void setLastCompletedTopicId(String lastCompletedTopicId) {
        this.lastCompletedTopicId = lastCompletedTopicId;
    }
}
