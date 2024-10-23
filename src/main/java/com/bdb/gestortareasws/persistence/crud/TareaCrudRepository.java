package com.bdb.gestortareasws.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TareaCrudRepository extends CrudRepository<Producto, Integer> {

    /*List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria); //Podemos llamarlo de cualquier manera Ej:getBy

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);*/
}
