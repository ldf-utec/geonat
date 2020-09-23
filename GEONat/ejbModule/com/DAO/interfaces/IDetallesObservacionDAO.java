package com.DAO.interfaces;



import java.util.List;

import javax.ejb.Remote;

import com.entities.DetalleObservacion;
import com.exception.ServiciosException;

@Remote
public interface IDetallesObservacionDAO {
	
	DetalleObservacion create(DetalleObservacion detalleObservacion) throws ServiciosException;

	void update(DetalleObservacion detalleObservacion) throws ServiciosException;

	void delete(int id) throws ServiciosException;

	DetalleObservacion obtenerUno(int id) throws ServiciosException;

	List<DetalleObservacion> obtenerTodos() throws ServiciosException;

	List<DetalleObservacion> obtenerTodosFiltro(String filtro) throws ServiciosException;

	
	
}
