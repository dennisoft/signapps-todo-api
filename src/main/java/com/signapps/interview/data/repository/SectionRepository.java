package com.signapps.interview.data.repository;

import com.signapps.interview.business.domain.section.GetSectionResponse;
import com.signapps.interview.business.domain.section.GetSectionStatusQueryOutput;
import com.signapps.interview.data.entity.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends CrudRepository<Section, Long>{

    @Query("UPDATE Section SET status = :status WHERE sectionId = :section")
    Iterable<Section> updateSectionStatus(@Param("status") String status, @Param("section") long sectionId);

    @Query("SELECT NEW com.signapps.interview.data.entity.Section(s.sectionId, s.listId, s.sectionName, s.status, s.userId) FROM Section s WHERE s.listId = :list")
    Iterable<Section> getSectionsByListId(@Param("list") long lidtId);

    @Query("SELECT NEW com.signapps.interview.business.domain.section.GetSectionStatusQueryOutput(sectionId, sectionName, status) FROM Section WHERE sectionId = :section")
    GetSectionStatusQueryOutput getSectionStatusById(@Param("section") long sectionId);

    @Query("SELECT NEW com.signapps.interview.business.domain.section.GetSectionResponse(s.sectionId, s.sectionName, s.status, s.userId) FROM Section s WHERE s.sectionId = :section AND s.status <> :status")
    Iterable<GetSectionResponse> getSectionStatus(@Param("section") long sectionId, @Param("status") String status);

}
