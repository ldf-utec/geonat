package com.DAO.interfaces;

import java.util.List;
import javax.ejb.Remote;
import com.entities.Observacion;
import com.exception.ServiciosException;

@Remote
public interface IObservacionDAO {

	Observacion create(Observacion observacion) throws ServiciosException;

	void update(Observacion observacion) throws ServiciosException;

	void delete(int id) throws ServiciosException;

	List<Observacion> obtenerTodos() throws ServiciosException;

	//List<Observacion> obtenerTodosFiltro(String filtro) throws ServiciosException;

	// No es necesario, ya que se puede hacer mediante ObtenerTodosFiltro(int id),  verificando si retorna una lista con size>0
	//boolean existeNombreObservacion(Observacion observacion) throws ServiciosException;

	// No es necesario, ya que se puede hacer mediante ObtenerUno(int id),  verificando si retorna null
	//boolean existeId(int id) throws ServiciosException;

	Observacion obtenerUno(int id) throws ServiciosException;
}
