package com.seek.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.seek.application.service.CandidateServiceImpl;
import com.seek.domain.model.CandidateEntity;
import com.seek.domain.repository.CandidateRepository;

public class CandidateServiceTest {

	@Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidateServiceImpl candidateService;

    private CandidateEntity candidate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        candidate = new CandidateEntity();
        candidate.setId(1);
        candidate.setName("John Doe");
        candidate.setEmail("john.doe@example.com");
        candidate.setGender("Male");
        candidate.setSalary_expected((double) 60000);
        candidate.setNationality("American");
    }

    @Test
    public void testGetAllCandidates() {
        List<CandidateEntity> candidates = Arrays.asList(candidate);
        when(candidateRepository.findAll()).thenReturn(candidates);

        List<CandidateEntity> result = candidateService.getAllCandidates();
        assertEquals(1, result.size());
        verify(candidateRepository, times(1)).findAll();
    }

    @Test
    public void testGetCandidateById() {
        when(candidateRepository.findById(1)).thenReturn(Optional.of(candidate));

        Optional<CandidateEntity> result = candidateService.getCandidateById(1);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(candidateRepository, times(1)).findById(1);
    }

    @Test
    public void testRegisterCandidate() {
        when(candidateRepository.save(candidate)).thenReturn(candidate);

        CandidateEntity result = candidateService.registerCandidate(candidate);
        assertEquals("John Doe", result.getName());
        verify(candidateRepository, times(1)).save(candidate);
    }

    @Test
    public void testUpdateCandidate() {
        when(candidateRepository.findById(1)).thenReturn(Optional.of(candidate));
        when(candidateRepository.save(candidate)).thenReturn(candidate);

        Optional<CandidateEntity> result = candidateService.updateCandidate(1, candidate);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(candidateRepository, times(1)).findById(1);
        verify(candidateRepository, times(1)).save(candidate);
    }

    @Test
    public void testDeleteCandidate() {
        doNothing().when(candidateRepository).deleteById(1);

        candidateService.deleteCandidate(1);
        verify(candidateRepository, times(1)).deleteById(1);
    }
}
