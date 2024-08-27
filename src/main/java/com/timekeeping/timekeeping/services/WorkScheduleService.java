package com.timekeeping.timekeeping.services;

import com.timekeeping.timekeeping.models.WorkSchedule;
import com.timekeeping.timekeeping.repositories.WorkScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkScheduleService {
    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    public List<WorkSchedule> getAllSchedules() {
        return workScheduleRepository.findAll();
    }

    public Optional<WorkSchedule> getScheduleById(int id) {
        return workScheduleRepository.findById(id);
    }

    public List<WorkSchedule> findByFullName(String fullName) {
        return workScheduleRepository.findByFullName("%" + fullName + "%");
    }

    public WorkSchedule saveSchedule(WorkSchedule schedule) {
        return workScheduleRepository.save(schedule);
    }

    public void deleteSchedule(int id) {
        workScheduleRepository.deleteById(id);
    }
}