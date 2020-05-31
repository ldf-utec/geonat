package com.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.sql.Blob;
import javax.persistence.*;


@Entity

public class Imagen implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id_Imagen;
	
	private Blob imagen;
	
	@JoinColumn(name="Id_Observacion", nullable=false)
	@ManyToOne()
	private Observacion Id_Observacion;
	
	private static final long serialVersionUID = 1L;

	public Imagen() {
		super();
	}   
	public Integer getId_Imagen() {
		return this.Id_Imagen;
	}

	public void setId_Imagen(Integer Id_Imagen) {
		this.Id_Imagen = Id_Imagen;
	}   
	public Blob getImagen() {
		return this.imagen;
	}

	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}   
	public Observacion getId_Observacion() {
		return this.Id_Observacion;
	}

	public void setId_Observacion(Observacion Id_Observacion) {
		this.Id_Observacion = Id_Observacion;
	}
   
}
