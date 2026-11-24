package com.tienda.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tienda.model.Producto;
import com.tienda.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public Producto crear(Producto producto) {
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        return repository.save(producto);
    }

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto vender(Long id, int cantidad) {
        Producto producto = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        if (producto.getStock() < cantidad) {
            throw new IllegalArgumentException("Stock insuficiente");
        }

        producto.setStock(producto.getStock() - cantidad);
        return repository.save(producto);
    }    public Producto actualizar(Long id, Producto datos) {
        Producto p = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        p.setNombre(datos.getNombre());
        p.setPrecio(datos.getPrecio());
        p.setStock(datos.getStock());
        return repository.save(p);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}