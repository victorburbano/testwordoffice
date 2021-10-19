package com.wordoffice.controller;

import com.wordoffice.model.Producto;
import com.wordoffice.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private IProductoService iProductoService;

    @GetMapping("/version")
    public ResponseEntity<String> getVersion() {
        return ResponseEntity.ok("1.0");
    }

    @GetMapping("/findByNombreContaining")
    public ResponseEntity<List<Producto>> findByNombreContaining(@RequestParam String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(iProductoService.findByNombreContaining(name, pageable));
    }

    @GetMapping("/findByPrecioBetween")
    public ResponseEntity<List<Producto>> findByPrecioBetween(@RequestParam Long init, @RequestParam Long end, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(iProductoService.findByPrecioBetween(init, end, pageable));
    }

    @GetMapping("/findByMarca")
    public ResponseEntity<List<Producto>> findByMarca(@RequestParam String marca, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(iProductoService.findByMarca(marca, pageable));
    }

}
