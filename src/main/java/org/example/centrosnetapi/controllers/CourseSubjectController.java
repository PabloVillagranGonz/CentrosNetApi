package org.example.centrosnetapi.controllers;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.CourseSubjectRequestDTO;
import org.example.centrosnetapi.dtos.CourseSubjectResponseDTO;
import org.example.centrosnetapi.services.CourseSubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-subjects")
@RequiredArgsConstructor
@CrossOrigin
public class CourseSubjectController {

    private final CourseSubjectService courseSubjectService;

    @PostMapping
    public CourseSubjectResponseDTO assign(@RequestBody CourseSubjectRequestDTO dto) {
        return courseSubjectService.assign(dto);
    }

    @GetMapping("/course/{courseId}")
    public List<CourseSubjectResponseDTO> getByCourse(@PathVariable Long courseId) {
        return courseSubjectService.findByCourse(courseId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courseSubjectService.delete(id);
    }
}