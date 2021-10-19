package com.wordoffice.service;

import com.wordoffice.model.Producto;
import com.wordoffice.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository iProductoRepository;

    @Override
    public void save(Producto m) {
        iProductoRepository.save(m);
    }

    @Override
    public List<Producto> findByNombreContaining(String name, Pageable pageable) {
        return iProductoRepository.findByNombreContaining(name, pageable);
    }

    @Override
    public List<Producto> findByPrecioBetween(Long init, Long end, Pageable pageable) {
        return iProductoRepository.findByPrecioBetween(init, end, pageable);
    }

    @Override
    public List<Producto> findByMarca(String marca, Pageable pageable) {
        return iProductoRepository.findByMarca(marca, pageable);
    }

}
