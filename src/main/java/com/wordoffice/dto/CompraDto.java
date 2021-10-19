package com.wordoffice.dto;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class CompraDto implements Serializable {

    private Long numeroFactura;

    private String numeroDocumento;

    private Long idProducto;

    private Integer cantidad;

}
