package com.presentacion.servicios;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import com.Enums.Criticidad;
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
	public Observacion create(Observacion observacion) throws ServiciosException {
		try {
			
			return servicios.observacionBean.create(observacion);
			
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
	
	// Obtener por criticidad y rango de fechas
	public List<Observacion> obtenerPorCriticidadRangoFechas(Criticidad criticidad, Date startDate, Date endDate) throws ServiciosException {
		try {
			
			List<Observacion> lista = servicios.observacionBean.obtenerPorCriticidadRangoFechas(criticidad, startDate, endDate);
			return lista;
			
		} catch (Exception e) {
			throw new ServiciosException("Error al obtener datos");
		}
	}
	
	// Obtener por criticidad
	public List<Observacion> obtenerPorCriticidad(Criticidad criticidad) throws ServiciosException {
		try {
			
			List<Observacion> lista = servicios.observacionBean.obtenerPorCriticidad(criticidad);
			return lista;
			
		} catch (Exception e) {
			throw new ServiciosException("Error al obtener datos");
		}
	}

}
