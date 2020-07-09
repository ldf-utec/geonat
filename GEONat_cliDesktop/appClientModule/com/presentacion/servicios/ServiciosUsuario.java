package com.presentacion.servicios;

import java.util.List;
import javax.persistence.PersistenceException;

import com.entities.Usuario;
import com.exception.ServiciosException;

public class ServiciosUsuario {

	ServiciosGUI servicios = ServiciosGUI.getServiciosGUI();
	
	// Crear nuevo
	public void create(Usuario usuario) throws ServiciosException {
		try {
			
			servicios.usuarioBean.create(usuario);
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al crear" );
		}
	}

	
	// Actualizar
	public void update(Usuario usuario) throws ServiciosException {
		try {
			
			servicios.usuarioBean.update(usuario);
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al actualizar");
		}		
	}

	// Eliminar por Id
	public void delete(int id) throws ServiciosException {
		try {
			servicios.usuarioBean.delete(id);
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al borrar");
		}		
	}
		

	// Obtener Todos
	public List<Usuario> obtenerTodos() throws ServiciosException {
		try {
			
			List<Usuario> lista = servicios.usuarioBean.obtenerTodos();
			return lista;
			
		} catch (Exception e) {
			throw new ServiciosException("Error al borrar");
		}
	}
	

	// Obtener Todos (filtro)
	public List<Usuario> obtenerTodos(String filtro) throws ServiciosException {
		try {
			
			List<Usuario> lista = servicios.usuarioBean.obtenerTodos(filtro);
			return lista;
			
		} catch (Exception e) {
			throw new ServiciosException("Error al borrar");
		}	
	}
	
	// TODO: Posiblemente este método no tiene mucho sentido, ya que al pasarse como parámetro el objeto Usuario, es más eficiente buscarlo por id.
	// Si lo que se quiere es obtener por Nombre, se debería pasar el String nombre
	// Obtener uno
	public Usuario obtenerUno(Usuario usuario) throws ServiciosException {
		
		String filtro = usuario.getNombreUsuario();
		Usuario u = servicios.usuarioBean.obtenerUno(usuario);
		return u ;
		
	  }
}
