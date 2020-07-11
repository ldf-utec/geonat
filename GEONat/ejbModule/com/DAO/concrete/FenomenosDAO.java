package com.DAO.concrete;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.DAO.interfaces.IFenomenoDAO;
import com.entities.Fenomeno;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class FenomenoBean
 */
@Stateless
public class FenomenosDAO implements IFenomenoDAO {

	@PersistenceContext
	private EntityManager em;
	
    public FenomenosDAO() {
    	
    }
    
    @Override
	public void create(Fenomeno fenomeno) throws ServiciosException {
		try {
			em.persist(fenomeno);
			em.flush();		
		} catch (PersistenceException e) {	
			throw new ServiciosException("Error al crear fenomeno. " + e.getMessage(), e );	
		}
	}
    
    @Override
	public void update(Fenomeno fenomeno) throws ServiciosException {
		try {
			em.merge(fenomeno);
			em.flush();	
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al actualizar el fenomeno. "+e.getMessage(),e);
		}
	}
    
    @Override
	public void delete(int id) throws ServiciosException {
    	
    	Fenomeno fenomeno = em.find(Fenomeno.class, id);
		if (fenomeno == null) {
			throw new ServiciosException("No existe el fenomeno a borrar. Id=" + fenomeno.getId_Fenomeno());
		}
    	try {
			
			em.remove(fenomeno);
			em.flush();	
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al borrar");
		}	
	}
    
    @Override
	public List<Fenomeno> obtenerTodos() throws ServiciosException {
		TypedQuery<Fenomeno> query = em.createNamedQuery("Fenomeno.obtenerTodos", Fenomeno.class);
		return query.getResultList();
	}
	
	@Override
	public List<Fenomeno> obtenerTodosFiltro(String filtro) throws ServiciosException {
		TypedQuery<Fenomeno> query = em.createNamedQuery("Fenomeno.obtenerTodosFiltro", Fenomeno.class)
				.setParameter("filtro", filtro);
		return query.getResultList();
	}
	
	@Override
	public boolean existeNombreFenomeno(Fenomeno fenomeno) throws ServiciosException {
		String filtro = fenomeno.getNombre();
		TypedQuery<Long> query = em.createNamedQuery("Fenomeno.existeNombreFenomeno", Long.class)
				.setParameter("filtro", filtro);
		if (query.getSingleResult()==0) {
			return false;
		} else {
			return true;
		}

	  }
	
	@Override
	public Fenomeno obtenerUno(int id) throws ServiciosException {
		try {
			//System.out.println("Obteniendo Fenomeno1");
			Fenomeno fenomeno = em.find(Fenomeno.class, id);
			//System.out.println("Obteniendo Fenomeno2");
			return fenomeno;
			
		} catch (PersistenceException e) {
			System.out.println("Error al obtener fenomeno");
			throw new ServiciosException("Error al consutar");
			
		}
		
	  }
	
	 @Override
		public boolean existeID(int id) throws ServiciosException {
			try {
				Fenomeno fenomeno = em.find(Fenomeno.class, id);
				if (fenomeno != null) {
					return true;
				} 
				return false;
			} catch (PersistenceException e) {
				throw new ServiciosException("Error al consultar");
			}	
		}
	    
	


}
