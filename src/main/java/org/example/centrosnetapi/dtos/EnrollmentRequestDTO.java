package org.example.centrosnetapi.dtos;

import lombok.Data;

@Data
public class EnrollmentRequestDTO {
    private Long studentId;
    private Long subjectId;
    private Long courseId;
}