package com.wordoffice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String marca;

    private Long precio;

    private Integer cantidad;

    private String estado;

    private Integer descuento;

    public Producto() {
        super();
    }

    public Producto(Long id, String nombre, String marca) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
    }

    public Producto(Long id, String nombre, String marca, Long precio, Integer cantidad, String estado, Integer descuento) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.cantidad = cantidad;
        this.estado = estado;
        this.descuento = descuento;
    }
}
