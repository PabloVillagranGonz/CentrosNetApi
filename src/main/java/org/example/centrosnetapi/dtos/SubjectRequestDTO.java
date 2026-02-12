package org.example.centrosnetapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubjectRequestDTO {

    private String name;
    private String code;
    private String description;
    private Integer durationMinutes;
    private Long centerId;
}