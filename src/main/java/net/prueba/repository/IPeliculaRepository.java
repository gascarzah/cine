package net.prueba.repository;

import org.springframework.stereotype.Repository;

import net.prueba.model.Pelicula;
@Repository
public interface IPeliculaRepository extends IGenericRepository<Pelicula,Integer>{
   Pelicula findByNombre(String nombre);
}
