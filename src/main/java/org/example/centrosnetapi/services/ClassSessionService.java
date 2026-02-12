package org.example.centrosnetapi.services;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.ClassSessionRequestDTO;
import org.example.centrosnetapi.models.ClassSession;
import org.example.centrosnetapi.models.Enrollment;
import org.example.centrosnetapi.models.User;
import org.example.centrosnetapi.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassSessionService {

    private final ClassSessionRepository classSessionRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final EnrollmentRepository enrollmentRepository;

    public ClassSession create(ClassSessionRequestDTO dto) {

        ClassSession cs = new ClassSession();

        var course = courseRepository.findById(dto.getCourseId()).orElseThrow();
        var subject = subjectRepository.findById(dto.getSubjectId()).orElseThrow();

        cs.setCourse(course);
        cs.setSubject(subject);

        if (dto.getTeacherId() != null) {
            cs.setTeacher(userRepository.findById(dto.getTeacherId()).orElse(null));
        }

        if (dto.getRoomId() != null) {
            cs.setRoom(roomRepository.findById(dto.getRoomId()).orElse(null));
        }

        cs.setDayOfWeek(dto.getDayOfWeek());
        cs.setStartTime(dto.getStartTime());
        cs.setEndTime(dto.getEndTime());

        // 1️⃣ Guardamos la sesión
        ClassSession savedSession = classSessionRepository.save(cs);

        // 2️⃣ MATRICULAR AUTOMÁTICAMENTE A LOS ALUMNOS DEL CURSO
        List<User> students = userRepository.findByCourseId(course.getId());

        for (User student : students) {
            boolean exists = enrollmentRepository
                    .existsByStudentIdAndCourseIdAndSubjectId(
                            student.getId(),
                            course.getId(),
                            subject.getId()
                    );

            if (!exists) {
                Enrollment enrollment = new Enrollment();
                enrollment.setStudent(student);
                enrollment.setCourse(course);
                enrollment.setSubject(subject);
                enrollmentRepository.save(enrollment);
            }
        }

        return savedSession;
    }
    public List<ClassSession> findByCourse(Long courseId) {
        return classSessionRepository.findByCourseIdOrderByDayOfWeekAscStartTimeAsc(courseId);
    }

    public List<ClassSession> findByTeacher(Long teacherId) {
        return classSessionRepository.findByTeacherId(teacherId);
    }

    public List<ClassSession> findByCourseId(Long courseId) {
        return classSessionRepository.findByCourseIdOrderByDayOfWeekAscStartTimeAsc(courseId);
    }

    public List<User> findTeachersForStudent(Long studentId) {
        return classSessionRepository.findTeachersForStudent(studentId);
    }

}
