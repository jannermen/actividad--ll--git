package com.tienda.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.tienda.model.Producto;
import com.tienda.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository repository;

    @InjectMocks
    private ProductoService service;

    @Test
    void crearProductoConPrecioValido() {
        Producto p = new Producto(null, "Arroz", 5000, 10);
        when(repository.save(p)).thenReturn(p);

        Producto resultado = service.crear(p);

        assertEquals("Arroz", resultado.getNombre());
        verify(repository).save(p);
    }

    @Test
    void noPermitePrecioNegativo() {
        Producto p = new Producto(null, "Arroz", -100, 10);

        assertThrows(IllegalArgumentException.class, () -> service.crear(p));
    }

    @Test
    void venderDescuentaStock() {
        Producto p = new Producto(1L, "Arroz", 5000, 10);
        when(repository.findById(1L)).thenReturn(Optional.of(p));
        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        Producto resultado = service.vender(1L, 3);

        assertEquals(7, resultado.getStock());
    }

    @Test
    void venderConStockInsuficienteLanzaError() {
        Producto p = new Producto(1L, "Arroz", 5000, 2);
        when(repository.findById(1L)).thenReturn(Optional.of(p));

        assertThrows(IllegalArgumentException.class, () -> service.vender(1L, 5));
    }
}