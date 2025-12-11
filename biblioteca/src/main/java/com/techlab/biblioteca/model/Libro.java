package com.techlab.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String isbn;
    private String genero;
    private int stock;              // me llega un stock, va a ir cambiando a medida que se hagan las operaciones
    private int vecesPrestado;      // saber el que más se prestó

    // Constructor vacío (necesario para JPA)
    public Libro() {
        this.stock = 0;
        this.vecesPrestado = 0;
    }

    // Constructor completo
    public Libro(String titulo, String autor, String isbn, String genero, int stock) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.genero = genero;
        this.stock = stock;
        this.vecesPrestado = 0;
    }

    // ==== Para trabajar con los préstamos

    public void prestarLibro() {
        if (puedePrestarse()) {
            this.vecesPrestado++;
            this.stock--;
        }
    }

    public boolean puedePrestarse() {
        return this.stock > 0;
    }

    public void devolverLibro() {
        stock++;
    }

    public boolean tieneStock() {
        return this.stock > 0;
    }

    public void aumentarStock() {
        this.stock++;
    }

    public void restarStock() {
        this.stock--;
    }

    public void aumentarVecesPrestado() {
        this.vecesPrestado++;
    }





}