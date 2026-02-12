package org.example.centrosnetapi.dtos;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ClassSessionRequestDTO {
    private Long courseId;
    private Long subjectId;
    private Long teacherId;
    private Long roomId;
    private Integer dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}