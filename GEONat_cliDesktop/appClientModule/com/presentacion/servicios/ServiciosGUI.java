package com.presentacion.servicios;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.DAO.interfaces.ICaracteristicaDAO;
import com.DAO.interfaces.IDetallesObservacionDAO;
import com.DAO.interfaces.IFenomenoDAO;
import com.DAO.interfaces.IObservacionDAO;
import com.DAO.interfaces.IUsuarioDAO;

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
		usuarioBean = (IUsuarioDAO) InitialContext.doLookup("/GEONat/UsuarioDAO!com.DAO.IUsuarioDAO");
		
		// DAO Caracteristica
		caracteristicaBean = (ICaracteristicaDAO) InitialContext.doLookup("/GEONat/CaracteristicaDAO!com.DAO.ICaracteristicaDAO");
			
		// DAO Fenomeno
		fenomenoBean = (IFenomenoDAO) InitialContext.doLookup("/GEONat/FenomenosDAO!com.DAO.FenomenoDAO");

		// DAO Observacion
		observacionBean = (IObservacionDAO) InitialContext.doLookup("/GEONat/ObservacionDAO!com.DAO.IObservacionDAO");
		
		// DAO DetalleObservacion
		detallesObservacionesBean = (IDetallesObservacionDAO) InitialContext.doLookup("/GEONat/DetallesObservacionDAO!com.DAO.IDetallesObservacionDAO");

			
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
