package com.presentacion.servicios;

import java.util.List;

import javax.persistence.PersistenceException;

import com.entities.DetalleObservacion;
import com.exception.ServiciosException;

public class ServiciosDetalleObservaciones {
	private static ServiciosDetalleObservaciones instance = new ServiciosDetalleObservaciones();
	ServiciosGUI servicios = ServiciosGUI.getInstance();
	
	public ServiciosDetalleObservaciones() {
		super();
	}
	
	public static ServiciosDetalleObservaciones getInstance() {
		if (instance==null) {
			instance = new ServiciosDetalleObservaciones();
		}	
		return instance;
	}
	
	// Crear nuevo
	public void create(DetalleObservacion detallesObservaciones) throws ServiciosException {
		try {
			
			servicios.detallesObservacionesBean.create(detallesObservaciones);
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al crear detallesObservacion" );
		}
	}

	
	// Actualizar
	public void update(DetalleObservacion detallesObservacion) throws ServiciosException {
		try {
			
			servicios.detallesObservacionesBean.update(detallesObservacion);
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al actualizar el detallesObservacion");
		}		
	}

	// Eliminar por Id
	public void delete(int id) throws ServiciosException {
		try {
			servicios.detallesObservacionesBean.delete(id);
			
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al borrar el detallesObservacion");
		}		
	}
		

	// Obtener Todos
	public List<DetalleObservacion> obtenerTodos() throws ServiciosException {
		try {
			
			List<DetalleObservacion> lista = servicios.detallesObservacionesBean.obtenerTodos();
			return lista;
			
		} catch (Exception e) {
			throw new ServiciosException("Error al obtener datos");
		}
	}
	


}
