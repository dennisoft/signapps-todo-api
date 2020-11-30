package com.signapps.interview.business.service;

import com.signapps.interview.business.domain.section.GetSectionStatus;
import com.signapps.interview.business.domain.section.GetSectionStatusQueryOutput;
import com.signapps.interview.business.domain.section.GetSectionStatusResponse;
import com.signapps.interview.data.repository.SectionRepository;
import com.signapps.interview.data.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class SectionStatusService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Async
    public CompletableFuture<GetSectionStatusResponse> sectionStatus(long sectionId) {
        int pendingTasks = this.taskRepository.getNumberOfPendingTasksBySection(sectionId);
        GetSectionStatusQueryOutput sectionDetails = sectionRepository.getSectionStatusById(sectionId);
        if (pendingTasks > 0) {
            GetSectionStatusResponse response =  new GetSectionStatusResponse(sectionId, sectionDetails.getSectionName(), sectionDetails.getStatus(), pendingTasks);
            return CompletableFuture.completedFuture(response);
        } else {
            GetSectionStatusResponse response = new GetSectionStatusResponse(sectionId, sectionDetails.getSectionName(), sectionDetails.getStatus());
            return CompletableFuture.completedFuture(response);
        }
    }

    boolean isSectionCompleted(long sectionId) {
        int pendingTasks = this.taskRepository.getNumberOfPendingTasksBySection(sectionId);
        return pendingTasks <= 0;
    }
}
