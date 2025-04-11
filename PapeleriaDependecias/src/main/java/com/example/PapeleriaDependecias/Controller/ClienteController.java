package com.example.PapeleriaDependecias.Controller;

import com.example.PapeleriaDependecias.Model.Cliente;
import com.example.PapeleriaDependecias.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Api/Cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> getAllClientes() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok().body(Map.of("mensaje", "Lista de clientes obtenida", "clientes", clientes));
    }

    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
        try {
            Cliente saved = clienteService.save(cliente);
            return ResponseEntity.status(200).body(Map.of("mensaje", "Cliente creado exitosamente", "cliente", saved));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Long id) {
        return clienteService.findById(id)
                .map(cliente -> ResponseEntity.ok().body(Map.of("mensaje", "Cliente encontrado", "cliente", cliente)))
                .orElse(ResponseEntity.status(404).body(Map.of("mensaje", "Cliente no encontrado")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente updated = clienteService.updateById(id, cliente);
            return ResponseEntity.ok().body(Map.of("mensaje", "Cliente actualizado correctamente", "cliente", updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        try {
            clienteService.deleteById(id);
            return ResponseEntity.ok().body(Map.of("mensaje", "Cliente eliminado correctamente"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }
}
