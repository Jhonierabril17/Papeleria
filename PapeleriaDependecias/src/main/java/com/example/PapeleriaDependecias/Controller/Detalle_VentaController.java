package com.example.PapeleriaDependecias.Controller;

import com.example.PapeleriaDependecias.Model.Cliente;
import com.example.PapeleriaDependecias.Model.Detalle_Venta;
import com.example.PapeleriaDependecias.Service.ClienteService;
import com.example.PapeleriaDependecias.Service.Detalle_VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Api/Detalle_Venta")
public class Detalle_VentaController {

    @Autowired
    private Detalle_VentaService detalleVentaService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Detalle_Venta> detalles = detalleVentaService.findAll();
        return ResponseEntity.ok(Map.of("mensaje", "Lista de detalles obtenida", "detalles", detalles));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Detalle_Venta detalleVenta) {
        try {
            Detalle_Venta saved = detalleVentaService.save(detalleVenta);
            return ResponseEntity.status(201).body(Map.of("mensaje", "Detalle guardado con éxito", "detalle", saved));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return detalleVentaService.findById(id)
                .map(detalle -> ResponseEntity.ok(Map.of("mensaje", "Detalle encontrado", "detalle", detalle)))
                .orElse(ResponseEntity.status(404).body(Map.of("mensaje", "Detalle no encontrado")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody Detalle_Venta detalleVenta) {
        return detalleVentaService.updateById(id, detalleVenta)
                .map(updated -> ResponseEntity.ok(Map.of("mensaje", "Detalle actualizado con éxito", "detalle", updated)))
                .orElse(ResponseEntity.status(404).body(Map.of("mensaje", "Detalle no encontrado")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (detalleVentaService.deleteById(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Detalle eliminado correctamente"));
        } else {
            return ResponseEntity.status(404).body(Map.of("mensaje", "Detalle no encontrado"));
        }
    }

    @GetMapping("/empleado/{idEmpleado}/cliente/{idCliente}")
    public ResponseEntity<?> obtenerDetallesPorEmpleadoYCliente(
            @PathVariable Long idEmpleado,
            @PathVariable Long idCliente) {

        List<Detalle_Venta> detalles = detalleVentaService.buscarDetallesPorEmpleadoYCliente(idEmpleado, idCliente);

        if (detalles.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("mensaje", "No se encontraron detalles de venta entre este empleado y cliente."));
        }

        return ResponseEntity.ok(Map.of("mensaje", "Detalles encontrados", "detalles", detalles));
    }

}
