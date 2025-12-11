package com.techlab.biblioteca.repository;

import com.techlab.biblioteca.model.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositoryJPA extends JpaRepository<Libro,Long>{

    // Métodos que String ya tiene
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    List<Libro> findByAutorContainingIgnoreCase(String autor);
    List<Libro> findByGeneroContainingIgnoreCase(String genero);
    List<Libro> findByTituloContainingIgnoreCaseAndAutorContainingIgnoreCase(String titulo, String autor);
    List<Libro> findByIsbn(String isbn);
}
