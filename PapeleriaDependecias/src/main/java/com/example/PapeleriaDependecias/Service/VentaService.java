package com.example.PapeleriaDependecias.Service;

import com.example.PapeleriaDependecias.Model.Proveedor;
import com.example.PapeleriaDependecias.Model.Venta;
import com.example.PapeleriaDependecias.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Venta save(Venta venta) {
        if (venta.getFecha() == null) {
            throw new IllegalArgumentException("La fecha de la venta es obligatoria");
        }
        if (venta.getCliente() == null) {
            throw new IllegalArgumentException("Debe asignar un cliente a la venta");
        }
        if (venta.getEmpleado() == null) {
            throw new IllegalArgumentException("Debe asignar un empleado a la venta");
        }

        return ventaRepository.save(venta);
    }

    public Venta findById(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public Venta updateById(Long id, Venta datosActualizados) {
        return ventaRepository.findById(id).map(venta -> {
            venta.setFecha(datosActualizados.getFecha());
            venta.setCliente(datosActualizados.getCliente());
            venta.setEmpleado(datosActualizados.getEmpleado());
            return ventaRepository.save(venta);
        }).orElse(null);
    }

    public boolean deleteById(Long id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Venta> findVentasByEmpleadoId(Long idEmpleado) {
        return ventaRepository.findByEmpleadoId(idEmpleado);
    }

    public List<Venta> buscarVentasPorEmpleadoYCliente(Long idEmpleado, Long idCliente) {
        return ventaRepository.findVentasByEmpleadoAndCliente(idEmpleado, idCliente);
    }
}
