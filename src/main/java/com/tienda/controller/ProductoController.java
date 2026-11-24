package com.tienda.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.tienda.model.Producto;
import com.tienda.services.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return service.crear(producto);
    }

    @PostMapping("/{id}/vender")
    public Producto vender(@PathVariable Long id, @RequestParam int cantidad) {
        return service.vender(id, cantidad);
    }    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return service.actualizar(id, producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}