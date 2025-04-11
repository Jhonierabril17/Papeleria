package com.example.PapeleriaDependecias.Service;

import com.example.PapeleriaDependecias.Model.Cliente;
import com.example.PapeleriaDependecias.Model.Detalle_Venta;
import com.example.PapeleriaDependecias.Model.Venta;
import com.example.PapeleriaDependecias.Repository.Detalle_VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Detalle_VentaService {

    @Autowired
    private Detalle_VentaRepository detalleVentaRepository;

    public List<Detalle_Venta> findAll(){
        return detalleVentaRepository.findAll();
    }

    public Optional<Detalle_Venta> findById(Long id) {
        return detalleVentaRepository.findById(id);
    }

    public Detalle_Venta save(Detalle_Venta detalleVenta) {
        if (detalleVenta.getCantidad() == null || detalleVenta.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        if (detalleVenta.getPrecio_unitario() == null || detalleVenta.getPrecio_unitario() <= 0) {
            throw new IllegalArgumentException("El precio unitario debe ser mayor a cero");
        }
        return detalleVentaRepository.save(detalleVenta);
    }

    public Optional<Detalle_Venta> updateById(Long id, Detalle_Venta detalleVentaActualizado) {
        return detalleVentaRepository.findById(id).map(detalle -> {
            detalle.setCantidad(detalleVentaActualizado.getCantidad());
            detalle.setPrecio_unitario(detalleVentaActualizado.getPrecio_unitario());
            return detalleVentaRepository.save(detalle);
        });
    }

    public boolean deleteById(Long id) {
        if (detalleVentaRepository.existsById(id)) {
            detalleVentaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Detalle_Venta> buscarDetallesPorEmpleadoYCliente(Long idEmpleado, Long idCliente) {
        return detalleVentaRepository.findDetallesByEmpleadoAndCliente(idEmpleado, idCliente);
    }
}
