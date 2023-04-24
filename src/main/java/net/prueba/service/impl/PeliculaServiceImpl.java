package net.prueba.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.prueba.dto.request.PeliculaRequest;
import net.prueba.model.Actor;
import net.prueba.model.Pelicula;
import net.prueba.repository.IActorRepository;
import net.prueba.repository.IGenericRepository;
import net.prueba.repository.IPeliculaRepository;
import net.prueba.service.IPeliculaService;
@AllArgsConstructor
@Transactional
@Service
public class PeliculaServiceImpl extends CRUDImpl<Pelicula, Integer> implements IPeliculaService{
	private IPeliculaRepository repo;
	

	
	@Override
	protected IGenericRepository<Pelicula, Integer> getRepo() {
		
		return repo;
	}


}
