package org.example.centrosnetapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseResponseDTO {
    private Long id;
    private String name;
    private Integer year;
    private String notes;
    private Long centerId;
}
