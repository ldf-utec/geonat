package com.DAO.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Fenomeno;
import com.exception.ServiciosException;

@Remote
public interface IFenomenoDAO {

	void create(Fenomeno fenomeno) throws ServiciosException;

	void update(Fenomeno fenomeno) throws ServiciosException;

	void delete(int id) throws ServiciosException;

	List<Fenomeno> obtenerTodos() throws ServiciosException;

	List<Fenomeno> obtenerTodosFiltro(String filtro) throws ServiciosException;

	boolean existeNombreFenomeno(Fenomeno fenomeno) throws ServiciosException;

	boolean existeID(int id) throws ServiciosException;

	//Fenomeno obtenerUno(int id) throws ServiciosException;

	List<Fenomeno> obtenerUnoID(Integer filtro) throws ServiciosException;

	Fenomeno obtenerUno(int id) throws ServiciosException;

}
