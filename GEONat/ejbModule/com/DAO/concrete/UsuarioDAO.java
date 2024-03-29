package com.DAO.concrete;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	
	@Deprecated
	@Override
	public Usuario obtenerPorNombreOld(String nombreUsuario) throws ServiciosException{
		
		Usuario usr = new Usuario();
		nombreUsuario = nombreUsuario.toUpperCase();
		try {
			TypedQuery<Usuario> query = em.createNamedQuery("Usuario.obtenerPorNombre", Usuario.class)
					.setParameter("filtro", nombreUsuario);
			//query.setMaxResults(1);
			List<Usuario> lista = query.getResultList();
			if (lista.size()>0) {
				usr = lista.get(0);
			} else {
				usr.setNombreUsuario("");
			}

		} catch  (Exception e) {
			e.printStackTrace();
		}
		
		return usr;
	  }
	
	
	@Override
	public Usuario obtenerPorNombre(String nombreUsuario) throws ServiciosException{

		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.obtenerPorNombre", Usuario.class)
				.setParameter("filtro", nombreUsuario.toUpperCase().replace("%", "")+ "%");
		List<Usuario> lista = query.getResultList();
		
		if (lista.size()>0) {
			return lista.get(0);
		} else {
			return null;
		}
	  }
	
	
	
	@Override
	public Usuario obtenerPorDocumento(String documento) throws ServiciosException {
		
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.obtenerPorDocumento", Usuario.class)
				.setParameter("filtro", documento );
		List<Usuario> lista = query.getResultList();
		
		if (lista.size()>0 ) {
			// si obtuvo resultados, retorno el primer elemento
			return lista.get(0); 
		}else {
			return null;
		}
	}
	
	@Override
	public List<Usuario> obtenerPorEstado(Boolean estado) throws ServiciosException {
		
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.obtenerPorEstado", Usuario.class)
				.setParameter("estado", estado );
		List<Usuario> lista = query.getResultList();
		
		if (lista.size()>0 ) {
			// si obtuvo resultados, retorno todo
			return lista; 
		}else {
			return null;
		}	
	}
	
}
