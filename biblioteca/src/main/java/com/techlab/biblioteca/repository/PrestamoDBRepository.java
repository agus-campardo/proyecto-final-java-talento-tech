package com.techlab.biblioteca.repository;

import com.techlab.biblioteca.model.Prestamo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("local")
public class PrestamoDBRepository implements PrestamoRepository {

    private final PrestamoRepositoryJPA prestamoRepositoryJPA;

    public PrestamoDBRepository(PrestamoRepositoryJPA prestamoRepositoryJPA) {
        this.prestamoRepositoryJPA = prestamoRepositoryJPA;
    }

    public Prestamo guardarPrestamo(Prestamo prestamo) {
        return prestamoRepositoryJPA.save(prestamo);
    }

    public void eliminarPrestamo(Prestamo prestamo) {
        prestamoRepositoryJPA.delete(prestamo);
    }

    public Optional<Prestamo> buscarPrestamoPorId(Long id) {
        return prestamoRepositoryJPA.findById(id);
    }

    public List<Prestamo> obtenerTodosLosPrestamos() {
        return prestamoRepositoryJPA.findAll();
    }

    public List<Prestamo> obtenerPrestamosActivos() {
        return prestamoRepositoryJPA.findPrestamosActivos();
    }

    public List<Prestamo> obtenerPrestamosPorLibroId(Long libroId) {
        return prestamoRepositoryJPA.findByLibroId(libroId);
    }

}