package com.example.PapeleriaDependecias.Controller;


import com.example.PapeleriaDependecias.Model.Proveedor;
import com.example.PapeleriaDependecias.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Api/Proveedor")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(
                Map.of("mensaje", "Lista de proveedores obtenida", "proveedores", proveedorService.findAll())
        );
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Proveedor proveedor) {
        try {
            Proveedor guardado = proveedorService.save(proveedor);
            return ResponseEntity.status(201).body(
                    Map.of("mensaje", "Proveedor guardado correctamente", "proveedor", guardado)
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.findById(id);
        if (proveedor != null) {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Proveedor encontrado", "proveedor", proveedor)
            );
        } else {
            return ResponseEntity.status(404).body(Map.of("mensaje", "Proveedor no encontrado"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        Proveedor actualizado = proveedorService.updateById(id, proveedor);
        if (actualizado != null) {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Proveedor actualizado", "proveedor", actualizado)
            );
        } else {
            return ResponseEntity.status(404).body(Map.of("mensaje", "Proveedor no encontrado para actualizar"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        boolean eliminado = proveedorService.deleteById(id);
        if (eliminado) {
            return ResponseEntity.ok().body(Map.of("mensaje", "Proveedor eliminado correctamente"));
        } else {
            return ResponseEntity.status(404).body(Map.of("mensaje", "Proveedor no encontrado para eliminar"));
        }
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> buscarProveedorPorNombre(@PathVariable String nombre) {
        List<Proveedor> proveedores = proveedorService.buscarPorNombre(nombre);

        if (proveedores.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("mensaje", "No se encontraron proveedores con ese nombre"));
        }

        return ResponseEntity.ok(Map.of("mensaje", "Proveedores encontrados", "proveedores", proveedores));
    }
}
