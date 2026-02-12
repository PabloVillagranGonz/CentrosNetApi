package org.example.centrosnetapi.services;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.TeachingAssignmentRequestDTO;
import org.example.centrosnetapi.dtos.TeachingAssignmentResponseDTO;
import org.example.centrosnetapi.models.Course;
import org.example.centrosnetapi.models.Subject;
import org.example.centrosnetapi.models.TeachingAssignment;
import org.example.centrosnetapi.models.User;
import org.example.centrosnetapi.repositories.CourseRepository;
import org.example.centrosnetapi.repositories.SubjectRepository;
import org.example.centrosnetapi.repositories.TeachingAssignmentRepository;
import org.example.centrosnetapi.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeachingAssignmentService {

    private final TeachingAssignmentRepository repository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;

    // CREATE
    public TeachingAssignmentResponseDTO assign(
            TeachingAssignmentRequestDTO dto
    ) {

        if (repository.existsByTeacherIdAndSubjectIdAndCourseId(
                dto.getTeacherId(),
                dto.getSubjectId(),
                dto.getCourseId()
        )) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "ASSIGNMENT_ALREADY_EXISTS"
            );
        }

        User teacher = userRepository.findById(dto.getTeacherId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "TEACHER_NOT_FOUND")
                );

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "SUBJECT_NOT_FOUND")
                );

        Course course = null;
        if (dto.getCourseId() != null) {
            course = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND, "COURSE_NOT_FOUND")
                    );
        }

        TeachingAssignment ta = new TeachingAssignment();
        ta.setTeacher(teacher);
        ta.setSubject(subject);
        ta.setCourse(course);
        ta.setRole(dto.getRole() != null ? dto.getRole() : "teacher");

        return toDTO(repository.save(ta));
    }

    // READ BY COURSE (CLAVE PARA FLUTTER)
    public List<TeachingAssignmentResponseDTO> findByCourse(Long courseId) {
        return repository.findByCourseId(courseId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // =========================
    // MAPPER
    // =========================

    private TeachingAssignmentResponseDTO toDTO(TeachingAssignment ta) {
        return TeachingAssignmentResponseDTO.builder()
                .id(ta.getId())
                .teacherId(ta.getTeacher().getId())
                .teacherName(
                        ta.getTeacher().getNombre() + " " +
                                ta.getTeacher().getApellidos()
                )
                .subjectId(ta.getSubject().getId())
                .subjectName(ta.getSubject().getName())
                .courseId(
                        ta.getCourse() != null ? ta.getCourse().getId() : null
                )
                .courseName(
                        ta.getCourse() != null ? ta.getCourse().getName() : null
                )
                .role(ta.getRole())
                .build();
    }
}