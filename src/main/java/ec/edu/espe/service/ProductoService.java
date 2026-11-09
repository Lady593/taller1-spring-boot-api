package ec.edu.espe.service;

import ec.edu.espe.model.Producto;
import ec.edu.espe.exception.ProductoInvalidoException;
import ec.edu.espe.exception.ProductoNoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    // Almacenamiento temporal en memoria (Lista)
    private final List<Producto> productos = new ArrayList<>();
    private Long contadorId = 1L;

    // Obtener todos los productos (GET /api/productos)
    public List<Producto> listarProductos() {
        return productos;
    }

    // Buscar producto por ID (GET /api/productos/{id})
    public Producto obtenerPorId(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductoNoEncontradoException("El producto con ID " + id + " no existe."));
    }

    // Registrar un nuevo producto con reglas de negocio (POST /api/productos)
    public Producto registrarProducto(Producto producto) {
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            throw new ProductoInvalidoException("El precio debe ser mayor a cero.");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new ProductoInvalidoException("El stock no puede ser negativo.");
        }

        producto.setId(contadorId++);
        productos.add(producto);
        return producto;
    }

    // Eliminar un producto existente (DELETE /api/productos/{id})
    public void eliminarProducto(Long id) {
        Producto producto = obtenerPorId(id); // Lanza excepcion si no existe
        productos.remove(producto);
    }
}