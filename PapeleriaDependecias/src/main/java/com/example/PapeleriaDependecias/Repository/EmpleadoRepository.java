package com.example.PapeleriaDependecias.Repository;

import com.example.PapeleriaDependecias.Model.Detalle_Venta;
import com.example.PapeleriaDependecias.Model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    @Query(value = "SELECT * FROM empleado WHERE cargo = ?1", nativeQuery = true)
    List<Empleado> findByCargo(String cargo);
}
