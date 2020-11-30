package com.signapps.interview.business.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.signapps.interview.data.entity.History;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class HistoryService {

    @PersistenceContext
    private EntityManager entityManager;

    public long insert(History history) {
        entityManager.persist(history);
        return history.getEntryId();
    }
}