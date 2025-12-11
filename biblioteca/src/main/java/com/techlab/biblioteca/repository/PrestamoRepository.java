package com.techlab.biblioteca.repository;

import com.techlab.biblioteca.model.Prestamo;
import java.util.List;
import java.util.Optional;

public interface PrestamoRepository {

    Prestamo guardarPrestamo(Prestamo prestamo);
    void eliminarPrestamo(Prestamo prestamo);
    Optional<Prestamo> buscarPrestamoPorId(Long id);
    List<Prestamo> obtenerTodosLosPrestamos();
    List<Prestamo> obtenerPrestamosActivos();
    List<Prestamo> obtenerPrestamosPorLibroId(Long libroId);

}