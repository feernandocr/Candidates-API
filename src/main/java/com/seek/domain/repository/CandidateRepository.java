package com.seek.domain.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.seek.domain.model.CandidateEntity;

@Repository
public interface CandidateRepository extends ListCrudRepository<CandidateEntity, Integer> {

}
