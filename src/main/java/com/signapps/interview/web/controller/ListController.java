package com.signapps.interview.web.controller;

import com.signapps.interview.business.domain.checklist.IssueCheckList;
import com.signapps.interview.business.domain.checklist.IssueCheckListResponse;
import com.signapps.interview.business.domain.task.GetHistoryResponse;
import com.signapps.interview.business.service.IssueCheckListService;
import com.signapps.interview.data.entity.List;
import com.signapps.interview.data.repository.HistoryRepository;
import com.signapps.interview.data.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/todo/checklist")
public class ListController {

    @Autowired
    private IssueCheckListService issueCheckListService;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private ListRepository listRepository;

    @GetMapping
    public Iterable<List> getLists() {
        return this.listRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<List> getCheckListById(@PathVariable(value="id")long listId) {
        return this.listRepository.findById(listId);
    }

    @PostMapping(value = "/issue")
    public @ResponseBody
    ResponseEntity<IssueCheckListResponse> response(@Valid @RequestBody IssueCheckList body) {
        IssueCheckListResponse checkListResponse = issueCheckListService.issueCheckList(body).join();
        return ResponseEntity.status(checkListResponse.getStatusCode()).body(checkListResponse);
    }

    @GetMapping("/history/{list}")
    public ResponseEntity<Iterable<GetHistoryResponse>> getChecklistChangeHistory(@PathVariable(value="list")String listId) {
        Iterable<GetHistoryResponse> history = historyRepository.getChecklistHistory();
        if (StreamSupport.stream(history.spliterator(), false).count() > 0) {
            return ResponseEntity.ok().body(history);
        } else {
            return ResponseEntity.status(404).body(history);
        }
    }
}
