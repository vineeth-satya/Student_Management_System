package com.example.record.repository;
import com.example.record.entity.Trainee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TraineeRepo extends JpaRepository<Trainee, Long> {
    Page<Trainee> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Trainee> findByGroupNameContainingIgnoreCase(String groupName, Pageable pageable);
}
