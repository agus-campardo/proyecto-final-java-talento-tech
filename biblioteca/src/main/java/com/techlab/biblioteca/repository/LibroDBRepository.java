package com.techlab.biblioteca.repository;

import com.techlab.biblioteca.model.Libro;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("local") // Solo vengo acá si con el parfil "local"
public class LibroDBRepository implements LibroRepository {

    private LibroRepositoryJPA libroRepositoryJPA;

    public LibroDBRepository(LibroRepositoryJPA libroRepositoryJPA) {
        this.libroRepositoryJPA = libroRepositoryJPA;
    }

    @Override
    public Libro guardarLibro(Libro libro) {
        return this.libroRepositoryJPA.save(libro);
    }

    @Override
    public void borrarLibro(Libro libro) {
        this.libroRepositoryJPA.delete(libro);
    }

    @Override
    public Optional<Libro> buscarLibroPorId(Long id) {
        return this.libroRepositoryJPA.findById(id);
    }

    @Override
    public List<Libro> obtenerTodosLosLibros() {
        return this.libroRepositoryJPA.findAll();
    }

    @Override
    public List<Libro> obtenerLibrosPorTitulo(String titulo) {
        return this.libroRepositoryJPA.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public List<Libro> obtenerLibrosPorAutor(String autor) {
        return this.libroRepositoryJPA.findByAutorContainingIgnoreCase(autor);
    }

    @Override
    public List<Libro> obtenerLibrosPorGenero(String genero) {
        return this.libroRepositoryJPA.findByGeneroContainingIgnoreCase(genero);
    }

    @Override
    public List<Libro> obtenerLibrosPorTituloYAutor(String titulo, String autor) {
        return this.libroRepositoryJPA.findByTituloContainingIgnoreCaseAndAutorContainingIgnoreCase(titulo, autor);
    }
}