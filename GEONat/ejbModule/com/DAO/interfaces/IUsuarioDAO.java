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
	public Usuario obtenerUno(Integer id) throws ServiciosException;
	Usuario obtenerPorNombre(String nombreUsuario) throws ServiciosException;
	Usuario obtenerPorDocumento(String documento) throws ServiciosException;
	Usuario obtenerPorNombreOld(String nombreUsuario) throws ServiciosException;
	List<Usuario> obtenerPorEstado(Boolean estado) throws ServiciosException;

}
