package com.signapps.interview.data.repository;

import com.signapps.interview.data.entity.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends CrudRepository<List, Long> {
}
