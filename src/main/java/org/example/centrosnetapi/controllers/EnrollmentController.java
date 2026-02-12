package org.example.centrosnetapi.controllers;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.EnrollmentRequestDTO;
import org.example.centrosnetapi.dtos.EnrollmentResponseDTO;
import org.example.centrosnetapi.services.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@CrossOrigin
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnrollmentResponseDTO enroll(
            @RequestBody EnrollmentRequestDTO dto
    ) {
        return enrollmentService.enroll(dto);
    }

    @GetMapping("/student/{studentId}")
    public List<EnrollmentResponseDTO> getByStudent(
            @PathVariable Long studentId
    ) {
        return enrollmentService.getByStudent(studentId);
    }
}