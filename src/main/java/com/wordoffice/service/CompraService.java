package com.wordoffice.service;

import com.wordoffice.model.Compra;
import com.wordoffice.model.Producto;
import com.wordoffice.repository.ICompraRepository;
import com.wordoffice.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class CompraService implements ICompraService {

    @Autowired
    private ICompraRepository iCompraRepository;

    @Autowired
    private IProductoRepository iProductoRepository;

    @Override
    public Compra save(Compra m) throws IOException {
        Producto producto = iProductoRepository.findById(m.getIdProducto()).orElseThrow();
        if(producto.getCantidad() < m.getCantidad())
            throw new IOException("Validacion de cantidad de productos");

        return iCompraRepository.save(m);
    }

    @Override
    public List<Compra> findByNumeroFactura(Long numeroFactura) {
        return iCompraRepository.findByNumeroFactura(numeroFactura);
    }

    @Transactional
    @Override
    public void deleteByNumeroFactura(Long numeroFactura) {
        iCompraRepository.deleteByNumeroFactura(numeroFactura);
    }

    @Override
    public void endCompra(Long numeroFactura) {
        List<Compra> compraList = iCompraRepository.findByNumeroFactura(numeroFactura);
        compraList.forEach(e -> {
            Producto p = iProductoRepository.findById(e.getIdProducto()).orElseThrow();
            p.setCantidad(p.getCantidad() - e.getCantidad());
            iProductoRepository.save(p);
        });
    }
}
