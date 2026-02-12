package org.example.centrosnetapi.controllers;

import lombok.*;
import org.example.centrosnetapi.dtos.ClassSessionRequestDTO;
import org.example.centrosnetapi.dtos.ClassSessionResponseDTO;
import org.example.centrosnetapi.models.ClassSession;
import org.example.centrosnetapi.services.ClassSessionService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class ClassSessionController {

    private final ClassSessionService service;

    @PostMapping
    public void create(@RequestBody ClassSessionRequestDTO dto) {
        service.create(dto);
    }

    @GetMapping("/course/{courseId}")
    public List<ClassSessionResponseDTO> byCourse(@PathVariable Long courseId) {
        return service.findByCourse(courseId).stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/teacher/{teacherId}")
    public List<ClassSessionResponseDTO> byTeacher(@PathVariable Long teacherId) {
        return service.findByTeacher(teacherId).stream()
                .map(this::toDTO)
                .toList();
    }

    private ClassSessionResponseDTO toDTO(ClassSession cs) {
        return new ClassSessionResponseDTO(
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
        );
    }
}