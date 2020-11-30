package com.signapps.interview.business.domain.task;

import java.time.LocalDateTime;

public class GetHistoryResponse {
    private long taskId;
    private String userId;
    private String taskName;
    private LocalDateTime entryDate;
    private String comments;

    public GetHistoryResponse(long taskId, String userId, String taskName, LocalDateTime entryDate, String comments) {
        this.taskId = taskId;
        this.userId = userId;
        this.taskName = taskName;
        this.entryDate = entryDate;
        this.comments = comments;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
