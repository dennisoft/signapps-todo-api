package com.signapps.interview.data.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HISTORY")
public class History {
    @Id
    @Column(name="ENTRY_ID")
    @SequenceGenerator(name= "HISTORY_SEQUENCE", sequenceName = "HISTORY_SEQUENCE_ID", initialValue=1, allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="HISTORY_SEQUENCE")
    private long entryId;
    @Column(name="ENTRY_DATE")
    private LocalDateTime entryDate;
    @Column(name="TASK_ID")
    private long taskId;
    @Column(name="USER_ID")
    private String userId;
    @Column(name="COMMENTS")
    private String comments;

    public History() {}

    public History(LocalDateTime entryDate, long taskId, String userId, String comments) {
        this.entryDate = entryDate;
        this.taskId = taskId;
        this.userId = userId;
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
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
}
