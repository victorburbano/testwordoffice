package com.wordoffice.repository;

import com.wordoffice.model.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICompraRepository extends CrudRepository<Compra, Long> {

    List<Compra> findByNumeroFactura(Long numeroFactura);

    void deleteByNumeroFactura(Long numeroFactura);

}
