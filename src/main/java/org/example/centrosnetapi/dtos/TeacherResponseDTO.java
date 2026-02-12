package org.example.centrosnetapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherResponseDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private Long centerId;
}