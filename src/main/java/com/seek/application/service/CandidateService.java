package com.seek.application.service;

import java.util.List;
import java.util.Optional;

import com.seek.domain.model.CandidateEntity;

public interface CandidateService {

	List<CandidateEntity> getAllCandidates();
	Optional<CandidateEntity>getCandidateById(Integer id);
	CandidateEntity registerCandidate(CandidateEntity candidate);
	Optional<CandidateEntity> updateCandidate(Integer id, CandidateEntity candidateDetails);
	void deleteCandidate(Integer id);
}
