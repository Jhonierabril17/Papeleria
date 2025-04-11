package com.example.PapeleriaDependecias.Repository;

import com.example.PapeleriaDependecias.Model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    @Query(value = "SELECT * FROM proveedor WHERE nombre LIKE %?1%", nativeQuery = true)
    List<Proveedor> searchByName(String nombre);
}
