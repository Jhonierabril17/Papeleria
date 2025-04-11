package com.example.PapeleriaDependecias.Service;

import com.example.PapeleriaDependecias.Model.Producto;
import com.example.PapeleriaDependecias.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    public Producto save(Producto producto){
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        return productoRepository.save(producto);
    }

    public Producto findById(Long id){
        return productoRepository.findById(id).orElse(null);
    }

    public Producto updateById(Long id, Producto nuevosDatos) {
        Optional<Producto> productoExistente = productoRepository.findById(id);
        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setNombre(nuevosDatos.getNombre());
            producto.setDescripcion(nuevosDatos.getDescripcion());
            producto.setPrecio(nuevosDatos.getPrecio());
            producto.setStock(nuevosDatos.getStock());
            producto.setProveedor(nuevosDatos.getProveedor());
            return productoRepository.save(producto);
        }
        return null;
    }

    public boolean deleteById(Long id){
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Producto> buscarPorProveedor(Long idProveedor) {
        return productoRepository.findByProveedorId(idProveedor);
    }

}
