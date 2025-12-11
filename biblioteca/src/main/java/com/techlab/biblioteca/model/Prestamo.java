package com.techlab.biblioteca.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate; // USO LIBRERIA PARA FECHAS

@Entity
@Setter
@Getter
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
    private LocalDate fechaPrestamo;
    private boolean devuelto;

    public Prestamo() {
        this.fechaPrestamo = LocalDate.now();
        this.devuelto = false;
    }

    public Prestamo(Libro libro) {
        this.fechaPrestamo = LocalDate.now();
        this.devuelto = false;
        this.libro = libro;
    }

    public void marcarComoDevuelto() {
        this.devuelto = true;
    }

    public boolean isDevuelto() {
        return this.devuelto;
    }
}
