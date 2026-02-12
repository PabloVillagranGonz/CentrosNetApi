package org.example.centrosnetapi.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instruments")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
}