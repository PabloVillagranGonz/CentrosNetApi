package org.example.centrosnetapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendCorreoRequestDTO {
    private Long emisorId;
    private Long destinatarioId;
    private String asunto;
    private String cuerpo;
}