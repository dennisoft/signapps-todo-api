package com.signapps.interview.web.controller;

import com.signapps.interview.business.domain.checklist.CaptureCheckListChange;
import com.signapps.interview.business.domain.checklist.CaptureCheckListChangeResponse;
import com.signapps.interview.business.domain.task.GetTasksResponse;
import com.signapps.interview.business.service.CaptureChecklistChangeService;
import com.signapps.interview.data.entity.Task;
import com.signapps.interview.data.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/todo/tasks")
public class TasksController {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CaptureChecklistChangeService captureChecklistChangeService;

    @GetMapping
    public Iterable<Task> getTasks(){
        return this.taskRepository.findAll();
    }

    @GetMapping("/task/{id}")
    public Optional<Task> getTaskById(@PathVariable(value="id")long taskId) {
        return this.taskRepository.findById(taskId);
    }

    @GetMapping("/section/{id}")
    public ResponseEntity<Iterable<Task>> getTasksBySectionId(@PathVariable(value="id")long sectionId) {
        Iterable<Task> task = this.taskRepository.findTaskBySectionId(sectionId);
        if (StreamSupport.stream(task.spliterator(), false).count() > 0) {
            return ResponseEntity.ok().body(task);
        } else {
            return ResponseEntity.status(404).body(task);
        }
    }

    @GetMapping("/task/user/{user}")
    public ResponseEntity<Iterable<GetTasksResponse>> getTasksByUser(@PathVariable(value="user")String userId) {
        Iterable<GetTasksResponse> tasks = taskRepository.getAllTasksByUserId(userId);
        if (StreamSupport.stream(tasks.spliterator(), false).count() > 0) {
            return ResponseEntity.ok().body(tasks);
        } else {
            return ResponseEntity.status(404).body(tasks);
        }
    }

    @PostMapping("/task/capture-change")
    public @ResponseBody
    ResponseEntity<CaptureCheckListChangeResponse> response(@Valid @RequestBody CaptureCheckListChange body){
        CaptureCheckListChangeResponse taskStatusChange =  captureChecklistChangeService.captureCheckListChange(body).join();
        return ResponseEntity.status(taskStatusChange.getResponseCode()).body(taskStatusChange);
    }
}
