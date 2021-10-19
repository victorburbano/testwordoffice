package com.wordoffice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductoDto implements Serializable {

    private String nombre;

    private String marca;

    private Long precio;

    private Integer cantidad;

    private String estado;

    private Integer descuento;

}
