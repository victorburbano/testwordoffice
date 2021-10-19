package com.wordoffice.service;

import com.wordoffice.model.Compra;

import java.io.IOException;
import java.util.List;

public interface ICompraService {

    Compra save(Compra m) throws IOException;

    List<Compra> findByNumeroFactura(Long numeroFactura);

    void deleteByNumeroFactura(Long numeroFactura);

    void endCompra(Long numeroFactura);
}
