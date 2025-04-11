package com.example.PapeleriaDependecias.Service;

import com.example.PapeleriaDependecias.Model.Producto;
import com.example.PapeleriaDependecias.Model.Proveedor;
import com.example.PapeleriaDependecias.Repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    public Proveedor save(Proveedor proveedor) {
        if (proveedor.getNombre() == null || proveedor.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proveedor es obligatorio");
        }
        if (proveedor.getTelefono() == null || proveedor.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        return proveedorRepository.save(proveedor);
    }

    public Proveedor findById(Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public Proveedor updateById(Long id, Proveedor datosNuevos) {
        return proveedorRepository.findById(id).map(proveedor -> {
            proveedor.setNombre(datosNuevos.getNombre());
            proveedor.setTelefono(datosNuevos.getTelefono());
            proveedor.setCorreo(datosNuevos.getCorreo());
            proveedor.setDireccion(datosNuevos.getDireccion());
            return proveedorRepository.save(proveedor);
        }).orElse(null);
    }

    public boolean deleteById(Long id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Proveedor> buscarPorNombre(String nombre) {
        return proveedorRepository.searchByName(nombre);
    }
}
