package com.signapps.interview.business.domain.checklist;

import java.time.LocalDateTime;
import java.util.Date;

public class IssueCheckListResponse {
    private int statusCode;
    private String message;
    private LocalDateTime date;

    public IssueCheckListResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public IssueCheckListResponse(int statusCode, String message, LocalDateTime date) {
        this.statusCode = statusCode;
        this.message = message;
        this.date = date;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
