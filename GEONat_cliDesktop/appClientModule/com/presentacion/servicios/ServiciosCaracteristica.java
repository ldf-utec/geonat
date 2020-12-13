package com.presentacion.servicios;

import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.DAO.interfaces.ICaracteristicaDAO;
import com.entities.Caracteristica;
import com.exception.ServiciosException;

public class ServiciosCaracteristica {
	private static ServiciosCaracteristica instance = new ServiciosCaracteristica();
	ServiciosGUI servicios = ServiciosGUI.getInstance();
	
	
	public ServiciosCaracteristica() {
		super();
	}
	
	public static ServiciosCaracteristica getInstance() {
		if (instance==null) {
			instance = new ServiciosCaracteristica();
		}	
		return instance;
	}
	
	
	
	public void create(Caracteristica caracteristica) throws ServiciosException {
		try {
			servicios.caracteristicaBean.create(caracteristica);	
		} catch (PersistenceException e) {	
			throw new ServiciosException("Error al crear" );	
		}
	}
    

	public void update(Caracteristica caracteristica) throws ServiciosException {
		try {
			servicios.caracteristicaBean.update(caracteristica);
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al actualizar");
		}
	}
    

	public void delete(int id) throws ServiciosException {
		try {
			servicios.caracteristicaBean.delete(id);
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al borrar");
		}	
	}
    

	public List<Caracteristica> obtenerTodos() throws ServiciosException {
		try {
		
			List<Caracteristica> l = servicios.caracteristicaBean.obtenerTodos();	
			return l;
		} catch (Exception e) {
			System.out.println("Error en serviciosCaracteristicas ObtenerTodos(). " + e.getMessage());
			e.printStackTrace();
			
		}
		return null;
	}
	

	public List<Caracteristica> obtenerTodosFiltro(String filtro) throws ServiciosException {
		return servicios.caracteristicaBean.obtenerTodosFiltro(filtro);
	}
	
	
	public Caracteristica obtenerUno(int id) throws ServiciosException {
		try {
			return servicios.caracteristicaBean.obtenerUno(id);
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al consultar");
		}
	}
	
	public boolean existeNombreCaracteristica(String nombre) throws ServiciosException {
		try {
			return servicios.caracteristicaBean.existeNombreCaracteristica(nombre);
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al consultar");
		}

	}
	
	
	
}
