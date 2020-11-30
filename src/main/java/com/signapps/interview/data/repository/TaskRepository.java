package com.signapps.interview.data.repository;

import com.signapps.interview.business.domain.task.GetTasksResponse;
import com.signapps.interview.data.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Iterable<Task> findTaskBySectionId(Long sectionId);

    @Query("SELECT NEW com.signapps.interview.business.domain.task.GetTasksResponse(t.taskId, s.sectionName, t.title, t.status, s.userId, t.required) FROM Task t JOIN Section s ON t.sectionId = s.sectionId WHERE s.userId = :user")
    Iterable<GetTasksResponse> getAllTasksByUserId(@Param("user") String userId);

    @Query("UPDATE Task SET status = :status WHERE taskId = :task")
    Iterable<Task> updateTaskStatus(@Param("status") String status, @Param("task") long taskId);

    @Query("SELECT COUNT(t.taskId) FROM Task t JOIN Section s ON t.sectionId = s.sectionId WHERE s.sectionId = :section AND t.status <> 'Completed' AND t.required <> false")
    int getNumberOfPendingTasksBySection(@Param("section") long sectionId);
}