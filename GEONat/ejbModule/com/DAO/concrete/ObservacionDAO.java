package com.DAO.concrete;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import com.DAO.interfaces.IObservacionDAO;
import com.Enums.Criticidad;
import com.entities.Fenomeno;
import com.entities.Observacion;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class ObservacionDAO
 */
@Stateless
public class ObservacionDAO implements IObservacionDAO {

	@PersistenceContext
	private EntityManager em;

    public ObservacionDAO() {
        
    }

	@Override
	public Observacion create(Observacion observacion) throws ServiciosException {
		try {
			if(observacion.getFenomeno()!=null) {
				observacion.setFenomeno(em.find(Fenomeno.class, observacion.getFenomeno().getId_Fenomeno()));
			}
			
			em.persist(observacion);
			em.flush();	
			em.refresh(observacion);
			return observacion; // Retorno el objeto persistido, que contiene la PK autogenerada, ya que la necesito para persistir la clase asociada (Detalles..)
		} catch (PersistenceException e) {	
			throw new ServiciosException("Error al crear" );	
			
		}
		
	}

	@Override
	public void update(Observacion observacion) throws ServiciosException {
		try {
			em.merge(observacion);
			em.flush();	
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al actualizar");
		}
		
	}

	@Override
	public void delete(int id) throws ServiciosException {
		try {
			Observacion observacion = em.find(Observacion.class, id);
			em.remove(observacion);
			em.flush();	
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al borrar");
		}	
		
	}

	@Override
	public List<Observacion> obtenerTodos() throws ServiciosException {
		TypedQuery<Observacion> query = em.createNamedQuery("Observacion.obtenerTodos", Observacion.class);
		return query.getResultList();
	}

//	@Override
//	public List<Observacion> obtenerTodosFiltro(String filtro) throws ServiciosException {
//		TypedQuery<Observacion> query = em.createNamedQuery("Observacion.obtenerTodosFiltro", Observacion.class)
//				.setParameter("filtro", filtro);
//		return query.getResultList();
//	}
	
	@Override
	public List<Observacion> obtenerPorCriticidad(Criticidad criticidad) throws ServiciosException {
		TypedQuery<Observacion> query = em.createNamedQuery("Observacion.obtenerPorCriticidad", Observacion.class)
				.setParameter("criticidad", criticidad);
		List<Observacion> lista = query.getResultList();
		if (lista.size()>0) {
			return lista;
		}else {
			return null;
		}
	}

	@Override
	public Observacion obtenerUno(int id) throws ServiciosException {
		try {
			Observacion observacion = em.find(Observacion.class, id);
			if (observacion != null) {
				return observacion;
			} 
			return observacion = null;
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al obtener por id");
		}
	}
	
	@Override
	public List<Observacion> obtenerPorCriticidadRangoFechas(Criticidad criticidad, Date startDate, Date endDate) throws ServiciosException {
		String strCriticidad; 
		TypedQuery<Observacion> query;
		
		if (criticidad == null) {
			query = em.createNamedQuery("Observacion.obtenerPorRangoFechas", Observacion.class)
				.setParameter("startDate", startDate, TemporalType.DATE)
				.setParameter("endDate", endDate, TemporalType.DATE);
		}else {
			strCriticidad = criticidad.toString();
			query = em.createNamedQuery("Observacion.obtenerPorCriticidadRangoFechas", Observacion.class)
				.setParameter("criticidad", criticidad)
				.setParameter("startDate", startDate, TemporalType.DATE)
				.setParameter("endDate", endDate, TemporalType.DATE);
		}
		
		List<Observacion> lista = query.getResultList();
		if (lista.size()>0) {
			return lista;
		}else {
			return null;
		}	
	}

}

// http://www.jtech.ua.es/j2ee/restringido/jpa/sesion05-apuntes.html#C%C3%B3mo+implementar+y+usar+un+DAO+con+JPA
