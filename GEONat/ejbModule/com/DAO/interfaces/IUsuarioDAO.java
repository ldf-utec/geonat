package com.DAO.interfaces;

import java.util.List;

import javax.ejb.Remote;
import com.entities.Usuario;
import com.exception.ServiciosException;

@Remote
public interface IUsuarioDAO {

	void create(Usuario usuario) throws ServiciosException;
	void update(Usuario usuario) throws ServiciosException;
	void delete(int id) throws ServiciosException;
	List <Usuario> obtenerTodos() throws ServiciosException;
	List <Usuario> obtenerTodos(String filtro) throws ServiciosException;
	boolean existeNombreUsuario(Usuario usuario) throws ServiciosException;
	public Usuario obtenerUno(Usuario usuario) throws ServiciosException;
	List<Usuario> obtenerLogin(Usuario usuario) throws ServiciosException; // esto se agrego para el login con sus tipo de usuario
	public Usuario obtenerUno(Integer id) throws ServiciosException;
	
}
