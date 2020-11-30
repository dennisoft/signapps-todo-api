package com.signapps.interview.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TASK")
public class Task {
    @Id
    @Column(name="TASK_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taskId;
    @Column(name="SECTION_ID")
    private long sectionId;
    @Column(name="TITLE")
    private String title;
    @Column(name="REQUIRED")
    private boolean required;
    @Column(name="STATUS")
    private String status;

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskID) {
        this.taskId = taskID;
    }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
