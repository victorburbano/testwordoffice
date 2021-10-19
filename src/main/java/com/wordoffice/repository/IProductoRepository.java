package com.wordoffice.repository;

import com.wordoffice.model.Producto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreContaining(String name, Pageable pageable);

    List<Producto> findByPrecioBetween(Long init,Long end, Pageable pageable);

    List<Producto> findByMarca(String marca, Pageable pageable);

}
