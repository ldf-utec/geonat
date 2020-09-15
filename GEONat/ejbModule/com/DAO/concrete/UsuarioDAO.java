package com.DAO.concrete;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.DAO.interfaces.IUsuarioDAO;
import com.entities.Usuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class UsuarioDAO
 */

@Stateless
public class UsuarioDAO implements IUsuarioDAO {
	@PersistenceContext
	private EntityManager em;
    
    public UsuarioDAO() {
        
    }

	@Override
	public void create(Usuario usuario) throws ServiciosException {
		try {
			
			em.persist(usuario);
			em.flush();
			
		} catch (PersistenceException e) {
			
			throw new ServiciosException("Error al crear" );
			
		}
		
	}

	@Override
	public void update(Usuario usuario) throws ServiciosException {
		try {
			
			em.merge(usuario);
			em.flush();
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al actualizar");
		}
		
	}

	@Override
	public void delete(int id) throws ServiciosException {
		try {
			Usuario usuario = em.find(Usuario.class, id);
			em.remove(usuario);
			em.flush();
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al borrar");
		}
		
	}

	@Override
	public List<Usuario> obtenerTodos() throws ServiciosException {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.obtenerTodos", Usuario.class);
		return query.getResultList();
	}
	
	@Override
	public List<Usuario> obtenerTodos(String filtro) throws ServiciosException {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.obtenerTodosFiltro", Usuario.class)
				.setParameter("filtro", filtro);
		return query.getResultList();
	}
	
	@Override
	public boolean existeNombreUsuario(Usuario usuario) throws ServiciosException {
		String filtro = usuario.getNombreUsuario();
		TypedQuery<Long> query = em.createNamedQuery("Usuario.existeNombreUsuario", Long.class)
				.setParameter("filtro", filtro);
		if (query.getSingleResult()==0) {
			return false;
		} else {
			return true;
		}

	  }

	@Deprecated
	@Override
	public Usuario obtenerUno(Usuario usuario) throws ServiciosException {
		
		String filtro = usuario.getNombreUsuario();
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.obtenerUno", Usuario.class)
				.setParameter("filtro", filtro);
		return query.getSingleResult() ;
	}
	
	@Override
	public Usuario obtenerUno(Integer id) throws ServiciosException {
		
		Usuario u = em.find( Usuario.class, id);
		return u ;
	}
	
	
	@Override
	public List<Usuario> obtenerLogin(Usuario usuario) throws ServiciosException {
		
		String filtro = usuario.getNombreUsuario();
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.obtenerUno", Usuario.class)
				.setParameter("filtro", filtro);
		return query.getResultList() ;
	}
	
}
