package com.presentacion.servicios;

import java.util.List;

import javax.persistence.PersistenceException;

import com.entities.Fenomeno;
import com.entities.Usuario;
import com.exception.ServiciosException;

public class ServiciosFenomeno {
	
	private static ServiciosFenomeno instance = new ServiciosFenomeno();
	ServiciosGUI servicios = ServiciosGUI.getInstance();
	
	public ServiciosFenomeno() {
		super();
	}
	
	public static ServiciosFenomeno getInstance() {
		if (instance==null) {
			instance = new ServiciosFenomeno();
		}	
		return instance;
	}
	
	// Crear nuevo
	public void create(Fenomeno fenomeno) throws ServiciosException {
		try {
			
			servicios.fenomenoBean.create(fenomeno);
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al crear fenomeno" );
		}
	}

	
	// Actualizar
	public void update(Fenomeno fenomeno) throws ServiciosException {
		try {
			
			servicios.fenomenoBean.update(fenomeno);
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al actualizar el fenomeno");
		}		
	}

	// Eliminar por Id
	public void delete(int id) throws ServiciosException {
		try {
			servicios.fenomenoBean.delete(id);
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al borrar el fenomeno");
		}		
	}
		

	// Obtener Todos
	public List<Fenomeno> obtenerTodos() throws ServiciosException {
		try {
			
			List<Fenomeno> lista = servicios.fenomenoBean.obtenerTodos();
			return lista;
			
		} catch (Exception e) {
			throw new ServiciosException("Error al obtener datos");
		}
	}
	

	// Obtener Todos (filtro)
	public List<Fenomeno> obtenerTodos(String filtro) throws ServiciosException {
		try {
			
			List<Fenomeno> lista = servicios.fenomenoBean.obtenerTodosFiltro(filtro);
			return lista;
			
		} catch (Exception e) {
			throw new ServiciosException("Error al obtener lista");
		}	
	}
	
	// TODO: Posiblemente este método no tiene mucho sentido, ya que al pasarse como parámetro el objeto Usuario, es más eficiente buscarlo por id.
	// Si lo que se quiere es obtener por Nombre, se debería pasar el String nombre
	// Obtener uno
	public Fenomeno obtenerUno(int id) throws ServiciosException {
	
		return servicios.fenomenoBean.obtenerUno(id); 
	
	  }
	
	public boolean existeNombreFenomeno(Fenomeno fenomeno) throws ServiciosException {
		return servicios.fenomenoBean.existeNombreFenomeno(fenomeno);
	}

}
