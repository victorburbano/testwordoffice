package com.wordoffice.controller;

import com.wordoffice.model.Compra;
import com.wordoffice.service.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private ICompraService iCompraService;

    @PostMapping
    public ResponseEntity<Compra> save(@RequestBody Compra m) throws IOException {
        return ResponseEntity.ok(iCompraService.save(m));
    }

    @GetMapping("/findByNumeroFactura")
    public ResponseEntity<List<Compra>> findByNumeroFactura(@RequestParam Long numeroFactura) {
        return ResponseEntity.ok(iCompraService.findByNumeroFactura(numeroFactura));
    }

    @DeleteMapping("/deleteByNumeroFactura")
    public ResponseEntity<Void> deleteByNumeroFactura(@RequestParam Long numeroFactura) {
        iCompraService.deleteByNumeroFactura(numeroFactura);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<Void> endCompra(@RequestParam Long numeroFactura) {
        iCompraService.endCompra(numeroFactura);
        return ResponseEntity.ok().build();
    }
}
