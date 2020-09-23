package com.entities;

import java.io.Serializable;
import java.lang.Integer;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ID class for entity: DetalleObservacion
 *
 */ 
@Embeddable
public class DetalleObservacionPK  implements Serializable {   
   
	@Column(name = "Id_Observacion")
	private Integer Id_Observacion;       
	
	@Column(name = "Id_Caracteristica")
	private Integer Id_Caracteristica;
	
	private static final long serialVersionUID = 1L;

	public DetalleObservacionPK() {}

	

	public DetalleObservacionPK(Integer id_Observacion, Integer id_Caracteristica) {
		super();
		Id_Observacion = id_Observacion;
		Id_Caracteristica = id_Caracteristica;
	}



	public Integer getId_Observacion() {
		return this.Id_Observacion;
	}

	public void setId_Observacion(Integer Id_Observacion) {
		this.Id_Observacion = Id_Observacion;
	}
	

	public Integer getId_Caracteristica() {
		return this.Id_Caracteristica;
	}

	public void setId_Caracteristica(Integer Id_Caracteristica) {
		this.Id_Caracteristica = Id_Caracteristica;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof DetalleObservacionPK)) {
			return false;
		}
		DetalleObservacionPK other = (DetalleObservacionPK) o;
		return true
			&& (getId_Observacion() == null ? other.getId_Observacion() == null : getId_Observacion().equals(other.getId_Observacion()))
			&& (getId_Caracteristica() == null ? other.getId_Caracteristica() == null : getId_Caracteristica().equals(other.getId_Caracteristica()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getId_Observacion() == null ? 0 : getId_Observacion().hashCode());
		result = prime * result + (getId_Caracteristica() == null ? 0 : getId_Caracteristica().hashCode());
		return result;
	}
   
   
}
