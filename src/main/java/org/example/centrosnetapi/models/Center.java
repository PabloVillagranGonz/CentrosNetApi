package org.example.centrosnetapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "centers")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String phone;
    private String email;
    private String website;

    @Column(name = "opening_hours")
    private String openingHours;

    private String address;

    @Column(name = "postal_code")
    private String postalCode;

    private String town;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}