package com.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

@Entity

public class Localidad implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id_Localidad;
	
	@Basic(optional = false)
	@Column(length = 50)
	private String nombre;
	
//	@JoinColumn(name="Id_Geolocalizacion", nullable=false)
//	@OneToOne()
	
//	@JoinColumn(name="Id_departamento", nullable=false)
//	@ManyToOne()

	private int Id_departamento;
	
	private static final long serialVersionUID = 1L;

	public Localidad() {
		super();
	}   
	public Integer getId_Localidad() {
		return this.Id_Localidad;
	}

	public void setId_Localidad(Integer Id_Localidad) {
		this.Id_Localidad = Id_Localidad;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId_departamento() {
		return Id_departamento;
	}
	public void setId_departamento(int id_departamento) {
		Id_departamento = id_departamento;
	}
	
	
//	public Geolocalizacion getId_Geolocalizacion() {
//		return Id_Geolocalizacion;
//	}
//	public void setId_Geolocalizacion(Geolocalizacion id_Geolocalizacion) {
//		Id_Geolocalizacion = id_Geolocalizacion;
//	}   

   
}
