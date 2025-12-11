package com.techlab.biblioteca.controller;

import com.techlab.biblioteca.model.Prestamo;
import com.techlab.biblioteca.service.PrestamoServiceJPA;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoServiceJPA prestamoService;

    public PrestamoController(PrestamoServiceJPA prestamoService) {
        this.prestamoService = prestamoService;
    }

    // POST: Crear préstamo
    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(@RequestParam Long libroId) {
        Prestamo prestamo = this.prestamoService.crearPrestamo(libroId);
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamo);
    }

    // GET: Obtenemos todos los préstamos
    @GetMapping
    public List<Prestamo> listarPrestamos() {
        return this.prestamoService.listarTodosLosPrestamos();
    }

    // GET: Por ID
    @GetMapping("/{id}")
    public Prestamo obtenerPrestamoPorID(@PathVariable Long id) {
        return this.prestamoService.obtenerPrestamoPorId(id);
    }

    // GET: Préstamos activos
    @GetMapping("/activos")
    // Aquellos que todavía no han sido devueltos
    public List<Prestamo> listarPrestamosActivos() {
        return this.prestamoService.listarPrestamosActivos();
    }

    // POST: Devolver préstamo
    @PostMapping("/{id}/devolver")
    public String devolverPrestamo(@PathVariable Long id) {
        try {
            boolean exito = this.prestamoService.devolverPrestamo(id);
            if (exito) {
                return "✅ Préstamo devuelto exitosamente";
            } else {
                return "❌ No se pudo devolver el préstamo";
            }
        } catch (RuntimeException e) {
            return "❌ Error: " + e.getMessage();
        }
    }

}