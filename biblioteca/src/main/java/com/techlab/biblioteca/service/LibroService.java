package com.techlab.biblioteca.service;

import com.techlab.biblioteca.model.Libro;
import java.util.List;


public interface LibroService {

    Libro crearLibro(Libro libro);
    List<Libro> listarLibros(String titulo, String autor);
    Libro editarLibro(Long id, Libro libro);
    Libro borrarLibro(Long id);
    Libro obtenerLibroPorId(Long id);
    List<Libro> buscarPorTitulo(String titulo);
    List<Libro> buscarPorAutor(String autor);
    List<Libro> buscarPorGenero(String genero);

    // PARA PRÉSTAMOS
    boolean prestarLibro(Long libroId);
    boolean devolverLibro(Long libroId);
}
