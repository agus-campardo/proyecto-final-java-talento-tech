package com.techlab.biblioteca.service;

import com.techlab.biblioteca.model.Libro;
import com.techlab.biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceJPA implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceJPA(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // Crear Libro
    @Override
    public Libro crearLibro(Libro libro) {
        System.out.println("Creando nuevo libro");
        return this.libroRepository.guardarLibro(libro);
    }

    // Listar libros
    @Override
    public List<Libro> listarLibros(String titulo, String autor) {
        List<Libro> resultado;

        if (titulo != null && !titulo.isEmpty() && autor != null && !autor.isEmpty()) {
            resultado = this.libroRepository.obtenerLibrosPorTituloYAutor(titulo, autor);
        } else if (titulo != null && !titulo.isEmpty()) {
            resultado = this.libroRepository.obtenerLibrosPorTitulo(titulo);
        } else if (autor != null && !autor.isEmpty()) {
            resultado = this.libroRepository.obtenerLibrosPorAutor(autor);
        } else {
            resultado = this.libroRepository.obtenerTodosLosLibros();
        }

        return resultado;
    }

    // Obtener libro por ID
    @Override
    public Libro obtenerLibroPorId(Long id) {
        Optional<Libro> libroOptional = this.libroRepository.buscarLibroPorId(id);
        return libroOptional.orElse(null);
    }

    // Editar libro (PUT, actualiza todo)
    @Override
    public Libro editarLibro(Long id, Libro libroActualizado) {
        Optional<Libro> libroOptional = this.libroRepository.buscarLibroPorId(id);

        if (libroOptional.isPresent()) {
            Libro libroExistente = libroOptional.get();

            // actualizamos todos sus atributos
            libroExistente.setTitulo(libroActualizado.getTitulo());
            libroExistente.setAutor(libroActualizado.getAutor());
            libroExistente.setIsbn(libroActualizado.getIsbn());
            libroExistente.setGenero(libroActualizado.getGenero());

            libroExistente.aumentarStock();
            return libroRepository.guardarLibro(libroExistente);
        }

        return null;
    }

    // Borrar libro
    @Override
    public Libro borrarLibro(Long id) {
        Optional<Libro> libroOptional = this.libroRepository.buscarLibroPorId(id);

        if (libroOptional.isPresent()) {
            Libro libro = libroOptional.get();
            this.libroRepository.borrarLibro(libro);
            return libro;
        }

        return null;
    }

    // Buscar por género
    @Override
    public List<Libro> buscarPorGenero(String genero) {
        return this.libroRepository.obtenerLibrosPorGenero(genero);
    }

    // Buscar solo por título
    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        return this.libroRepository.obtenerLibrosPorTitulo(titulo);
    }

    // Buscar solo por autor
    @Override
    public List<Libro> buscarPorAutor(String autor) {
        return this.libroRepository.obtenerLibrosPorAutor(autor);
    }


    @Override
    public boolean prestarLibro(Long libroId) {
        Optional<Libro> libroOptional = this.libroRepository.buscarLibroPorId(libroId);

        // Si no existe el libro
        if (libroOptional.isEmpty()) {
            return false;
        }

        Libro libro = libroOptional.get();

        // Si no tiene stock
        if (!libro.tieneStock()) {
            return false;
        }

        // Si pasa todas las validaciones, realiza el préstamo
        realizarPrestamo(libro);
        return true;
    }

    private void realizarPrestamo(Libro libro) {
        libro.restarStock();
        libro.aumentarVecesPrestado();
        this.libroRepository.guardarLibro(libro);
    }

    @Override
    public boolean devolverLibro(Long libroId) {
        Optional<Libro> libroOptional =  this.libroRepository.buscarLibroPorId(libroId);
        boolean respuesta;

        if (libroOptional.isEmpty()) {
            System.out.println("⚠️ Intento de devolución - Libro no encontrado ID: " + libroId);
            return false;
        }

        Libro libro = libroOptional.get();
        realizarDevolucion(libro);

        System.out.println("✅ Devolución exitosa - Libro ID: " + libroId + " - Título: " + libro.getTitulo() + " - Nuevo stock: " + libro.getStock());

        return true;
    }


    public void realizarDevolucion(Libro libro) {
        libro.aumentarStock();
        this.libroRepository.guardarLibro(libro);
    }
}