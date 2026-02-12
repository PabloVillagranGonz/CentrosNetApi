package org.example.centrosnetapi.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "course_subjects",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"course_id", "subject_id"})
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    // =========================
    // DATOS EXTRA
    // =========================

    @Column(name = "hours_per_week")
    private Double hoursPerWeek;
}