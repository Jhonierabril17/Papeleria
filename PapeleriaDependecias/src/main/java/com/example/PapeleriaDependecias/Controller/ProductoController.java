package com.example.PapeleriaDependecias.Controller;

import com.example.PapeleriaDependecias.Model.Producto;
import com.example.PapeleriaDependecias.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Api/Producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(Map.of("mensaje", "Lista de productos obtenida", "productos", productoService.findAll()));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Producto producto) {
        try {
            Producto guardado = productoService.save(producto);
            return ResponseEntity.status(201).body(Map.of("mensaje", "Producto guardado con éxito", "producto", guardado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        if (producto != null) {
            return ResponseEntity.ok().body(Map.of("mensaje", "Producto encontrado", "producto", producto));
        } else {
            return ResponseEntity.status(404).body(Map.of("mensaje", "Producto no encontrado"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Producto datosActualizados) {
        Producto actualizado = productoService.updateById(id, datosActualizados);
        if (actualizado != null) {
            return ResponseEntity.ok().body(Map.of("mensaje", "Producto actualizado", "producto", actualizado));
        } else {
            return ResponseEntity.status(404).body(Map.of("mensaje", "No se encontró el producto a actualizar"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        boolean eliminado = productoService.deleteById(id);
        if (eliminado) {
            return ResponseEntity.ok().body(Map.of("mensaje", "Producto eliminado correctamente"));
        } else {
            return ResponseEntity.status(404).body(Map.of("mensaje", "No se encontró el producto a eliminar"));
        }
    }

    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<?> buscarProductosPorProveedor(@PathVariable Long idProveedor) {
        List<Producto> productos = productoService.buscarPorProveedor(idProveedor);

        if (productos.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("mensaje", "Este proveedor no tiene productos registrados"));
        }

        return ResponseEntity.ok(Map.of("mensaje", "Productos del proveedor", "productos", productos));
    }
}
