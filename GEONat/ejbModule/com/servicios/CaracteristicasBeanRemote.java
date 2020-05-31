package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Caracteristica;
import com.exception.ServiciosException;

@Remote
public interface CaracteristicasBeanRemote {
	void create(Caracteristica caracteristica) throws ServiciosException;
	void update(Caracteristica caracteristica) throws ServiciosException;
	void delete(int id) throws ServiciosException;
	List<Caracteristica> obtenerTodos() throws ServiciosException;
	List<Caracteristica> obtenerTodosFiltro(String filtro) throws ServiciosException;
	boolean existeIdCaracteristica(Caracteristica caracteristica) throws ServiciosException;
	public Caracteristica obtenerUno(int id) throws ServiciosException;
}
