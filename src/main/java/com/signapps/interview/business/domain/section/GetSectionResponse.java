package com.signapps.interview.business.domain.section;

public class GetSectionResponse {

    private long sectionId;
    private String sectionName;
    private String status;
    private String userId;

    public GetSectionResponse(long sectionId, String sectionName, String status, String userId) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.status = status;
        this.userId = userId;
    }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
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
}
