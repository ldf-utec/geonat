package com.presentacion.servicios;

import java.util.List;

import javax.persistence.PersistenceException;

import com.entities.Fenomeno;
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
			throw new ServiciosException("Error al obtener datos " +e.getMessage());
		}
	}
	

	
	  // Obtener Todos (filtro) 
	public List<Fenomeno> obtenerTodosPorNombre(String filtro) throws ServiciosException { 
		  try {
			  List<Fenomeno> lista = servicios.fenomenoBean.obtenerTodosPorNombre(filtro);
	  	  		return lista;
	  
		  } catch (Exception e) { 
			  throw new  ServiciosException("Error al obtener lista"); 
		  } 
	}
	 
	
	
	public List<Fenomeno> obtenerUnoID(Integer filtro) throws ServiciosException {
		
		List<Fenomeno> lista = servicios.fenomenoBean.obtenerUnoID(filtro);
		return lista;
		
	}
		
	
	  public Fenomeno obtenerUno(int id) throws ServiciosException {
	  
	  return servicios.fenomenoBean.obtenerUno(id);
	  
	  }
	 
	
	public boolean existeNombreFenomeno(Fenomeno fenomeno) throws ServiciosException {
		return servicios.fenomenoBean.existeNombreFenomeno(fenomeno);
	}

}
