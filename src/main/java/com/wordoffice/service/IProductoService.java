package com.wordoffice.service;

import com.wordoffice.model.Producto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductoService {

    void save(Producto m);

    List<Producto> findByNombreContaining(String name, Pageable pageable);

    List<Producto> findByPrecioBetween(Long init,Long end, Pageable pageable);

    List<Producto> findByMarca(String marca, Pageable pageable);
}
