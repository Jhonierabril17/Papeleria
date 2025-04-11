package com.example.PapeleriaDependecias.Service;

import com.example.PapeleriaDependecias.Model.Cliente;
import com.example.PapeleriaDependecias.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente save(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío");
        }
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente updateById(Long id, Cliente clienteActualizado) {
        Cliente clienteExistente = clienteRepository.findById(id).orElse(null);
        if (clienteExistente != null) {
            clienteExistente.setNombre(clienteActualizado.getNombre());
            clienteExistente.setCedula(clienteActualizado.getCedula());
            clienteExistente.setTelefono(clienteActualizado.getTelefono());
            clienteExistente.setCorreo(clienteActualizado.getCorreo());
            return clienteRepository.save(clienteExistente);
        }
        return null;
    }

    public void deleteById(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe el cliente con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
