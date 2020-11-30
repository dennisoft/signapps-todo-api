package com.signapps.interview.business.service;

import com.signapps.interview.business.domain.checklist.CaptureCheckListChange;
import com.signapps.interview.business.domain.checklist.CaptureCheckListChangeResponse;
import com.signapps.interview.data.entity.History;
import com.signapps.interview.data.entity.Task;
import com.signapps.interview.data.repository.SectionRepository;
import com.signapps.interview.data.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class CaptureChecklistChangeService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    SectionStatusService sectionStatusService;

    @Autowired
    UpdateSectionService updateSectionService;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    HistoryService historyService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptureChecklistChangeService.class);

    @Async
    public CompletableFuture<CaptureCheckListChangeResponse> captureCheckListChange(CaptureCheckListChange captureCheckListChangeRequest) {
        UUID uuid = UUID.randomUUID();
        LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|Checklist change was detected, attempting to capture the change");
        Optional<Task> task = this.taskRepository.findById(captureCheckListChangeRequest.getTaskId());
        if (task.isPresent()) {
            Task updatedTask = new Task();
            updatedTask.setStatus(captureCheckListChangeRequest.getStatus());
            updatedTask.setSectionId(task.get().getSectionId());
            updatedTask.setRequired(task.get().isRequired());
            updatedTask.setTitle(task.get().getTitle());
            updatedTask.setTaskId(captureCheckListChangeRequest.getTaskId());
            LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|Confirmed that task exists and is valid");
            try {
                taskRepository.save(updatedTask);
                LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|Task status updated successfully");

                if (sectionStatusService.isSectionCompleted(task.get().getSectionId())) {
                    updateSectionService.updateSection(task.get().getSectionId());
                    LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|Section status updated successfully");
                }

                historyService.insert(new History(java.time.LocalDateTime.now(), task.get().getTaskId(), captureCheckListChangeRequest.getUserId(), captureCheckListChangeRequest.getStatus()));
                LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|History entry for the task added successfully");
                CaptureCheckListChangeResponse response =  new CaptureCheckListChangeResponse(captureCheckListChangeRequest.getTaskId(), captureCheckListChangeRequest.getStatus(), "Success", 200);
                LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|Operation complete: Task change capture complete");

                return CompletableFuture.completedFuture(response);
            } catch (Exception e) {
                CaptureCheckListChangeResponse response = new CaptureCheckListChangeResponse("Could not capture checklist change, try again later", 500);
                LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|Operation ends in error: could not capture the change|" + e.getMessage());
                return CompletableFuture.completedFuture(response);
            }
        } else {
            CaptureCheckListChangeResponse response =  new CaptureCheckListChangeResponse("This task does not exist", 400);
            LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|TOperation ends in error: he task does not exist|400");
            return CompletableFuture.completedFuture(response);
        }
    }

}
