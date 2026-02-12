package org.example.centrosnetapi.controllers;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.TeacherResponseDTO;
import org.example.centrosnetapi.dtos.UserResponseDTO;
import org.example.centrosnetapi.models.User;
import org.example.centrosnetapi.services.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
@CrossOrigin
public class TeacherController {

    private final TeacherService teacherService;

    // ALL TEACHERS
    @GetMapping
    public List<TeacherResponseDTO> getAll() {
        return teacherService.findAllTeachers()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // TEACHERS BY CENTER
    @GetMapping("/center/{centerId}")
    public List<TeacherResponseDTO> getByCenter(@PathVariable Long centerId) {
        return teacherService.findTeachersByCenter(centerId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ASSIGN TO CENTER
    @PutMapping("/{teacherId}/center/{centerId}")
    public TeacherResponseDTO assignToCenter(
            @PathVariable Long teacherId,
            @PathVariable Long centerId
    ) {
        return toDTO(
                teacherService.assignToCenter(teacherId, centerId)
        );
    }

    // REMOVE FROM CENTER
    @DeleteMapping("/{teacherId}/center")
    public void removeFromCenter(@PathVariable Long teacherId) {
        teacherService.removeFromCenter(teacherId);
    }

    private TeacherResponseDTO toDTO(User u) {
        return new TeacherResponseDTO(
                u.getId(),
                u.getNombre(),
                u.getApellidos(),
                u.getEmail(),
                u.getCenter() != null ? u.getCenter().getId() : null
        );
    }

    @GetMapping("/{id}/students")
    public List<UserResponseDTO> getStudents(@PathVariable Long id) {
        return teacherService.getStudentsForTeacher(id);
    }
}
