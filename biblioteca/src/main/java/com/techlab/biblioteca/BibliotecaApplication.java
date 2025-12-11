package com.techlab.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);

        System.out.println("\n");
        System.out.println("BIENVENIDO AL SISTEMA DE BIBLIOTECA :D");
        System.out.println("===========================================");
        System.out.println("¿Qué acciones podemos hacer?");
        System.out.println("");
        System.out.println("📚 PARA GESTIONAR LIBROS:");
        System.out.println("   • CREAR nuevo libro         → POST   /libros");
        System.out.println("   • VER todos los libros      → GET    /libros");
        System.out.println("   • BUSCAR por ID             → GET    /libros/{id}");
        System.out.println("   • ACTUALIZAR información    → PUT    /libros/{id}");
        System.out.println("   • ELIMINAR libro            → DELETE /libros/{id}");
        System.out.println("   • PRESTAR rápidamente       → POST   /libros/{id}/prestar");
        System.out.println("");
        System.out.println("📝 PARA REGISTRAR PRÉSTAMOS:");
        System.out.println("   • CREAR préstamo con historial → POST   /prestamos?libroId={id}");
        System.out.println("   • VER préstamos activos        → GET    /prestamos/activos");
        System.out.println("   • DEVOLVER un préstamo         → POST   /prestamos/{id}/devolver");
        System.out.println("");
        System.out.println("🔧 UTILIDADES:");
        System.out.println("   • Base de datos H2: http://localhost:8080/h2-console");
        System.out.println("   • Usuario: 'sa', Contraseña: (vacía)");
        System.out.println("");
        System.out.println("DECISIONES DE DISEÑO:");
        System.out.println("   • La clase 'Libro' representa TÍTULOS, no ejemplares");
        System.out.println("     (ID único por título, más simple que llevar registro de cada ejemplar)");
        System.out.println("   • Los préstamos necesitan libros existentes");
        System.out.println("     (Por eso se crean después (manualmente), usando IDs de libros)");
        System.out.println("===========================================");
    }
}