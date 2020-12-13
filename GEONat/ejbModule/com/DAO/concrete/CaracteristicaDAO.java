package com.DAO.concrete;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.DAO.interfaces.ICaracteristicaDAO;
import com.entities.Caracteristica;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class CaracteristicaBean
 */
@Stateless
public class CaracteristicaDAO implements ICaracteristicaDAO {

	@PersistenceContext
	private EntityManager em;
	
    public CaracteristicaDAO() {
    	
    }
    
    
    @Override
	public void create(Caracteristica caracteristica) throws ServiciosException {
		try {
			em.persist(caracteristica);
			em.flush();		
		} catch (PersistenceException e) {	
			throw new ServiciosException("Error al crear característica" );	
		}
	}
    
    
    @Override
	public void update(Caracteristica caracteristica) throws ServiciosException {
		try {
			em.merge(caracteristica);
			em.flush();	
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al actualizar característica");
		}
	}
    
    
    @Override
	public void delete(int id) throws ServiciosException {
		try {
			Caracteristica caracteristica = em.find(Caracteristica.class, id);
			em.remove(caracteristica);
			em.flush();	
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al borrar carcterística");
		}	
	}
    
    
    @Override
	public List<Caracteristica> obtenerTodos() throws ServiciosException {
		try {
			TypedQuery<Caracteristica> query = em.createNamedQuery("Caracteristica.obtenerTodos", Caracteristica.class);
			List<Caracteristica> lista = query.getResultList();
			return lista;
			
		} catch (Exception e) {
			System.out.println("Error al obtenerTodos Característica. " + e.getMessage());
		}
		return null;
	}
	
    
	@Override
	public List<Caracteristica> obtenerTodosFiltro(String filtro) throws ServiciosException {
		TypedQuery<Caracteristica> query = em.createNamedQuery("Caracteristica.obtenerPorNombre", Caracteristica.class)
				.setParameter("filtro", filtro);
		return query.getResultList();
	}
	
	
	@Override
	public Caracteristica obtenerUno(int id) throws ServiciosException {
		try {
			Caracteristica c = em.find(Caracteristica.class, id);
			if (c != null) {
				return c;
			} 
			return c = null;
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al consultar");
		}
	}
	
	@Override
	public boolean existeNombreCaracteristica(String nombre) throws ServiciosException {
		TypedQuery<Long> query = em.createNamedQuery("Caracteristica.existeNombreCaracteristica", Long.class)
				.setParameter("filtro", nombre);
		if (query.getSingleResult()==0) {
			return false;
		} else {
			return true;
		}

	}

}
