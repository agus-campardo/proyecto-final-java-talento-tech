package com.techlab.biblioteca.repository;

import com.techlab.biblioteca.model.Libro;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("dev")  // Solo se me activa si tengo el perfil "dev"

public class LibroMemRepository implements LibroRepository {

    private static Long nextId = 1L;
    private ArrayList<Libro> libros;

    public LibroMemRepository() {
        this.libros = new ArrayList<>();
    }

    public Libro guardarLibro(Libro libro) {
        asignarId(libro);
        libros.add(libro);
        return libro;
    }

    public void borrarLibro(Libro libro) {
        this.libros.remove(libro);
    }

    public Optional<Libro> buscarLibroPorId(Long id) {
        for (Libro libro : libros) {
            if (Objects.equals(libro.getId(), id)) {
                return Optional.of(libro);
            }
        }
        return Optional.empty();
    }

    public List<Libro> obtenerTodosLosLibros() {
        return new ArrayList<>(this.libros);
    }

    public List<Libro> obtenerLibrosPorTitulo(String titulo) {
        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : this.libros) {
            if (contieneTexto(libro.getTitulo(), titulo)) {
                librosEncontrados.add(libro);
            }
        }
        return librosEncontrados;
    }

    public List<Libro> obtenerLibrosPorAutor(String autor) {
        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : this.libros) {
            if (contieneTexto(libro.getAutor(), autor)) {
                librosEncontrados.add(libro);
            }
        }
        return librosEncontrados;
    }

    public List<Libro> obtenerLibrosPorGenero(String genero) {
        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : this.libros) {
            if (contieneTexto(libro.getGenero(), genero)) {
                librosEncontrados.add(libro);
            }
        }
        return librosEncontrados;
    }

    public List<Libro> obtenerLibrosPorTituloYAutor(String titulo, String autor) {
        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : this.libros) {
            if (contieneTexto(libro.getTitulo(), titulo) && contieneTexto(libro.getAutor(), autor)) {
                librosEncontrados.add(libro);
            }
        }
        return librosEncontrados;
    }

    private boolean contieneTexto(String textoCompleto, String textoBuscado) {
        boolean contiene;

        if (textoBuscado == null || textoBuscado.isEmpty()) {
            contiene = true;
        } else if (textoCompleto == null) {
            contiene = false;
        } else {
            String textoCompletoCambiado = formatoBusqueda(textoCompleto);
            String textoBuscadoCambiado = formatoBusqueda(textoBuscado);
            contiene = textoCompletoCambiado.contains(textoBuscadoCambiado);
        }

        return contiene;
    }

    private String formatoBusqueda(String texto) {
        return texto.trim().toLowerCase();
    }

    private void asignarId(Libro libro) {
        if (libro.getId() == null) {
            libro.setId(nextId);
            nextId++;
        }
    }


}