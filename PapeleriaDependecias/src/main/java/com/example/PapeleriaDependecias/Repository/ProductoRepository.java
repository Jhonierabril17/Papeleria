package com.example.PapeleriaDependecias.Repository;

import com.example.PapeleriaDependecias.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query(value = "SELECT * FROM producto WHERE id_proveedor = ?1", nativeQuery = true)
    List<Producto> findByProveedorId(Long idProveedor);
}
