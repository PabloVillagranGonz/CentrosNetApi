package org.example.centrosnetapi.services;


import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.CourseRequestDTO;
import org.example.centrosnetapi.dtos.CourseResponseDTO;
import org.example.centrosnetapi.models.Center;
import org.example.centrosnetapi.models.Course;
import org.example.centrosnetapi.models.CourseSubject;
import org.example.centrosnetapi.models.Subject;
import org.example.centrosnetapi.repositories.CenterRepository;
import org.example.centrosnetapi.repositories.CourseRepository;
import org.example.centrosnetapi.repositories.CourseSubjectRepository;
import org.example.centrosnetapi.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CenterRepository centerRepository;
    private final SubjectRepository subjectRepository;
    private final CourseSubjectRepository courseSubjectRepository;

    // CREATE
    public CourseResponseDTO create(CourseRequestDTO dto) {

        Center center = centerRepository.findById(dto.getCenterId())
                .orElseThrow(() -> new RuntimeException("CENTER_NOT_FOUND"));

        Course course = new Course();
        course.setName(dto.getName());
        course.setYear(dto.getYear());
        course.setNotes(dto.getNotes());
        course.setCenter(center);

        course = courseRepository.save(course);

        return toDTO(course);
    }

    // READ ALL
    public List<CourseResponseDTO> findAll() {
        return courseRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // READ BY CENTER
    public List<CourseResponseDTO> findByCenter(Long centerId) {
        return courseRepository.findByCenterId(centerId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // READ BY ID
    public CourseResponseDTO findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("COURSE_NOT_FOUND"));

        return toDTO(course);
    }

    // UPDATE
    public CourseResponseDTO update(Long id, CourseRequestDTO dto) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("COURSE_NOT_FOUND"));

        course.setName(dto.getName());
        course.setYear(dto.getYear());
        course.setNotes(dto.getNotes());

        return toDTO(courseRepository.save(course));
    }

    // DELETE
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    private CourseResponseDTO toDTO(Course course) {
        return new CourseResponseDTO(
                course.getId(),
                course.getName(),
                course.getYear(),
                course.getNotes(),
                course.getCenter() != null ? course.getCenter().getId() : null
        );
    }

    public void addSubjectToCourse(Long courseId, Long subjectId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        CourseSubject cs = CourseSubject.builder()
                .course(course)
                .subject(subject)
                .hoursPerWeek(null) // o valor por defecto
                .build();

        courseSubjectRepository.save(cs);
    }
}
