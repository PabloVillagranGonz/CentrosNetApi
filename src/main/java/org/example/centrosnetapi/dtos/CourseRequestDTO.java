package org.example.centrosnetapi.dtos;

import lombok.Data;

@Data
public class CourseRequestDTO {
    private String name;
    private Integer year;
    private Long centerId;
    private String notes;
}