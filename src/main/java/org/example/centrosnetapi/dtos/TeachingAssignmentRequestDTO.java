package org.example.centrosnetapi.dtos;

import lombok.Data;

@Data
public class TeachingAssignmentRequestDTO {

    private Long teacherId;
    private Long subjectId;
    private Long courseId; // puede ser null
    private String role;
}