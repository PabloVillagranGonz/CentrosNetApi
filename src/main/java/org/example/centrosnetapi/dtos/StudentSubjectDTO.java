package org.example.centrosnetapi.dtos;


public record StudentSubjectDTO(
        Long id,
        String subject,
        String teacher,
        String room
) {}