package org.example.centrosnetapi.repositories;

import org.example.centrosnetapi.models.TeachingAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachingAssignmentRepository
        extends JpaRepository<TeachingAssignment, Long> {

    List<TeachingAssignment> findByCourseId(Long courseId);

    List<TeachingAssignment> findBySubjectId(Long subjectId);

    List<TeachingAssignment> findByTeacherId(Long teacherId);

    boolean existsByTeacherIdAndSubjectIdAndCourseId(
            Long teacherId,
            Long subjectId,
            Long courseId
    );
}