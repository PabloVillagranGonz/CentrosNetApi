package org.example.centrosnetapi.controllers;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.*;
import org.example.centrosnetapi.models.Course;
import org.example.centrosnetapi.models.User;
import org.example.centrosnetapi.repositories.UserRepository;
import org.example.centrosnetapi.services.ClassSessionService;
import org.example.centrosnetapi.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin
public class StudentController {

    private final StudentService studentService;
    private final ClassSessionService classSessionService;
    private final UserRepository userRepository;

    // 🔹 PROFESORES DEL ALUMNO (DESDE CLASS_SESSION)
    @GetMapping("/{id}/teachers")
    public List<UserResponseDTO> getTeachers(@PathVariable Long id) {
        return classSessionService.findTeachersForStudent(id)
                .stream()
                .map(u -> UserResponseDTO.builder()
                        .id(u.getId())
                        .nombre(u.getNombre())
                        .apellidos(u.getApellidos())
                        .email(u.getEmail())
                        .role(u.getRole())
                        .centerId(
                                u.getCenter() != null ? u.getCenter().getId() : null
                        )
                        .instrumentId(
                                u.getInstrument() != null ? u.getInstrument().getId() : null
                        )
                        .build()
                )
                .toList();
    }
    // 🔹 ASIGNATURAS DEL ALUMNO
    @GetMapping("/{id}/subjects")
    public List<StudentSubjectDTO> getStudentSubjects(@PathVariable Long id) {

        User student = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));

        if (student.getCourse() == null) {
            return List.of();
        }

        return classSessionService
                .findByCourseId(student.getCourse().getId())
                .stream()
                .map(cs -> new StudentSubjectDTO(
                        cs.getSubject().getId(),
                        cs.getSubject().getName(),
                        cs.getTeacher() != null
                                ? cs.getTeacher().getNombre() + " " + cs.getTeacher().getApellidos()
                                : "No asignado",
                        cs.getRoom() != null
                                ? cs.getRoom().getName()
                                : "No asignada"
                ))
                .distinct()
                .toList();
    }

    // 🔹 CURSOS DEL ALUMNO (opcional)
    @GetMapping("/{id}/courses")
    public List<CourseResponseDTO> getCourses(@PathVariable Long id) {
        return studentService.getCoursesForStudent(id);
    }

    @GetMapping("/{studentId}/schedule")
    public List<ClassSessionResponseDTO> getSchedule(
            @PathVariable Long studentId
    ) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));

        if (student.getCourse() == null) {
            return List.of();
        }

        return classSessionService
                .findByCourseId(student.getCourse().getId())
                .stream()
                .map(cs -> new ClassSessionResponseDTO(
                        cs.getId(),
                        cs.getCourse().getName(),
                        cs.getSubject().getName(),
                        cs.getTeacher() != null
                                ? cs.getTeacher().getNombre() + " " + cs.getTeacher().getApellidos()
                                : null,
                        cs.getRoom() != null ? cs.getRoom().getName() : null,
                        cs.getDayOfWeek(),
                        cs.getStartTime(),
                        cs.getEndTime()
                ))
                .toList();
    }

    @GetMapping("/{id}/course")
    public CourseResponseDTO getCourse(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));

        if (user.getCourse() == null) {
            return null;
        }

        Course c = user.getCourse();

        return new CourseResponseDTO(
                c.getId(),
                c.getName(),
                c.getYear(),
                c.getNotes(),
                c.getCenter() != null ? c.getCenter().getId() : null
        );
    }
}