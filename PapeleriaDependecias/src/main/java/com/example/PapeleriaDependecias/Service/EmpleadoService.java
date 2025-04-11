package com.example.PapeleriaDependecias.Service;

import com.example.PapeleriaDependecias.Model.Cliente;
import com.example.PapeleriaDependecias.Model.Empleado;
import com.example.PapeleriaDependecias.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> findById(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado save(Empleado empleado) {
        if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (empleado.getCargo() == null || empleado.getCargo().isEmpty()) {
            throw new IllegalArgumentException("El cargo no puede estar vacío");
        }
        return empleadoRepository.save(empleado);
    }

    public Optional<Empleado> updateById(Long id, Empleado datosNuevos) {
        Optional<Empleado> existente = empleadoRepository.findById(id);
        if (existente.isPresent()) {
            Empleado empleado = existente.get();
            empleado.setNombre(datosNuevos.getNombre());
            empleado.setCargo(datosNuevos.getCargo());
            empleado.setTelefono(datosNuevos.getTelefono());
            return Optional.of(empleadoRepository.save(empleado));
        }
        return Optional.empty();
    }

    public boolean deleteById(Long id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
