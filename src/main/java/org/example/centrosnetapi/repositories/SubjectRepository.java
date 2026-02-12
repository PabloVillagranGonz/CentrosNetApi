package org.example.centrosnetapi.repositories;

import org.example.centrosnetapi.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByCenterId(Long centerId);
}