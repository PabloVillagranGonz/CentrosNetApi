package org.example.centrosnetapi.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rooms",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "center_id"})
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String notes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "center_id")
    private Center center;
}