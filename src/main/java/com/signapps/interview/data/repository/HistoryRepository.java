package com.signapps.interview.data.repository;

import com.signapps.interview.business.domain.task.GetHistoryResponse;
import com.signapps.interview.data.entity.History;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {
     @Query("SELECT NEW com.signapps.interview.business.domain.task.GetHistoryResponse(h.taskId, h.userId, t.title, h.entryDate, h.comments) FROM History h JOIN Task t ON h.taskId = t.taskId")
     Iterable<GetHistoryResponse> getChecklistHistory();
 }
