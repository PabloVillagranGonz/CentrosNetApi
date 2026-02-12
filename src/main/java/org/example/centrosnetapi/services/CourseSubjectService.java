package org.example.centrosnetapi.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.CourseSubjectRequestDTO;
import org.example.centrosnetapi.dtos.CourseSubjectResponseDTO;
import org.example.centrosnetapi.models.Course;
import org.example.centrosnetapi.models.CourseSubject;
import org.example.centrosnetapi.models.Subject;
import org.example.centrosnetapi.repositories.CourseRepository;
import org.example.centrosnetapi.repositories.CourseSubjectRepository;
import org.example.centrosnetapi.repositories.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseSubjectService {

    private final CourseSubjectRepository courseSubjectRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;

    // ================= CREATE =================
    @PreAuthorize("hasRole('ADMIN')")
    public CourseSubjectResponseDTO assign(CourseSubjectRequestDTO dto) {

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "COURSE_NOT_FOUND"));

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "SUBJECT_NOT_FOUND"));

        return courseSubjectRepository
                .findByCourseIdAndSubjectId(course.getId(), subject.getId())
                .map(this::toDTO) // ya existe → devuelvo lo que hay
                .orElseGet(() -> {
                    CourseSubject cs = new CourseSubject();
                    cs.setCourse(course);
                    cs.setSubject(subject);
                    return toDTO(courseSubjectRepository.save(cs));
                });
    }

    // ================= READ =================
    @PreAuthorize("isAuthenticated()")
    public List<CourseSubjectResponseDTO> findByCourse(Long courseId) {
        return courseSubjectRepository.findByCourseId(courseId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ================= DELETE =================
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        courseSubjectRepository.deleteById(id);
    }

    private CourseSubjectResponseDTO toDTO(CourseSubject cs) {
        return CourseSubjectResponseDTO.builder()
                .id(cs.getId())
                .courseId(cs.getCourse().getId())
                .courseName(cs.getCourse().getName())
                .subjectId(cs.getSubject().getId())
                .subjectName(cs.getSubject().getName())
                .build();
    }
}