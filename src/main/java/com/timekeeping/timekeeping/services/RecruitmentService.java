package com.timekeeping.timekeeping.services;

import com.timekeeping.timekeeping.models.Recruitment;
import com.timekeeping.timekeeping.repositories.RecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruitmentService {

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    public List<Recruitment> findAll() {
        return recruitmentRepository.findAll();
    }

    public Optional<Recruitment> findById(int recruitmentId) {
        return recruitmentRepository.findById(recruitmentId);
    }

    public Recruitment save(Recruitment recruitment) {
        return recruitmentRepository.save(recruitment);
    }

    public Recruitment update(int recruitmentId, Recruitment updatedRecruitment) {
        Optional<Recruitment> existingRecruitment = recruitmentRepository.findById(recruitmentId);
        if (existingRecruitment.isPresent()) {
            Recruitment recruitment = existingRecruitment.get();

            // Update fields
            recruitment.setJobId(updatedRecruitment.getJobId());
            recruitment.setStatus(updatedRecruitment.getStatus());
            recruitment.setDescription(updatedRecruitment.getDescription());
            recruitment.setStartDate(updatedRecruitment.getStartDate());
            recruitment.setEndDate(updatedRecruitment.getEndDate());
            recruitment.setInterviewDate(updatedRecruitment.getInterviewDate());
            recruitment.setLocation(updatedRecruitment.getLocation());
            recruitment.setName(updatedRecruitment.getName());
            recruitment.setInterviewType(updatedRecruitment.getInterviewType());
            recruitment.setNotes(updatedRecruitment.getNotes());
            recruitment.setAccountId(updatedRecruitment.getAccountId());

            // Save updated recruitment
            try {
                return recruitmentRepository.save(recruitment);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error saving updated recruitment", e);
            }
        } else {
            throw new RuntimeException("Recruitment not found with id: " + recruitmentId);
        }
    }



    public void deleteById(int recruitmentId) {
        recruitmentRepository.deleteById(recruitmentId);
    }


}
