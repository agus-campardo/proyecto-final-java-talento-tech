package com.techlab.biblioteca.service;

import com.techlab.biblioteca.model.Prestamo;
import java.util.List;

public interface PrestamoService {

    Prestamo crearPrestamo(Long libroId);
    boolean devolverPrestamo(Long prestamoId);
    List<Prestamo> listarTodosLosPrestamos();
    List<Prestamo> listarPrestamosActivos();
    Prestamo obtenerPrestamoPorId(Long id);
    List<Prestamo> obtenerPrestamosPorLibro(Long libroId);

}