package com.signapps.interview.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SECTION")
public class Section {
    @Id
    @Column(name="SECTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sectionId;
    @Column(name="LIST_ID")
    private long listId;
    @Column(name="SECTION_NAME")
    private String sectionName;
    @Column(name="STATUS")
    private String status;
    @Column(name="USER_ID")
    private String userId;

    public Section(long sectionId, long listId, String sectionName, String status, String userId) {
        this.sectionId = sectionId;
        this.listId = listId;
        this.sectionName = sectionName;
        this.status = status;
        this.userId = userId;
    }

    public Section() { }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
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
