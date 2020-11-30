package com.signapps.interview.business.service;

import com.signapps.interview.business.domain.checklist.IssueCheckList;
import com.signapps.interview.business.domain.checklist.IssueCheckListResponse;
import com.signapps.interview.data.entity.History;
import com.signapps.interview.data.entity.List;
import com.signapps.interview.data.entity.Section;
import com.signapps.interview.data.entity.Task;
import com.signapps.interview.data.repository.ListRepository;
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
public class IssueCheckListService {
    private final ListRepository listRepository;
    private final SectionRepository sectionRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public IssueCheckListService(ListRepository listRepository, SectionRepository sectionRepository, TaskRepository taskRepository) {
        this.listRepository = listRepository;
        this.sectionRepository = sectionRepository;
        this.taskRepository = taskRepository;
    }

    @Autowired
    HistoryService historyService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptureChecklistChangeService.class);

    @Async
    public CompletableFuture<IssueCheckListResponse> issueCheckList(IssueCheckList checkList) {
        UUID uuid = UUID.randomUUID();
        LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|Operation to assign tasks / section to users detected");

        Optional<List> list = this.listRepository.findById(checkList.getListId());
        if (list.isPresent()) {
            Optional<Section> section = this.sectionRepository.findById(checkList.getSectionId());
            if (section.isPresent()) {
                Section newSection = new Section();
                newSection.setListId(checkList.getListId());
                newSection.setSectionId(checkList.getSectionId());
                newSection.setStatus("Assigned");
                newSection.setUserId(checkList.getUserId());
                newSection.setSectionName(section.get().getSectionName());
                LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|Confirmed task and section exists");

                try {
                    sectionRepository.save(newSection);
                    LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|Section status updated successfully");
                    Iterable<Task> tasks = this.taskRepository.findTaskBySectionId(checkList.getSectionId());
                    tasks.forEach(task -> {
                        task.setSectionId(checkList.getSectionId());
                        task.setStatus("Assigned");
                        historyService.insert(new History(java.time.LocalDateTime.now(), task.getTaskId(), "system", "Assigned" ));
                    });
                    taskRepository.saveAll(tasks);
                    LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|All tasks assigned and new status updated successfully");
                } catch (Exception e) {
                    IssueCheckListResponse response =  new IssueCheckListResponse(500, "Tasks could not be issued");
                    LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|Operation ends in error: task could not be issued" + e.getMessage());
                    return CompletableFuture.completedFuture(response);
                }

                IssueCheckListResponse response = new IssueCheckListResponse(200,"Assignment successful",java.time.LocalDateTime.now());
                LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|Operation complete: Assignment was successful");
                return CompletableFuture.completedFuture(response);

            } else {
                IssueCheckListResponse response = new IssueCheckListResponse(400, "Section does not exist");
                LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|Operation ends in error: Section does not exists|400");
                return CompletableFuture.completedFuture(response);
            }
        } else {
            IssueCheckListResponse response = new IssueCheckListResponse(400, "List does not exist");
            LOGGER.info("--LOGS|" + uuid +"|" +java.time.LocalDateTime.now() +"|Operation ends in error: Task does not exists|400");
            return CompletableFuture.completedFuture(response);
        }
    }
}
