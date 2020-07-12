package com.presentacion.gui;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

// CLASE CON PATRÓN SINGLETON QUE CREA DATOS DE PRUEBA EN CASO DE NO EXISTIR AÚN LOS MISMOS


import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.DAO.interfaces.ICaracteristicaDAO;
import com.DAO.interfaces.IDetallesObservacionDAO;
import com.DAO.interfaces.IFenomenoDAO;
import com.DAO.interfaces.IObservacionDAO;
import com.DAO.interfaces.IUsuarioDAO;
import com.entities.Caracteristica;
import com.entities.DetalleObservacion;
import com.entities.Fenomeno;
import com.entities.Observacion;
import com.entities.TipoDato;
import com.entities.TipoDocumento;
import com.entities.TipoUsuario;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosGUI;
import com.presentacion.servicios.ServiciosUsuario;

public class DatosDePrueba {
	private static DatosDePrueba instance = new DatosDePrueba(); 
	
	private DatosDePrueba() {	
		
		creaUsuarios();
		
		//creaFenoYCarac();
		
		creaCaracteristicasYFenomenos();
		
		creaObservaciones();
	}


	//método estático que obtiene la instance de la clase
	public static DatosDePrueba getInstance() { 
		if ( instance==null) {
			instance = new DatosDePrueba();
		}
		return instance; 
	}
//-------  USUARIOS
	private void creaUsuarios() {
				
		 try {
			 IUsuarioDAO usuarioDAO = ServiciosGUI.getInstance().getUsuarioBean();
			 List<Usuario> listaUsuarios = usuarioDAO.obtenerTodos();
			 
			 if (listaUsuarios.size()<1) {
				System.out.println("NO existen datos de prueba de Usuarios: "+ listaUsuarios.size() );
				for (int i = 0; i < 10; i++) {
					Usuario u = new Usuario();
					u.setNombre("Nombre" + i);
					u.setApellido("Apellido" + i);
					u.setNombreUsuario("nombreUsuario" + i);
					u.setDireccion("Dirección" + i);
					u.setEmail("email" + i + "@email" + 1+ ".com");
					u.setNroDocumento("0000000" + i);
					u.setEstadoActivo(true);
					u.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
					u.setTipoDocumento(TipoDocumento.CARTA_CIUDADANIA);
					u.setPassword(Integer.toString(i));
										
					// Llamada al servicio remoto UsuarioBeanRemote para solicitarle que cree el usuario "usuario1"
					try {
						usuarioDAO.create(u);
						System.out.println("Usuario creado " + i);
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
	
	
	/*
	 * // Fenomenos y Caracteristicas
	 * 
	 * private void creaFenoYCarac() {
	 * 
	 * //Atiendo a Fenomenos try { IFenomenoDAO fenomenoDAO =
	 * ServiciosGUI.getInstance().getFenomenoBean(); List<Fenomeno> listarFenomenos
	 * = fenomenoDAO.obtenerTodos();
	 * 
	 * if (listarFenomenos.size()<1) {
	 * System.out.println("NO existen datos de prueba para Fenomenos: "+
	 * listarFenomenos.size() ); for (int i = 0; i < 10; i++) { Fenomeno f = new
	 * Fenomeno(); //f.setId_Fenomeno(100 + i);
	 * 
	 * Set<Caracteristica> caracteristicas = new HashSet<>();
	 * f.setCaracteristicas(caracteristicas); Set<Observacion> observaciones = new
	 * HashSet<>();
	 * 
	 * f.setDescripcion("Descripcion " + i); f.setNombre("Nombre " + i);
	 * f.setTelefono("Telefono " + i);
	 * 
	 * 
	 * 
	 * // Llamada al servicio remoto Fenomenos DAO para solicitar crear el Fenomeno
	 * "i" try { fenomenoDAO.create(f); System.out.println("Fenomeno " + i +
	 * " creado "); System.out.println("Vez: " + i + "Lista: " +
	 * listarFenomenos.size());
	 * 
	 * } catch (ServiciosException err) { err.printStackTrace(); } } } else {
	 * System.out.println("Ya existen datos de prueba para Fenomenos"); }
	 * 
	 * } catch (ServiciosException e) { e.printStackTrace(); } ;
	 * 
	 * //Atiendo a Caracteristicas try { ICaracteristicaDAO caracteristicaDAO =
	 * ServiciosGUI.getInstance().getCaracteristicaBean(); List<Caracteristica>
	 * listarCaracteristica = caracteristicaDAO.obtenerTodos();
	 * 
	 * if (listarCaracteristica.size()<1) {
	 * System.out.println("NO existen datos de prueba para Caracteristica: "+
	 * listarCaracteristica.size() ); for (int i = 0; i < 10; i++) { Caracteristica
	 * c = new Caracteristica(); //c.setId_Caracteristica(100 + i);
	 * c.setEtiqPresentacion("Etiqueta de presentacion " + i); c.setNombre("Nombre "
	 * + i); //c.setTipoDato(null); //c.setFenomeno(null);
	 * 
	 * // Llamada al servicio remoto Caracteristica DAO para solicitar crear la
	 * Caracteristica "i" try { caracteristicaDAO.create(c);
	 * System.out.println("Caracteristica " + i + " creada ");
	 * 
	 * } catch (ServiciosException err) { err.printStackTrace(); } } } else {
	 * System.out.println("Ya existen datos de prueba para Caracteristicas"); }
	 * 
	 * } catch (ServiciosException e) { e.printStackTrace(); } ;
	 * 
	 * 
	 * 
	 * }
	 * 
	 */
//------- CARACTERISTICAS Y FENOMENOS
	private void creaCaracteristicasYFenomenos() {
		//  CARACTERISTICAS Y FENOMENOS
		ICaracteristicaDAO caracteristicaBean = ServiciosGUI.getInstance().getCaracteristicaBean();
		IFenomenoDAO fenomenoBean = ServiciosGUI.getInstance().getFenomenoBean();		
		
		try {
			
			if (caracteristicaBean.obtenerTodos().size()<1) {
				
				System.out.println("NO existen datos de prueba: "+ caracteristicaBean.obtenerTodos().size() );
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
				cGranizo1.setNombre ("Diámetro de granizo");
				cGranizo1.setEtiqPresentacion(" ");
				cGranizo1.setTipoDato(TipoDato.NUMERICO);
				cGranizo1.setFenomeno(granizo);		
				
				
				
				Caracteristica cGranizo2 = new Caracteristica();
				cGranizo2.setNombre ("Duración de granizada (min)");
				cGranizo2.setEtiqPresentacion(" ");
				cGranizo2.setTipoDato(TipoDato.NUMERICO);
				cGranizo2.setFenomeno(granizo);	
				
				granizo.getCaracteristicas().add(cGranizo1);
				granizo.getCaracteristicas().add(cGranizo2);
				
				
				Caracteristica cLluvia = new Caracteristica();
				cLluvia.setNombre ("Milímetros de lluvia");
				cLluvia.setEtiqPresentacion(" ");
				cLluvia.setTipoDato(TipoDato.NUMERICO);
				cLluvia.setFenomeno(lluvia);
				
				lluvia.getCaracteristicas().add(cLluvia);
				
				Caracteristica cHelada = new Caracteristica();
				cHelada.setNombre ("Temperatura mínima de helada");
				cHelada.setEtiqPresentacion(" ");
				cHelada.setTipoDato(TipoDato.NUMERICO);
				cHelada.setFenomeno(helada);
				
				helada.getCaracteristicas().add(cHelada);
				
				Caracteristica cIncendio1 = new Caracteristica();
				cIncendio1.setNombre ("Hectáreas afectadas");
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
					
					fenomenoBean.create(granizo);
					fenomenoBean.create(lluvia);
					fenomenoBean.create(helada);
					fenomenoBean.create(incendio);				
					
					System.out.println("Fenomenos y Caracteristicas creados");
					
				} catch (ServiciosException err) {
					System.out.println("Error al crear FENOMENOS/CARACTERISTICAS");
					err.printStackTrace();
				}
			}
			else {
				System.out.println("Ya existen datos de prueba Caracteristicas y fenomenos");
			}
			
		} catch (ServiciosException e) {
			e.printStackTrace();
		} ;
	} 

//------- OBSERVACIONES
	private void creaObservaciones() {
		IObservacionDAO observacionBean = ServiciosGUI.getInstance().getObservacionBean();
		ICaracteristicaDAO caracteristicaBean = ServiciosGUI.getInstance().getCaracteristicaBean();
		
	//	IObservacionDAO observacionBean=null;
		/*
		 * try { observacionBean = (IObservacionDAO)
		 * InitialContext.doLookup("/GEONat/ObservacionDAO!com.DAO.IObservacionDAO"); }
		 * catch (NamingException e) { e.printStackTrace(); }
		 */	
		 try {
			if (observacionBean.obtenerTodos().size()<1) {
				System.out.println("NO existen datos de prueba de Observaciones: " + observacionBean.obtenerTodos().size() );
				
				Observacion o1 = new Observacion();
				System.out.println("1 ");
				o1.setFenomeno(obtenerFenomeno(2));
				System.out.println("2 ");
				//o1.setGeolocalizacion(null);
				o1.setLocalidad(null);
				o1.setDescripcion("Se observó incendio forestal de monte de eucaliptus");
				o1.setFecha(java.util.Calendar.getInstance().getTime());
				
				observacionBean.create(o1);
				
				
//				ICaracteristicaDAO caracteristicaBean=null;		
				/*
				 * try { caracteristicaBean = (ICaracteristicaDAO) InitialContext.doLookup(
				 * "/GEONat/CaracteristicaDAO!com.DAO.ICaracteristicaDAO"); } catch
				 * (NamingException e) { e.printStackTrace(); }
				 */				
				
				Caracteristica c1 = new Caracteristica();
				c1 = caracteristicaBean.obtenerUno(1);
				
		
				DetalleObservacion d1 = new DetalleObservacion();
				d1.setFecha(java.util.Calendar.getInstance().getTime());
				d1.setValorNumerico( 2.5f);
				d1.setCaracteristica(c1);
				d1.setObservacion(o1);
				
				o1.getDetalleObservaciones().add(d1);
				c1.getDetalleObservaciones().add(d1);
				IDetallesObservacionDAO detallesObservacionesBean = ServiciosGUI.getInstance().getDetallesObservacionesBean();
				
//				IDetallesObservacionDAO detallesObservacionesBean=null;		
//				try {
//					detallesObservacionesBean = (IDetallesObservacionDAO) InitialContext.doLookup("/GEONat/DetallesObservacionDAO!com.DAO.IDetallesObservacionDAO");
//				} catch (NamingException e) {
//					e.printStackTrace();
//				}
				
				detallesObservacionesBean.create(d1);
				
				
			}
			else {
				System.out.println("Ya existen datos de prueba observaciones");
			}

		} catch (ServiciosException e) {
			e.printStackTrace();
		} ;
	}


	private Fenomeno obtenerFenomeno(Integer id) throws ServiciosException {
		
		IFenomenoDAO fenomenoBean1 = ServiciosGUI.getInstance().getFenomenoBean();
		//IFenomenoDAO fenomenoBean1 = null;
		//fenomenoBean1 = (IFenomenoDAO) InitialContext.doLookup("/GEONat/FenomenosDAO!com.DAO.FenomenoDAO11");
		
		return fenomenoBean1.obtenerUno(id);
	}








// HELPER METHODS



}
	
