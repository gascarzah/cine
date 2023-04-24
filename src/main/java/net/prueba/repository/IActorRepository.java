package net.prueba.repository;

import org.springframework.stereotype.Repository;

import net.prueba.model.Actor;

@Repository
public interface IActorRepository extends IGenericRepository<Actor,Integer>{
	

}
