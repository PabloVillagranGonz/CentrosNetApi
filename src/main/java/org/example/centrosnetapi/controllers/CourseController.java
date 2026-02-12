package org.example.centrosnetapi.controllers;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.CourseRequestDTO;
import org.example.centrosnetapi.dtos.CourseResponseDTO;
import org.example.centrosnetapi.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@CrossOrigin
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public CourseResponseDTO create(@RequestBody CourseRequestDTO dto) {
        return courseService.create(dto);
    }

    @GetMapping
    public List<CourseResponseDTO> getAll() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public CourseResponseDTO getById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @GetMapping("/center/{centerId}")
    public List<CourseResponseDTO> getByCenter(@PathVariable Long centerId) {
        return courseService.findByCenter(centerId);
    }

    @PostMapping("/{courseId}/subjects/{subjectId}")
    public void addSubjectToCourse(
            @PathVariable Long courseId,
            @PathVariable Long subjectId
    ) {
        courseService.addSubjectToCourse(courseId, subjectId);
    }

    @PutMapping("/{id}")
    public CourseResponseDTO update(
            @PathVariable Long id,
            @RequestBody CourseRequestDTO dto
    ) {
        return courseService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }
}