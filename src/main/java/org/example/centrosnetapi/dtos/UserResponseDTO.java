package org.example.centrosnetapi.dtos;

import lombok.*;
import org.example.centrosnetapi.models.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;

    private String nombre;
    private String apellidos;
    private String email;
    private Role role;

    private String token;

    private String phone;
    private String dni;

    private Long centerId;
    private String centerName;

    private Long instrumentId;
    private String instrumentName;

    private Long courseId;
    private String courseName;
}