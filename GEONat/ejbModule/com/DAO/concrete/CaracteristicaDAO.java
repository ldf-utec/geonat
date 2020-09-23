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
			throw new ServiciosException("Error al crear" );	
		}
	}
    
    
    @Override
	public void update(Caracteristica caracteristica) throws ServiciosException {
		try {
			em.merge(caracteristica);
			em.flush();	
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al actualizar");
		}
	}
    
    
    @Override
	public void delete(int id) throws ServiciosException {
		try {
			Caracteristica caracteristica = em.find(Caracteristica.class, id);
			em.remove(caracteristica);
			em.flush();	
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al borrar");
		}	
	}
    
    
    @Override
	public List<Caracteristica> obtenerTodos() throws ServiciosException {
		try {
			TypedQuery<Caracteristica> query = em.createNamedQuery("Caracteristica.obtenerTodos", Caracteristica.class);
			
			List<Caracteristica> lista = (List<Caracteristica>)query.getResultList();
			lista.forEach(System.out::println);
			return lista;
		} catch (Exception e) {
			System.out.println("Error al obtenerTodos Caracteristica. " + e.getMessage());
		}
		return null;
	}
	
    
	@Override
	public List<Caracteristica> obtenerTodosFiltro(String filtro) throws ServiciosException {
		TypedQuery<Caracteristica> query = em.createNamedQuery("Caracteristica.obtenerTodosFiltro", Caracteristica.class)
				.setParameter("filtro", filtro);
		return query.getResultList();
	}
	
	
	// TODO: Borrar este método, ya que se hace esto mediante el obtenerTodos.first() y verificando si devuelve distinto de null por ejemplo
	@Override
	public boolean existeIdCaracteristica(Caracteristica caracteristica) throws ServiciosException {
		String filtro = caracteristica.getNombre();
		TypedQuery<Long> query = em.createNamedQuery("Caracteristica.existeNombreFenomeno", Long.class)
				.setParameter("filtro", filtro);
		if (query.getSingleResult()==0) {
			return false;
		} else {
			return true;
		}
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

}
