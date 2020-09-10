package com.presentacion.gui;


import java.util.List;
/*import java.util.Set;
import java.util.HashSet;

// CLASE CON PATR�N SINGLETON QUE CREA DATOS DE PRUEBA EN CASO DE NO EXISTIR A�N LOS MISMOS


import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.DAO.interfaces.ICaracteristicaDAO;
import com.DAO.interfaces.IDetallesObservacionDAO;
import com.DAO.interfaces.IFenomenoDAO;
import com.DAO.interfaces.IObservacionDAO;
import com.DAO.interfaces.IUsuarioDAO;
*/
import com.entities.Caracteristica;
import com.entities.DetalleObservacion;
import com.entities.Fenomeno;
import com.entities.Observacion;
import com.entities.TipoDato;
import com.entities.TipoDocumento;
import com.entities.TipoUsuario;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosCaracteristica;
import com.presentacion.servicios.ServiciosDetalleObservaciones;
import com.presentacion.servicios.ServiciosFenomeno;
//import com.presentacion.servicios.ServiciosGUI;
import com.presentacion.servicios.ServiciosObservacion;
import com.presentacion.servicios.ServiciosUsuario;

/**
 * @author Victor
 *
 */
public class DatosDePrueba {
	private static DatosDePrueba instance = new DatosDePrueba(); 
	
	private DatosDePrueba() {	
		
		creaUsuarios();
		creaCaracteristicasYFenomenos();
		//creaObservaciones();
	}


	//m�todo est�tico que obtiene la instance de la clase
	public static DatosDePrueba getInstance() { 
		if ( instance==null) {
			instance = new DatosDePrueba();
		}
		return instance; 
	}
//-------  USUARIOS
	private void creaUsuarios() {
				
		 try {
			 
			 //IUsuarioDAO usuarioDAO = ServiciosGUI.getInstance().getUsuarioBean();
			 ServiciosUsuario serviciosUsuario = ServiciosUsuario.getInstance();
			 List<Usuario> listaUsuarios = serviciosUsuario.obtenerTodos();
			 
			 if (listaUsuarios.size()<6) {
				System.out.println("NO existen datos de prueba de Usuarios: "+ listaUsuarios.size() );
				for (int i = 0; i < 10; i++) {
					Usuario u = new Usuario();
					u.setNombre("Nombre" + i);
					u.setApellido("Apellido" + i);
					u.setNombreUsuario("nombreUsuario" + i);
					u.setDireccion("Direcci�n" + i);
					u.setEmail("email" + i + "@email" + 1+ ".com");
					u.setNroDocumento("0000000" + i);
					u.setEstadoActivo(true);
					u.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
					u.setTipoDocumento(TipoDocumento.CARTA_CIUDADANIA);
					u.setPassword(Integer.toString(i));
										
					// Llamada al servicio remoto UsuarioBeanRemote para solicitarle que cree el usuario "usuario1"
					try {
						serviciosUsuario.create(u);
						System.out.println("Usuario creado " + i);
						System.out.println("Vez: " + i + "Lista: " + listaUsuarios.size());
					} catch (ServiciosException err) {
						err.printStackTrace();
					}
				}
			}
			else {
				System.out.println("Ya existen datos de prueba Usuarios");
			}
			
		} catch (ServiciosException e) {
			e.printStackTrace();
		} ;
	}
	
	
	
//------- CARACTERISTICAS Y FENOMENOS
	private void creaCaracteristicasYFenomenos() {

		ServiciosCaracteristica serviciosCaracteristicas = ServiciosCaracteristica.getInstance();
		ServiciosFenomeno serviciosFenomeno = ServiciosFenomeno.getInstance();
		
				
		 try {
			 List<Caracteristica> listaCaracteristicas = serviciosCaracteristicas.obtenerTodos();
			 
			 if (listaCaracteristicas==null) {
				 System.out.println("DatosDePrueba: no se obtuvieron caracteristicas");
				 return;
			 }
			 
			 if (listaCaracteristicas.size() < 1) {
				 
				System.out.println("NO existen datos de prueba");
				// CREAR FENOMENOS
				Fenomeno granizo = new Fenomeno();
				granizo.setNombre("Granizo");
				granizo.setTelefono("44426897");
				granizo.setDescripcion("Granizada");
										
				Fenomeno lluvia = new Fenomeno();
				lluvia.setNombre("Lluvia");
				lluvia.setTelefono("03726897");
				lluvia.setDescripcion("Lluvias");
									
				Fenomeno helada = new Fenomeno();
				helada.setNombre("Helada");
				helada.setTelefono("099826897");
				helada.setDescripcion("Helada");
									
				Fenomeno incendio = new Fenomeno();
				incendio.setNombre("Incendio");
				incendio.setTelefono("104");
				incendio.setDescripcion("Incendio Forestal");
				
									
							
				// CREAR CARACTERISTICAS	
				Caracteristica cGranizo1 = new Caracteristica();
				cGranizo1.setNombre ("Di�metro de granizo");
				cGranizo1.setEtiqPresentacion(" ");
				cGranizo1.setTipoDato(TipoDato.NUMERICO);
				cGranizo1.setFenomeno(granizo);		
				
				
				
				Caracteristica cGranizo2 = new Caracteristica();
				cGranizo2.setNombre ("Duraci�n de granizada (min)");
				cGranizo2.setEtiqPresentacion(" ");
				cGranizo2.setTipoDato(TipoDato.NUMERICO);
				cGranizo2.setFenomeno(granizo);	
				
				granizo.getCaracteristicas().add(cGranizo1);
				granizo.getCaracteristicas().add(cGranizo2);
				
				
				Caracteristica cLluvia = new Caracteristica();
				cLluvia.setNombre ("Mil�metros de lluvia");
				cLluvia.setEtiqPresentacion(" ");
				cLluvia.setTipoDato(TipoDato.NUMERICO);
				cLluvia.setFenomeno(lluvia);
				
				lluvia.getCaracteristicas().add(cLluvia);
				
				Caracteristica cHelada = new Caracteristica();
				cHelada.setNombre ("Temperatura m�nima de helada");
				cHelada.setEtiqPresentacion(" ");
				cHelada.setTipoDato(TipoDato.NUMERICO);
				cHelada.setFenomeno(helada);
				
				helada.getCaracteristicas().add(cHelada);
				
				Caracteristica cIncendio1 = new Caracteristica();
				cIncendio1.setNombre ("Hect�reas afectadas");
				cIncendio1.setEtiqPresentacion(" ");
				cIncendio1.setTipoDato(TipoDato.NUMERICO);
				cIncendio1.setFenomeno(incendio);
				
				Caracteristica cIncendio2 = new Caracteristica();
				cIncendio2.setNombre ("Tipo de flora");
				cIncendio2.setEtiqPresentacion(" ");
				cIncendio2.setTipoDato(TipoDato.TEXTO);
				cIncendio2.setFenomeno(incendio);
				
				incendio.getCaracteristicas().add(cIncendio1);
				incendio.getCaracteristicas().add(cIncendio2);
									
				// Llamada al servicio remoto para crear los registros
				try {
					
					serviciosFenomeno.create(granizo);
					serviciosFenomeno.create(lluvia);
					serviciosFenomeno.create(helada);
					serviciosFenomeno.create(incendio);				
					
					System.out.println("Fenomenos y Caracteristicas creados");
					
				} catch (ServiciosException err) {
					System.out.println("Error al crear FENOMENOS/CARACTERISTICAS");
					err.printStackTrace();
				}
			}
			else {
				System.out.println("Ya existen datos de prueba Caracteristicas y fenomenos");
			}
			
		} catch (Exception e) {
			System.out.println("Error al crear datos de prueba: Caracter�sticas y Fen�menos. ");
			e.printStackTrace();
		} ;
	} 

//------- OBSERVACIONES
	private void creaObservaciones() {
		
		ServiciosObservacion serviciosObservacion = ServiciosObservacion.getInstance();
		 try {
			if (serviciosObservacion.obtenerTodos().size()<1) {
				System.out.println("NO existen datos de prueba de Observaciones: " + serviciosObservacion.obtenerTodos().size() );
				
				Observacion o1 = new Observacion();

				ServiciosUsuario serviciosUsuario = ServiciosUsuario.getInstance();
								
				Usuario u = new Usuario();
				u.setNombreUsuario("nombreUsuario0");
				o1.setUsuarioRegistro(serviciosUsuario.obtenerUno(u));
				u.setNombreUsuario("nombreUsuario1");	
				o1.setUsuarioRevision(serviciosUsuario.obtenerUno(u));

				System.out.println("obtuve usuarios necesarios");
				
				o1.setFenomeno(obtenerFenomeno(2));
				o1.setLocalidad(null);
				o1.setGeoLatitud(null);
				o1.setGeoLongitud(null);
				o1.setDescripcion("Se observ� incendio forestal de monte de eucaliptus");
				o1.setFecha(java.util.Calendar.getInstance().getTime());
				o1.setFechaRevision(java.util.Calendar.getInstance().getTime());
				o1.setFiabilidadRevision(null);
				o1.setComentarioRevision("Revisado OK");

				System.out.println("Voy a grabar Observacion");
				serviciosObservacion.create(o1);
				System.out.println("Grabare Observacion");
				//
				ServiciosCaracteristica serviciosCaracteristicas = ServiciosCaracteristica.getInstance();
				
				Caracteristica c1 = new Caracteristica();
				c1 = serviciosCaracteristicas.obtenerUno(1);
				
				DetalleObservacion d1 = new DetalleObservacion();
				d1.setFecha(java.util.Calendar.getInstance().getTime());
				d1.setValorNumerico( 2.5f);
				d1.setCaracteristica(c1);
				d1.setObservacion(o1);
				
				
				//
				ServiciosDetalleObservaciones serviciosDetalleObservaciones = ServiciosDetalleObservaciones.getInstance();
				serviciosDetalleObservaciones.create(d1);

				o1.getDetalleObservaciones().add(d1);
				c1.getDetalleObservaciones().add(d1);
				
				
				
			}
			else {
				System.out.println("Ya existen datos de prueba observaciones");
			}

		} catch (ServiciosException e) {
			e.printStackTrace();
		} ;
	}


	private Fenomeno obtenerFenomeno(Integer id) throws ServiciosException {

		ServiciosFenomeno serviciosFenomeno = ServiciosFenomeno.getInstance();
		Fenomeno fenomeno = serviciosFenomeno.obtenerUno(id);
		return fenomeno;
	}

	
	
	// HELPER METHODS
	
	
	
}
	
