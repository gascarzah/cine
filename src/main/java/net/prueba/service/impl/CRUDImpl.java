package net.prueba.service.impl;
import java.util.List;

import net.prueba.exception.ResourceNotFoundException;
import net.prueba.repository.IGenericRepository;
import net.prueba.service.ICRUD;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

	protected abstract IGenericRepository<T, ID> getRepo();
	
	@Override
	public T registrar(T t) throws ResourceNotFoundException {
		return getRepo().save(t);
	}

	@Override
	public T modificar(T t) throws ResourceNotFoundException {
		return getRepo().save(t);
	}

	@Override
	public List<T> listar() throws ResourceNotFoundException {		
		return getRepo().findAll();
	}

	@Override
	public T listarPorId(ID id) throws ResourceNotFoundException {
		return getRepo().findById(id).orElse(null);
	}

	@Override
	public void eliminar(ID id) throws ResourceNotFoundException {
		getRepo().deleteById(id);
	}

}
