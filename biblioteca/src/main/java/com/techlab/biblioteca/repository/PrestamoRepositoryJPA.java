package com.techlab.biblioteca.repository;

import com.techlab.biblioteca.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepositoryJPA extends JpaRepository<Prestamo, Long> {

    List<Prestamo> findByDevuelto(boolean devuelto);
    List<Prestamo> findByLibroId(Long libroId);
    default List<Prestamo> findPrestamosActivos() {
        return findByDevuelto(false);
    }

}