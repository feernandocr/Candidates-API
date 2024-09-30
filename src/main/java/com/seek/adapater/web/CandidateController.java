package com.seek.adapater.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seek.application.service.CandidateService;
import com.seek.domain.model.CandidateEntity;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
    private CandidateService candidateService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<CandidateEntity>> findAll(){
		return ResponseEntity.ok(candidateService.getAllCandidates());
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<CandidateEntity> getCandidateById(@PathVariable Integer id) {
        Optional<CandidateEntity> candidate = candidateService.getCandidateById(id);
        return candidate.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public CandidateEntity registerCandidate(@RequestBody CandidateEntity candidate) {
        return candidateService.registerCandidate(candidate);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CandidateEntity> updateCandidate(@PathVariable Integer id, @RequestBody CandidateEntity candidateDetails) {
        Optional<CandidateEntity> updatedCandidate = candidateService.updateCandidate(id, candidateDetails);
        return updatedCandidate.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Integer id) {
        Optional<CandidateEntity> candidate = candidateService.getCandidateById(id);
        if (candidate.isPresent()) {
            candidateService.deleteCandidate(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
