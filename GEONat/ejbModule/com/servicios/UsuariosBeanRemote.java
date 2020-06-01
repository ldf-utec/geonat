package com.servicios;

import java.util.List;

import javax.ejb.Remote;
import com.entities.Usuario;
import com.exception.ServiciosException;

@Remote
public interface UsuariosBeanRemote {

	void create(Usuario usuario) throws ServiciosException;
	void update(Usuario usuario) throws ServiciosException;
	void delete(int id) throws ServiciosException;
	List <Usuario> obtenerTodos() throws ServiciosException;
	List <Usuario> obtenerTodos(String filtro) throws ServiciosException;
	boolean existeNombreUsuario(Usuario usuario) throws ServiciosException;
	public Usuario obtenerUno(Usuario usuario) throws ServiciosException;
	
}