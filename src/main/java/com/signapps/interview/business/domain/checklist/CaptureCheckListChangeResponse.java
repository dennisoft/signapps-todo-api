package com.signapps.interview.business.domain.checklist;

public class CaptureCheckListChangeResponse {
    private long taskId;
    private String newStatus;
    private String message;
    private int responseCode;

    public CaptureCheckListChangeResponse(long taskId, String newStatus, String message, int responseCode) {
        this.taskId = taskId;
        this.newStatus = newStatus;
        this.message = message;
        this.responseCode = responseCode;
    }

    public CaptureCheckListChangeResponse(String message, int responseCode) {
        this.message = message;
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
}
