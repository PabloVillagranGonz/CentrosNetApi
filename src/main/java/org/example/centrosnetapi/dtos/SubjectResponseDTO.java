package org.example.centrosnetapi.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectResponseDTO {

    private Long id;
    private String name;
    private String code;
    private String description;
    private Integer durationMinutes;
    private Long centerId;
}