package com.wordoffice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_factura")
    private Long numeroFactura;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "id_producto")
    private Long idProducto;

    private Integer cantidad;

}
