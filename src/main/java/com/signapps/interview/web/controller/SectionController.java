package com.signapps.interview.web.controller;

import com.signapps.interview.business.domain.section.GetSectionStatusResponse;
import com.signapps.interview.business.service.SectionStatusService;
import com.signapps.interview.data.entity.Section;
import com.signapps.interview.data.entity.Task;
import com.signapps.interview.data.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/sections")
public class SectionController {
    @Autowired
    private SectionStatusService sectionStatusService;

    @Autowired
    private SectionRepository sectionRepository;

    @GetMapping
    public ResponseEntity<Iterable<Section>> getSections() {
        Iterable<Section> section = this.sectionRepository.findAll();
        if (StreamSupport.stream(section.spliterator(), false).count() > 0) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(section);
        } else {
            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body(section);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Section>> getSectionById(@PathVariable(value="id")long sectionId) {
        Optional<Section> section = this.sectionRepository.findById(sectionId);
        if (section.isPresent()) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(section);
        } else {
            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body(section);
        }
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<GetSectionStatusResponse> getStatus(@PathVariable(value="id")long sectionId) {
        GetSectionStatusResponse sectionStatus = this.sectionStatusService.sectionStatus(sectionId).join();
        if (sectionStatus.getStatus().equals("Unknown")) {
            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body(sectionStatus);
        } else {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(sectionStatus);
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Iterable<Section>> getSectionsByListId(@PathVariable(value="id")long sectionId) { ;
        Iterable<Section> section = this.sectionRepository.getSectionsByListId(sectionId);
        if (StreamSupport.stream(section.spliterator(), false).count() > 0) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(section);
        } else {
            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body(section);
        }
    }



}
