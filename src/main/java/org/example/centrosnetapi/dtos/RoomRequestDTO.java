package org.example.centrosnetapi.dtos;

import lombok.Data;

@Data
public class RoomRequestDTO {
    private String name;
    private String notes;
    private Long centerId;
}