package com.example.PapeleriaDependecias.Repository;

import com.example.PapeleriaDependecias.Model.Detalle_Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Detalle_VentaRepository extends JpaRepository<Detalle_Venta, Long> {
    //4. Mostrar los detalles de ventas que ha hecho un empleado a un cliente
    @Query(value = "SELECT dv.* FROM detalle_venta dv JOIN venta v ON dv.id_venta = v.id_venta WHERE v.id_empleado = ?1 AND v.id_cliente = ?2", nativeQuery = true)
    List<Detalle_Venta> findDetallesByEmpleadoAndCliente(Long idEmpleado, Long idCliente);
}

