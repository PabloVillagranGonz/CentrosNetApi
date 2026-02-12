package org.example.centrosnetapi.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeachingAssignmentResponseDTO {

    private Long id;

    private Long teacherId;
    private String teacherName;

    private Long subjectId;
    private String subjectName;

    private Long courseId;
    private String courseName;

    private String role;
}