package org.example.centrosnetapi.dtos;

import lombok.*;

@Data
@AllArgsConstructor
public class RoomResponseDTO {
    private Long id;
    private String name;
    private String notes;
    private Long centerId;
}