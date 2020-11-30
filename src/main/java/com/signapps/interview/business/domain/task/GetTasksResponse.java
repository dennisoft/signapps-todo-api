package com.signapps.interview.business.domain.task;

public class GetTasksResponse {
    private long taskId;
    private String sectionName;
    private String title;
    private String status;
    private String userId;
    private boolean required;

    public GetTasksResponse() {

    }

    public GetTasksResponse(long taskId, String sectionName, String title, String status, String userId, boolean required) {
        this.taskId = taskId;
        this.sectionName = sectionName;
        this.title = title;
        this.status = status;
        this.userId = userId;
        this.required = required;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
