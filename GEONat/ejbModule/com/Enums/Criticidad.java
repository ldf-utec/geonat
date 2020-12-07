package com.Enums;


public enum Criticidad{
	ALTA("Alta",1),
	MEDIA("Media",2),
	BAJA("Baja",2);
	
	private int id_criticidad;
	private String nombre;
		
	
	private Criticidad( String nombre, int id_criticidad) {
		this.id_criticidad = id_criticidad;
		this.nombre = nombre;
	}


	public String getnombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getId_criticidad() {
		return id_criticidad;
	}


	public void setId_criticidad(int id_criticidad) {
		this.id_criticidad = id_criticidad;
	}


}

