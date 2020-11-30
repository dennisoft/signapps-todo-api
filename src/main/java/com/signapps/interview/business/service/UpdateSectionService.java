package com.signapps.interview.business.service;

import com.signapps.interview.data.entity.Section;
import com.signapps.interview.data.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateSectionService {
    @Autowired
    SectionStatusService sectionStatusService;

    @Autowired
    SectionRepository sectionRepository;

    public void updateSection(long sectionId) {
        if (sectionStatusService.isSectionCompleted(sectionId)) {
            Optional<Section> existingSection = sectionRepository.findById(sectionId);
            Section updatedSection = new Section(existingSection.get().getSectionId(), existingSection.get().getListId(), existingSection.get().getSectionName(), "Completed", existingSection.get().getUserId());
            sectionRepository.save(updatedSection);
            System.out.println();
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("+++++++++++++++  Notice - section " + updatedSection.getSectionName() + " has been completed and SMS sent! +++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println();
        }
    }
}
