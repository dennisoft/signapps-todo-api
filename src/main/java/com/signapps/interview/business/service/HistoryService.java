package com.signapps.interview.business.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.signapps.interview.business.domain.task.GetHistoryResponse;
import com.signapps.interview.data.entity.History;
import com.signapps.interview.data.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class HistoryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    HistoryRepository historyRepository;

    public long insert(History history) {
        entityManager.persist(history);
        return history.getEntryId();
    }

    public Iterable<GetHistoryResponse> getHistoryByList(long listId) {
        return historyRepository.getChecklistHistory();
    }
}