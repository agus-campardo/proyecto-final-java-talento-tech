package com.techlab.biblioteca.repository;

import com.techlab.biblioteca.model.Libro;
import java.util.List;
import java.util.Optional;

public interface LibroRepository {

    Libro guardarLibro(Libro libro);
    void borrarLibro(Libro libro);
    Optional<Libro> buscarLibroPorId(Long id);
    List<Libro> obtenerTodosLosLibros();
    List<Libro> obtenerLibrosPorTitulo(String titulo);
    List<Libro> obtenerLibrosPorAutor(String autor);
    List<Libro> obtenerLibrosPorGenero(String genero);
    List<Libro> obtenerLibrosPorTituloYAutor(String titulo, String autor);

}