package com.signapps.interview.business.domain.section;

public class GetSectionStatusQueryOutput {
    private long sectionId;
    private String sectionName;
    private String status;

    public GetSectionStatusQueryOutput(long sectionId, String sectionName, String status) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.status = status;
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
}
