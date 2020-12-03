package com.signapps.interview.web.controller;

import com.google.gson.Gson;
import com.signapps.interview.business.domain.checklist.CaptureCheckListChange;
import com.signapps.interview.business.domain.checklist.CaptureCheckListChangeResponse;
import com.signapps.interview.business.domain.task.GetTasksResponse;
import com.signapps.interview.business.service.CaptureChecklistChangeService;
import com.signapps.interview.data.entity.Task;
import com.signapps.interview.data.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;



@RestController
@RequestMapping("/tasks")
public class TasksController {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CaptureChecklistChangeService captureChecklistChangeService;

    @GetMapping
    public ResponseEntity<Iterable<Task>> getTasks(){
        Iterable<Task> task = this.taskRepository.findAll();
        if (StreamSupport.stream(task.spliterator(), false).count() > 0) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(task);
        } else {
            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body(task);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable(value="id")long taskId) {
        Optional<Task> task = this.taskRepository.findById(taskId);
        if (task.isPresent()) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(task);
        } else {
            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body(task);
        }
    }

    @GetMapping("/section/{id}")
    public ResponseEntity<Iterable<Task>> getTasksBySectionId(@PathVariable(value="id")long sectionId) {
        Iterable<Task> task = this.taskRepository.findTaskBySectionId(sectionId);
        if (StreamSupport.stream(task.spliterator(), false).count() > 0) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(task);
        } else {
            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body(task);
        }
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<Iterable<GetTasksResponse>> getTasksByUser(@PathVariable(value="user")String userId) {
        Iterable<GetTasksResponse> tasks = taskRepository.getAllTasksByUserId(userId);
        if (StreamSupport.stream(tasks.spliterator(), false).count() > 0) {
            return ResponseEntity.ok().body(tasks);
        } else {
            return ResponseEntity.status(404).body(tasks);
        }
    }

    @PostMapping("/capture")
    public @ResponseBody
    ResponseEntity<CaptureCheckListChangeResponse> response(@Valid @RequestBody CaptureCheckListChange body){
        CaptureCheckListChangeResponse taskStatusChange =  captureChecklistChangeService.captureCheckListChange(body).join();
        return ResponseEntity.status(taskStatusChange.getResponseCode()).contentType(MediaType.APPLICATION_JSON).body(taskStatusChange);
    }
}