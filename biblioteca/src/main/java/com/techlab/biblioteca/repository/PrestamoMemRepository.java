package com.techlab.biblioteca.repository;

import com.techlab.biblioteca.model.Prestamo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Profile("dev") // Solo podemos accedeer aquó con "dev"
public class PrestamoMemRepository implements PrestamoRepository {

    private static Long nextId = 1L;
    private final ArrayList<Prestamo> prestamos;

    public PrestamoMemRepository() {
        this.prestamos = new ArrayList<>();
    }

    public Prestamo guardarPrestamo(Prestamo prestamo) {
        asignarId(prestamo);
        prestamos.add(prestamo);
        return prestamo;
    }

    public void eliminarPrestamo(Prestamo prestamo) {
        this.prestamos.remove(prestamo);
    }

    public Optional<Prestamo> buscarPrestamoPorId(Long id) {
        for (Prestamo prestamo : prestamos) {
            if (Objects.equals(prestamo.getId(), id)) {
                return Optional.of(prestamo);
            }
        }
        return Optional.empty();
    }

    public List<Prestamo> obtenerTodosLosPrestamos() {
        return new ArrayList<>(this.prestamos);
    }

    public List<Prestamo> obtenerPrestamosActivos() {
        ArrayList<Prestamo> activos = new ArrayList<>();
        for (Prestamo prestamo : this.prestamos) {
            if (!prestamo.isDevuelto()) {
                activos.add(prestamo);
            }
        }
        return activos;
    }

    public List<Prestamo> obtenerPrestamosPorLibroId(Long libroId) {
        ArrayList<Prestamo> prestamosDelLibro = new ArrayList<>();
        for (Prestamo prestamo : this.prestamos) {
            if (prestamo.getLibro() != null &&
                    Objects.equals(prestamo.getLibro().getId(), libroId)) {
                prestamosDelLibro.add(prestamo);
            }
        }
        return prestamosDelLibro;
    }

    private void asignarId(Prestamo prestamo) {
        if (prestamo.getId() == null) {
            prestamo.setId(nextId);
            nextId++;
        }
    }

}