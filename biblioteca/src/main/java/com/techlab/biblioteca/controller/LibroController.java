package com.techlab.biblioteca.controller;

import com.techlab.biblioteca.model.Libro;
import com.techlab.biblioteca.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // POST: Crear Libro
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.libroService.crearLibro(libro));
    }

    // GET: Obtener todos los libros
    @GetMapping
    public List<Libro> listarLibros(@RequestParam(required = false, defaultValue = "") String titulo, @RequestParam(required = false, defaultValue = "") String autor) {
        return this.libroService.listarLibros(titulo, autor);
    }

    // GET: Obtener por ID
    @GetMapping("/{id}")
    public Libro obtenerLibro(@PathVariable Long id) {
        return this.libroService.obtenerLibroPorId(id);
    }

    // PUT: Editar libro
    @PutMapping("/{id}")
    public Libro editarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return this.libroService.editarLibro(id, libro);
    }

    // DELETE: Borrar libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Libro> borrarLibro(@PathVariable(name = "id") Long libroId) {
        Libro libroEliminado = this.libroService.borrarLibro(libroId);
        if (libroEliminado != null) {
            return ResponseEntity.status(HttpStatus.OK).body(libroEliminado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // ===== Para los PRESTAMOS (métodos para prestar y devolverPrestamo un libro)

    @PostMapping("/{id}/prestar")
    public String prestarLibro(@PathVariable Long id) {
        boolean exito = this.libroService.prestarLibro(id);

        if (exito) {
            return "✅ Libro prestado exitosamente";
        } else {
            return "❌ No se pudo prestar (no existe o sin stock)";
        }
    }

    @PostMapping("/{id}/devolver")
    public String devolverLibro(@PathVariable Long id) {
        boolean exito = this.libroService.devolverLibro(id);

        if (exito) {
            return "✅ Libro devuelto exitosamente";
        } else {
            return "❌ Libro no encontrado";
        }
    }

}

