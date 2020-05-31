package com.entities;


public enum TipoDato{
	TEXTO("Valor Texto",1),
	NUMERICO("Valor Numerico",2);
	
	private int id_tipoDato;
	private String nombre;
		
	private TipoDato (String nombre, int id_tipoDato) {
		this.id_tipoDato=id_tipoDato;
		this.nombre=nombre;
	}

	public String getnombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId_tipoDato() {
		return id_tipoDato;
	}

	public void setId_tipoDato(int id_tipoDato) {
		this.id_tipoDato = id_tipoDato;
	}


}

