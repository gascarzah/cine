package net.prueba.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.prueba.model.Serie;
import net.prueba.repository.IGenericRepository;
import net.prueba.repository.ISerieRepository;
import net.prueba.service.ISerieService;
@AllArgsConstructor
@Transactional
@Service
public class SerieServiceImpl extends CRUDImpl<Serie, Integer> implements ISerieService{
	private ISerieRepository repo;
	@Override
	protected IGenericRepository<Serie, Integer> getRepo() {
		
		return repo;
	}

}
