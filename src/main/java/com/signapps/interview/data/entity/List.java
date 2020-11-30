package com.signapps.interview.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LIST")
public class List {
    @Id
    @Column(name="LIST_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long listId;
    @Column(name="PATIENT_ID")
    private long patientId;
    @Column(name="LIST_NAME")
    private String listName;
    @Column(name="STATUS")
    private String status;

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
