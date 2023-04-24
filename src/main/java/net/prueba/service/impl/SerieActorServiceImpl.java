package net.prueba.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.prueba.model.SerieActor;
import net.prueba.repository.IGenericRepository;
import net.prueba.repository.ISerieActorRepository;
import net.prueba.service.ISerieActorService;
@AllArgsConstructor
@Transactional
@Service
public class SerieActorServiceImpl extends CRUDImpl<SerieActor, Integer> implements ISerieActorService{
	private ISerieActorRepository repo;
	@Override
	protected IGenericRepository<SerieActor, Integer> getRepo() {
		
		return repo;
	}

}
