package com.presentacion.servicios;

import java.util.List;

import javax.persistence.PersistenceException;

import com.entities.Observacion;
import com.exception.ServiciosException;

public class ServiciosObservacion {
	private static ServiciosObservacion instance = new ServiciosObservacion();
	ServiciosGUI servicios = ServiciosGUI.getInstance();
	
	public ServiciosObservacion() {
		super();
	}
	
	public static ServiciosObservacion getInstance() {
		if (instance==null) {
			instance = new ServiciosObservacion();
		}	
		return instance;
	}
	
	// Crear nuevo
	public void create(Observacion observacion) throws ServiciosException {
		try {
			
			servicios.observacionBean.create(observacion);
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al crear observacion" );
		}
	}

	
	// Actualizar
	public void update(Observacion observacion) throws ServiciosException {
		try {
			
			servicios.observacionBean.update(observacion);
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al actualizar el observacion");
		}		
	}

	// Eliminar por Id
	public void delete(int id) throws ServiciosException {
		try {
			servicios.observacionBean.delete(id);
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al borrar el observacion");
		}		
	}
		

	// Obtener Todos
	public List<Observacion> obtenerTodos() throws ServiciosException {
		try {
			
			List<Observacion> lista = servicios.observacionBean.obtenerTodos();
			return lista;
			
		} catch (Exception e) {
			throw new ServiciosException("Error al obtener datos");
		}
	}
	


}
