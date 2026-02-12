package org.example.centrosnetapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class ClassSessionResponseDTO {
    private Long id;
    private String course;
    private String subject;
    private String teacher;
    private String room;
    private Integer dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}