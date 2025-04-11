package com.example.PapeleriaDependecias.Controller;

import com.example.PapeleriaDependecias.Model.Venta;
import com.example.PapeleriaDependecias.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Api/Venta")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(
                Map.of("mensaje", "Lista de ventas obtenida", "ventas", ventaService.findAll())
        );
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Venta venta) {
        try {
            Venta guardada = ventaService.save(venta);
            return ResponseEntity.status(201).body(
                    Map.of("mensaje", "Venta guardada correctamente", "venta", guardada)
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Venta venta = ventaService.findById(id);
        if (venta != null) {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Venta encontrada", "venta", venta)
            );
        } else {
            return ResponseEntity.status(404).body(Map.of("mensaje", "Venta no encontrada"));
        }
    }

    @GetMapping("/empleado/{id}")
    public ResponseEntity<?> ventasPorEmpleado(@PathVariable Long id) {
        List<Venta> ventas = ventaService.findVentasByEmpleadoId(id);
        if (ventas.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("mensaje", "No se encontraron ventas para el empleado"));
        }
        return ResponseEntity.ok(Map.of("mensaje", "Ventas encontradas", "ventas", ventas));
    }

    @GetMapping("/empleado/{idEmpleado}/cliente/{idCliente}")
    public ResponseEntity<?> obtenerVentasPorEmpleadoYCliente(
            @PathVariable Long idEmpleado,
            @PathVariable Long idCliente) {

        List<Venta> ventas = ventaService.buscarVentasPorEmpleadoYCliente(idEmpleado, idCliente);

        if (ventas.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("mensaje", "No se encontraron ventas entre este empleado y cliente."));
        }

        return ResponseEntity.ok(Map.of("mensaje", "Ventas encontradas", "ventas", ventas));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Venta venta) {
        Venta actualizada = ventaService.updateById(id, venta);
        if (actualizada != null) {
            return ResponseEntity.ok().body(
                    Map.of("mensaje", "Venta actualizada correctamente", "venta", actualizada)
            );
        } else {
            return ResponseEntity.status(404).body(Map.of("mensaje", "No se pudo actualizar, la venta no existe"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        boolean eliminado = ventaService.deleteById(id);
        if (eliminado) {
            return ResponseEntity.ok().body(Map.of("mensaje", "Venta eliminada correctamente"));
        } else {
            return ResponseEntity.status(404).body(Map.of("mensaje", "Venta no encontrada para eliminar"));
        }
    }
}
