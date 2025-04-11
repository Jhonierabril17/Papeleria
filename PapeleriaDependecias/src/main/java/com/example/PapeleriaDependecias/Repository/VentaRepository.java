package com.example.PapeleriaDependecias.Repository;

import com.example.PapeleriaDependecias.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    //1. Listar las ventas que ha realizado un empleado
    @Query(value = "SELECT * FROM venta WHERE id_empleado = ?1", nativeQuery = true)
    List<Venta> findByEmpleadoId(Long idEmpleado);

    //3. Mostrar las ventas que ha realizado un empleado a un cliente
    @Query(value = "SELECT * FROM venta WHERE id_empleado = ?1 AND id_cliente = ?2", nativeQuery = true)
    List<Venta> findVentasByEmpleadoAndCliente(Long idEmpleado, Long idCliente);
}
