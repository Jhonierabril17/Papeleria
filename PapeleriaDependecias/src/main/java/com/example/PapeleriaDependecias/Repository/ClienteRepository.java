package com.example.PapeleriaDependecias.Repository;


import com.example.PapeleriaDependecias.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query(value = "SELECT * FROM cliente WHERE cedula = ?1", nativeQuery = true)
    Cliente findByCedula(String cedula);

    @Query(value = "SELECT * FROM cliente WHERE nombre LIKE %?1%", nativeQuery = true)
    List<Cliente> searchByName(String nombre);
}
