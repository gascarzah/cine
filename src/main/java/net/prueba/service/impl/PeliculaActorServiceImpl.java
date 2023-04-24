package net.prueba.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.prueba.model.PeliculaActor;
import net.prueba.repository.IGenericRepository;
import net.prueba.repository.IPeliculaActorRepository;
import net.prueba.service.IPeliculaActorService;
@AllArgsConstructor
@Transactional
@Service
public class PeliculaActorServiceImpl extends CRUDImpl<PeliculaActor, Integer> implements IPeliculaActorService{
	private IPeliculaActorRepository repo;
	@Override
	protected IGenericRepository<PeliculaActor, Integer> getRepo() {
		
		return repo;
	}

}
