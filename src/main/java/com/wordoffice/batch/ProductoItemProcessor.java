package com.wordoffice.batch;

import com.wordoffice.dto.ProductoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class ProductoItemProcessor implements ItemProcessor<ProductoDto, ProductoDto> {

    private static final Logger log = LoggerFactory.getLogger(ProductoItemProcessor.class);

    @Override
    public ProductoDto process(ProductoDto productoDto) throws Exception {
        if(productoDto.getNombre() == null || productoDto.getNombre().isBlank()
            || productoDto.getMarca() == null || productoDto.getMarca().isBlank()
            || productoDto.getPrecio() == null
            || productoDto.getCantidad() == null
            || productoDto.getEstado() == null || productoDto.getEstado().isBlank()
            || productoDto.getDescuento() == null)
            return null;

        return productoDto;
    }
}
