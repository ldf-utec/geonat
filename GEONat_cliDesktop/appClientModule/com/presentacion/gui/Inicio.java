package com.presentacion.gui;

import java.util.List;

import com.entities.TipoDocumento;
import com.entities.TipoUsuario;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosUsuario;

public class Inicio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		creaUsuarios();
		Login p = new Login();
		p.setVisible(true);
	}

	private static void creaUsuarios() {
		
		 ServiciosUsuario serviciosUsuario = ServiciosUsuario.getInstance();
		 
		 	Usuario u = new Usuario();
				u.setNombre("administrador");
				u.setApellido("administrador");
				u.setNombreUsuario("administrador");
				u.setDireccion("administrador");
				u.setEmail("administrador@administrador.com");
				u.setNroDocumento("0000000");
				u.setEstadoActivo(true);
				u.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
				u.setTipoDocumento(TipoDocumento.CI);
				u.setPassword("administrador");
									
				// Llamada al servicio remoto UsuarioBeanRemote para solicitarle que cree el usuario "usuario1"
				try {
					serviciosUsuario.create(u);
					System.out.println("Usuario creado ");
					//System.out.println("Vez: " + i + "Lista: " + listaUsuarios.size());
				} catch (ServiciosException err) {
					err.printStackTrace();
				}
				
				Usuario experto = new Usuario();
				experto.setNombre("experto");
				experto.setApellido("experto");
				experto.setNombreUsuario("experto");
				experto.setDireccion("experto");
				experto.setEmail("experto@experto.com");
				experto.setNroDocumento("000000012");
				experto.setEstadoActivo(true);
				experto.setTipoUsuario(TipoUsuario.EXPERTO);
				experto.setTipoDocumento(TipoDocumento.CI);
				experto.setPassword("experto");
									
				// Llamada al servicio remoto UsuarioBeanRemote para solicitarle que cree el usuario "usuario1"
				try {
					serviciosUsuario.create(experto);
					System.out.println("Usuario creado ");
					//System.out.println("Vez: " + i + "Lista: " + listaUsuarios.size());
				} catch (ServiciosException err) {
					err.printStackTrace();
				}
				
				Usuario ong = new Usuario();
				ong.setNombre("ong");
				ong.setApellido("ong");
				ong.setNombreUsuario("ong");
				ong.setDireccion("ong");
				ong.setEmail("ong@ong.com");
				ong.setNroDocumento("000000021");
				ong.setEstadoActivo(true);
				ong.setTipoUsuario(TipoUsuario.ONG);
				ong.setTipoDocumento(TipoDocumento.CI);
				ong.setPassword("ong");
									
				// Llamada al servicio remoto UsuarioBeanRemote para solicitarle que cree el usuario "usuario1"
				try {
					serviciosUsuario.create(ong);
					System.out.println("Usuario creado ");
					//System.out.println("Vez: " + i + "Lista: " + listaUsuarios.size());
				} catch (ServiciosException err) {
					err.printStackTrace();
				}
				
				Usuario organismoPrivado = new Usuario();
				organismoPrivado.setNombre("organismoprivado");
				organismoPrivado.setApellido("organismoprivado");
				organismoPrivado.setNombreUsuario("organismoprivado");
				organismoPrivado.setDireccion("organismoprivado");
				organismoPrivado.setEmail("organismoprivado@organismoprivado.com");
				organismoPrivado.setNroDocumento("000000023");
				organismoPrivado.setEstadoActivo(true);
				organismoPrivado.setTipoUsuario(TipoUsuario.ORGANISMOPRIVADO);
				organismoPrivado.setTipoDocumento(TipoDocumento.CI);
				organismoPrivado.setPassword("organismoprivado");
									
				// Llamada al servicio remoto UsuarioBeanRemote para solicitarle que cree el usuario "usuario1"
				try {
					serviciosUsuario.create(organismoPrivado);
					System.out.println("Usuario creado ");
					//System.out.println("Vez: " + i + "Lista: " + listaUsuarios.size());
				} catch (ServiciosException err) {
					err.printStackTrace();
				}
				Usuario usuario = new Usuario();
				usuario.setNombre("usuario");
				usuario.setApellido("usuario");
				usuario.setNombreUsuario("usuario");
				usuario.setDireccion("usuario");
				usuario.setEmail("usuario@usuario.com");
				usuario.setNroDocumento("000000024");
				usuario.setEstadoActivo(true);
				usuario.setTipoUsuario(TipoUsuario.USUARIO);
				usuario.setTipoDocumento(TipoDocumento.CI);
				usuario.setPassword("usuario");
									
				// Llamada al servicio remoto UsuarioBeanRemote para solicitarle que cree el usuario "usuario1"
				try {
					serviciosUsuario.create(usuario);
					System.out.println("Usuario creado ");
					//System.out.println("Vez: " + i + "Lista: " + listaUsuarios.size());
				} catch (ServiciosException err) {
					err.printStackTrace();
				}
	}
}
