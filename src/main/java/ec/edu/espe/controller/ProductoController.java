package ec.edu.espe.controller;

import ec.edu.espe.model.Producto;
import ec.edu.espe.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    // Inyección de dependencias por constructor (Requerimiento estricto del taller)
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // 1. GET /api/productos - Retorna todos los productos (HTTP 200)
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.listarProductos();
        return ResponseEntity.status(HttpStatus.OK).body(productos);
    }

    // 2. GET /api/productos/{id} - Retorna un producto por su ID (HTTP 200 o 404 por excepción)
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(producto);
    }

    // 3. POST /api/productos - Registra un nuevo producto (HTTP 201 Created)
    @PostMapping
    public ResponseEntity<Producto> registrarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.registrarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    // 4. DELETE /api/productos/{id} - Elimina un producto existente (HTTP 204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}