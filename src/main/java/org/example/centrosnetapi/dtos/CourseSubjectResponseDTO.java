package org.example.centrosnetapi.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseSubjectResponseDTO {

    private Long id;
    private Long courseId;
    private String courseName;

    private Long subjectId;
    private String subjectName;

    private Double hoursPerWeek;
}