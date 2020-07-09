package com.DAO.concrete;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.DAO.interfaces.IDetallesObservacionDAO;
import com.entities.DetalleObservacion;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class DetallesObservacionDAO
 */
@Stateless
public class DetallesObservacionDAO implements IDetallesObservacionDAO {

	@PersistenceContext
	private EntityManager em;
	
    public DetallesObservacionDAO() {
        
    }

	@Override
	public void create(DetalleObservacion detalleObservacion) throws ServiciosException {
		try {
			em.persist(detalleObservacion);
			em.flush();		
		} catch (PersistenceException e) {	
			throw new ServiciosException("Error al crear" );	
		}
		
	}

	@Override
	public void update(DetalleObservacion detalleObservacion) throws ServiciosException {
		try {
			em.merge(detalleObservacion);
			em.flush();	
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al actualizar");
		}
	}

	@Override
	public void delete(int id) throws ServiciosException {
		try {
			DetalleObservacion detalleObservacion = em.find(DetalleObservacion.class, id);
			em.remove(detalleObservacion);
			em.flush();	
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al borrar");
		}	
	}

	@Override
	public DetalleObservacion obtenerUno(int id) throws ServiciosException {
		try {
			DetalleObservacion detalleObservacion = em.find(DetalleObservacion.class, id);
			return detalleObservacion;
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al consutar");
		}
	}

	@Override
	public List<DetalleObservacion> obtenerTodos() throws ServiciosException {
		try {
			TypedQuery<DetalleObservacion> query = em.createNamedQuery("DetalleObservacion.obtenerTodos", DetalleObservacion.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new ServiciosException("Error al consutar");
		}
		
		
	}

	@Override
	public List<DetalleObservacion> obtenerTodosFiltro(String filtro) throws ServiciosException {
		TypedQuery<DetalleObservacion> query = em.createNamedQuery("DetalleObservacion.obtenerTodosFiltro", DetalleObservacion.class)
				.setParameter("filtro", filtro);
		return query.getResultList();
	}

}
