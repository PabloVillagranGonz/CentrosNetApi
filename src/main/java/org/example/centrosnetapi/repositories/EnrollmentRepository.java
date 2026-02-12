package org.example.centrosnetapi.repositories;

import org.example.centrosnetapi.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByStudentIdAndCourseIdAndSubjectId(
            Long studentId,
            Long courseId,
            Long subjectId
    );

    List<Enrollment> findByStudent_Id(Long studentId);
}