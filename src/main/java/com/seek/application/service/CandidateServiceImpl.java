package com.seek.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seek.domain.model.CandidateEntity;
import com.seek.domain.repository.CandidateRepository;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;
	
	@Override
	public List<CandidateEntity> getAllCandidates() {
		return candidateRepository.findAll();
	}

	@Override
	public Optional<CandidateEntity> getCandidateById(Integer id) {
		return candidateRepository.findById(id);
	}

	@Override
	public CandidateEntity registerCandidate(CandidateEntity candidate) {
		return candidateRepository.save(candidate);
	}

	@Override
	public Optional<CandidateEntity> updateCandidate(Integer id, CandidateEntity candidateDetails) {
	    return candidateRepository.findById(id).map(candidate -> {
	        if (candidateDetails.getName() != null && !candidateDetails.getName().isEmpty()) {
	            candidate.setName(candidateDetails.getName());
	        }
	        if (candidateDetails.getEmail() != null && !candidateDetails.getEmail().isEmpty()) {
	            candidate.setEmail(candidateDetails.getEmail());
	        }
	        if (candidateDetails.getGender() != null && !candidateDetails.getGender().isEmpty()) {
	            candidate.setGender(candidateDetails.getGender());
	        }
	        if (candidateDetails.getSalary_expected() != null) {
	            candidate.setSalary_expected(candidateDetails.getSalary_expected());
	        }
	        if(candidateDetails.getNationality() != null) {
	        	candidate.setNationality(candidateDetails.getNationality());
	        }
	        return candidateRepository.save(candidate);
	    });
	}

	@Override
	public void deleteCandidate(Integer id) {
        candidateRepository.deleteById(id);
		
	}

}
