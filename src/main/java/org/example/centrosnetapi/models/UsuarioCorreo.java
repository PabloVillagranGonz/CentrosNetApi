package org.example.centrosnetapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios_correos")
@Getter
@Setter
public class UsuarioCorreo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================
    // RELACIONES
    // =========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "correo_id", nullable = false)
    private Correo correo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private User usuario;

    // =========================
    // ESTADO DEL CORREO
    // =========================

    @Column(nullable = false)
    private Boolean leido = false;

    @Column(nullable = false)
    private Boolean eliminado = false;

    @Column(nullable = false)
    private Boolean archivado = false;

    // =========================
    // FECHAS
    // =========================

    @Column(name = "fecha_leido")
    private LocalDateTime fechaLeido;

    @Column(name = "fecha_eliminado")
    private LocalDateTime fechaEliminado;
}