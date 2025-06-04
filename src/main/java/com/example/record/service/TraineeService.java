package com.example.record.service;
import com.example.record.entity.Trainee;
import com.example.record.repository.TraineeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
@Service @Validated
public class TraineeService {
    @Autowired private TraineeRepo traineeRepo;
    public Trainee addTrainee(Trainee trainee) { return traineeRepo.save(trainee); }
    public Trainee updateTrainee(Long id, Trainee trainee) { trainee.setId(id); return traineeRepo.save(trainee); }
    public void deleteTrainee(Long id) { traineeRepo.deleteById(id); }
    public Page<Trainee> getAllTrainees(int page, int size) { return traineeRepo.findAll(PageRequest.of(page, size)); }
    public Page<Trainee> searchByName(String name, int page, int size) { return traineeRepo.findByNameContainingIgnoreCase(name, PageRequest.of(page, size)); }
    public Page<Trainee> searchByGroup(String groupName, int page, int size) { return traineeRepo.findByGroupNameContainingIgnoreCase(groupName, PageRequest.of(page, size)); }
    public Trainee getTraineeById(Long id) { return traineeRepo.findById(id).orElseThrow(() -> new RuntimeException("Trainee not found")); }
}
