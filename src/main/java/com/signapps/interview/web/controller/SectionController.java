package com.signapps.interview.web.controller;

import com.signapps.interview.business.domain.section.GetSectionStatusResponse;
import com.signapps.interview.business.service.SectionStatusService;
import com.signapps.interview.data.entity.Section;
import com.signapps.interview.data.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/todo/sections")
public class SectionController {
    @Autowired
    private SectionStatusService sectionStatusService;

    @Autowired
    private SectionRepository sectionRepository;

    @GetMapping
    public Iterable<Section> getSections() {
        return this.sectionRepository.findAll();
    }

    @GetMapping("/section/{id}")
    public Optional<Section> getSectionById(@PathVariable(value="id")long sectionId) {
        return this.sectionRepository.findById(sectionId);
    }

    @GetMapping("/status/{id}")
    public GetSectionStatusResponse getStatus(@PathVariable(value="id")long sectionId) {
        return sectionStatusService.sectionStatus(sectionId).join();
    }

    @GetMapping("/list/{id}")
    public Iterable<Section> getSectionsByListId(@PathVariable(value="id")long sectionId) {
        return sectionRepository.getSectionsByListId(sectionId);
    }



}
