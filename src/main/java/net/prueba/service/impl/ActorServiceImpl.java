package net.prueba.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.prueba.model.Actor;
import net.prueba.repository.IActorRepository;
import net.prueba.repository.IGenericRepository;
import net.prueba.service.IActorService;
@AllArgsConstructor
@Transactional
@Service
public class ActorServiceImpl extends CRUDImpl<Actor, Integer> implements IActorService {
	private IActorRepository repo;
	@Override
	protected IGenericRepository<Actor, Integer> getRepo() {
		return repo;
	}

}
