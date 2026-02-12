package org.example.centrosnetapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "class_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Curso
    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    // Asignatura
    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    // Profesor
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    // Aula
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    // Día de la semana (1=Lunes … 7=Domingo)
    private Integer dayOfWeek;

    private LocalTime startTime;
    private LocalTime endTime;
}