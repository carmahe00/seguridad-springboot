package com.openwebinairs.rest.services.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * clase envoltorio para que extienda otras clases
 * 
 * @author juan
 *
 * @param <T> clase entidad
 * @param <ID> indetificador de clase entidad
 * @param <R> interfaz
 */
public abstract class BaseService<T, ID, R extends JpaRepository<T, ID>>{

	@Autowired
	protected R repositorio;
		
	public T save(T t) {
		return repositorio.save(t);
	}
	
	public Optional<T> findById(ID id){
		return repositorio.findById(id);
	}
	
	public List<T> findAll(){
		return repositorio.findAll();
	}
	
	public T edit(T t) {
		return repositorio.save(t);
	}
	
	public void delete(T t) {
		repositorio.delete(t);
	}
	
	public void deleetById(ID id) {
		repositorio.deleteById(id);
	}
}
