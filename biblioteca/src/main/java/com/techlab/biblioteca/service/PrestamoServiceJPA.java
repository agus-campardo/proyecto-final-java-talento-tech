package com.techlab.biblioteca.service;

import com.techlab.biblioteca.model.Libro;
import com.techlab.biblioteca.model.Prestamo;
import com.techlab.biblioteca.repository.LibroRepository;
import com.techlab.biblioteca.repository.PrestamoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceJPA implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;

    public PrestamoServiceJPA(PrestamoRepository prestamoRepository, LibroRepository libroRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
    }

    public Prestamo crearPrestamo(Long libroId) {
        Optional<Libro> libroOptional = libroRepository.buscarLibroPorId(libroId);

        if (libroOptional.isEmpty()) {
            throw new RuntimeException("Libro no encontrado");
        }

        Libro libro = libroOptional.get();

        if (!libro.tieneStock()) {
            throw new RuntimeException("No hay stock disponible");
        }

        Prestamo prestamo = new Prestamo(libro);

        // Actualizamos el libro
        libro.prestarLibro();
        libroRepository.guardarLibro(libro);

        return prestamoRepository.guardarPrestamo(prestamo);
    }


    public boolean devolverPrestamo(Long prestamoId) {
        Optional<Prestamo> prestamoOptional = prestamoRepository.buscarPrestamoPorId(prestamoId);

        if (prestamoOptional.isEmpty()) {
            throw new RuntimeException("Préstamo no encontrado");
        }

        Prestamo prestamo = prestamoOptional.get();

        if (prestamo.isDevuelto()) {
            throw new RuntimeException("Ya fue devuelto");
        }

        prestamo.marcarComoDevuelto();

        // Devolver libro
        Libro libro = prestamo.getLibro();
        libro.devolverLibro();
        libroRepository.guardarLibro(libro);

        this.prestamoRepository.guardarPrestamo(prestamo);
        return true;
    }

    public List<Prestamo> listarTodosLosPrestamos() {
        return prestamoRepository.obtenerTodosLosPrestamos();
    }

    @Override
    public List<Prestamo> listarPrestamosActivos() {
        return prestamoRepository.obtenerPrestamosActivos();
    }

    @Override
    public Prestamo obtenerPrestamoPorId(Long id) {
        return prestamoRepository.buscarPrestamoPorId(id).orElse(null);
    }

    @Override
    public List<Prestamo> obtenerPrestamosPorLibro(Long libroId) {
        return prestamoRepository.obtenerPrestamosPorLibroId(libroId);
    }

}