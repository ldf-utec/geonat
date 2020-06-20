package com.presentacion.servicios;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.serviciosDAO.interfaces.ICaracteristicaDAO;
import com.serviciosDAO.interfaces.IDetallesObservacionDAO;
import com.serviciosDAO.interfaces.IFenomenoDAO;
import com.serviciosDAO.interfaces.IObservacionDAO;
import com.serviciosDAO.interfaces.IUsuarioDAO;

public class ServiciosGUI {
	
	// Constructor
	public ServiciosGUI() {
		try {
			iniciarInterfases();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	IUsuarioDAO usuarioBean;
	ICaracteristicaDAO caracteristicaBean;
	IFenomenoDAO fenomenoBean;
	IObservacionDAO observacionBean;
	IDetallesObservacionDAO detallesObservacionesBean;
		
	
	public void iniciarInterfases() throws NamingException {
		
		//InitialContext ic = new InitialContext();
		
		// DAO Usuario
		usuarioBean = (IUsuarioDAO) InitialContext.doLookup("/GEONat/UsuarioDAO!com.serviciosDAO.IUsuarioDAO");
		
		// DAO Caracteristica
		caracteristicaBean = (ICaracteristicaDAO) InitialContext.doLookup("/GEONat/CaracteristicaDAO!com.serviciosDAO.ICaracteristicaDAO");
			
		// DAO Fenomeno
		fenomenoBean = (IFenomenoDAO) InitialContext.doLookup("/GEONat/FenomenosDAO!com.serviciosDAO.FenomenoDAO");

		// DAO Observacion
		observacionBean = (IObservacionDAO) InitialContext.doLookup("/GEONat/ObservacionDAO!com.serviciosDAO.IObservacionDAO");
		
		// DAO DetalleObservacion
		detallesObservacionesBean = (IDetallesObservacionDAO) InitialContext.doLookup("/GEONat/DetallesObservacionDAO!com.serviciosDAO.IDetallesObservacionDAO");

			
		//ic.close();
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
