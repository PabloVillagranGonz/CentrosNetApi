package org.example.centrosnetapi.repositories;

import org.example.centrosnetapi.models.ClassSession;
import org.example.centrosnetapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {

    List<ClassSession> findByCourseIdOrderByDayOfWeekAscStartTimeAsc(Long courseId);

    List<ClassSession> findByTeacherId(Long teacherId);

    @Query("""
    SELECT DISTINCT cs.teacher
    FROM ClassSession cs
    JOIN Enrollment e
      ON cs.course.id = e.course.id
     AND cs.subject.id = e.subject.id
    WHERE e.student.id = :studentId
      AND cs.teacher IS NOT NULL
    ORDER BY cs.teacher.apellidos, cs.teacher.nombre
""")
    List<User> findTeachersForStudent(@Param("studentId") Long studentId);

    @Query("""
    SELECT DISTINCT u
    FROM ClassSession cs
    JOIN User u ON u.course.id = cs.course.id
    WHERE cs.teacher.id = :teacherId
      AND u.role = 'STUDENT'
    ORDER BY u.apellidos, u.nombre
""")
    List<User> findStudentsForTeacher(@Param("teacherId") Long teacherId);
}