package org.example.centrosnetapi.controllers;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.TeachingAssignmentRequestDTO;
import org.example.centrosnetapi.dtos.TeachingAssignmentResponseDTO;
import org.example.centrosnetapi.services.TeachingAssignmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teaching-assignments")
@RequiredArgsConstructor
@CrossOrigin
public class TeachingAssignmentController {

    private final TeachingAssignmentService service;

    // ASSIGN
    @PostMapping
    public TeachingAssignmentResponseDTO assign(
            @RequestBody TeachingAssignmentRequestDTO dto
    ) {
        return service.assign(dto);
    }

    // GET BY COURSE → dropdown profesores
    @GetMapping("/course/{courseId}")
    public List<TeachingAssignmentResponseDTO> getByCourse(
            @PathVariable Long courseId
    ) {
        return service.findByCourse(courseId);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}