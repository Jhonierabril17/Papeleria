package com.example.PapeleriaDependecias.Controller;

import com.example.PapeleriaDependecias.Model.Detalle_Venta;
import com.example.PapeleriaDependecias.Model.Empleado;
import com.example.PapeleriaDependecias.Service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Api/Empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(Map.of("mensaje", "Lista de empleados obtenida correctamente", "empleados", empleadoService.findAll()));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Empleado empleado) {
        try {
            Empleado nuevo = empleadoService.save(empleado);
            return ResponseEntity.status(201).body(Map.of("mensaje", "Empleado guardado con éxito", "empleado", nuevo));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return empleadoService.findById(id)
                .map(empleado -> ResponseEntity.ok().body(Map.of("mensaje", "Empleado encontrado", "empleado", empleado)))
                .orElse(ResponseEntity.status(404).body(Map.of("mensaje", "No se encontró el empleado con ese ID")));
    }

    @PutMapping("/Actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Empleado datosActualizados) {
        Optional<Empleado> actualizado = empleadoService.updateById(id, datosActualizados);
        return actualizado
                .map(emp -> ResponseEntity.ok().body(Map.of("mensaje", "Empleado actualizado", "empleado", emp)))
                .orElse(ResponseEntity.status(404).body(Map.of("mensaje", "Empleado no encontrado para actualizar")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        boolean eliminado = empleadoService.deleteById(id);
        if (eliminado) {
            return ResponseEntity.ok().body(Map.of("mensaje", "Empleado eliminado correctamente"));
        } else {
            return ResponseEntity.status(404).body(Map.of("mensaje", "No se pudo eliminar, empleado no encontrado"));
        }
    }
}
