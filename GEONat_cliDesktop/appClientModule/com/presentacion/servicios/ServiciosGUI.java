package com.presentacion.servicios;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.DAO.interfaces.ICaracteristicaDAO;
import com.DAO.interfaces.IDetallesObservacionDAO;
import com.DAO.interfaces.IFenomenoDAO;
import com.DAO.interfaces.IObservacionDAO;
import com.DAO.interfaces.IUsuarioDAO;

public class ServiciosGUI {
	// Se utiliza el patrón Singleton, para que esta clase tenga una instancia única que provea los servicios a toda la capa
	private static ServiciosGUI instance;
	
	IUsuarioDAO usuarioBean;
	ICaracteristicaDAO caracteristicaBean;
	IFenomenoDAO fenomenoBean;
	IObservacionDAO observacionBean;
	IDetallesObservacionDAO detallesObservacionesBean;
	
	
	// Constructor
	private ServiciosGUI() {
		try {
			iniciarInterfases();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// Getter de la instacia única
	public static ServiciosGUI getInstance () {
		if (instance==null) {
			instance = new ServiciosGUI();
		}
		return instance;
	}
	
			
	
	public void iniciarInterfases() throws NamingException {
		try {
			//InitialContext ic = new InitialContext();
			
			// DAO Usuario
			usuarioBean = (IUsuarioDAO) InitialContext.doLookup("/GEONat/UsuarioDAO!com.DAO.interfaces.IUsuarioDAO");
			
			// DAO Caracteristica
			caracteristicaBean = (ICaracteristicaDAO) InitialContext.doLookup("/GEONat/CaracteristicaDAO!com.DAO.interfaces.ICaracteristicaDAO");
				
			// DAO Fenomeno
			fenomenoBean = (IFenomenoDAO) InitialContext.doLookup("/GEONat/FenomenosDAO!com.DAO.interfaces.IFenomenoDAO");

			// DAO Observacion
			observacionBean = (IObservacionDAO) InitialContext.doLookup("/GEONat/ObservacionDAO!com.DAO.interfaces.IObservacionDAO");
			
			// DAO DetalleObservacion
			detallesObservacionesBean = (IDetallesObservacionDAO) InitialContext.doLookup("/GEONat/DetallesObservacionDAO!com.DAO.interfaces.IDetallesObservacionDAO");

				
			//ic.close();
		} catch (Exception e) {
			System.out.println("Error al iniciar interfases. " +  e.getMessage());
			// TODO: handle exception
		}
		
		
	}

	
	
	// GETTERs y SETTERs
	public IUsuarioDAO getUsuarioBean() {
		return usuarioBean;
	}


	public void setUsuarioBean(IUsuarioDAO usuarioBean) {
		this.usuarioBean = usuarioBean;
	}


	public ICaracteristicaDAO getCaracteristicaBean() {
		return caracteristicaBean;
	}


	public void setCaracteristicaBean(ICaracteristicaDAO caracteristicaBean) {
		this.caracteristicaBean = caracteristicaBean;
	}


	public IFenomenoDAO getFenomenoBean() {
		return fenomenoBean;
	}


	public void setFenomenoBean(IFenomenoDAO fenomenoBean) {
		this.fenomenoBean = fenomenoBean;
	}


	public IObservacionDAO getObservacionBean() {
		return observacionBean;
	}


	public void setObservacionBean(IObservacionDAO observacionBean) {
		this.observacionBean = observacionBean;
	}


	public IDetallesObservacionDAO getDetallesObservacionesBean() {
		return detallesObservacionesBean;
	}


	public void setDetallesObservacionesBean(IDetallesObservacionDAO detallesObservacionesBean) {
		this.detallesObservacionesBean = detallesObservacionesBean;
	}

	
}
