package org.example.centrosnetapi.dtos;

import lombok.Data;

@Data
public class CenterResponseDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String website;
    private String openingHours;
    private String address;
    private String postalCode;
    private String town;
}