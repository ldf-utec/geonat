package com.entities;


public enum TipoUsuario{
	ADMINISTRADOR("Adminsitrador",1),
	EXPERTO("Experto",2),
	ONG("ONG",3),
	ORGANISMOPRIVADO("Organismo Privado",4),
	USUARIO("Usuario Común",5);
	
	private int id_tipoUsuario;
	private String nombre;
		
	private TipoUsuario (String nombre, int id_tipoUsuario) {
		this.id_tipoUsuario=id_tipoUsuario;
		this.nombre=nombre;
	}

	public String getnombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId_tipoUsuario() {
		return id_tipoUsuario;
	}

	public void setId_tipoUsuario(int id_tipoUsuario) {
		this.id_tipoUsuario = id_tipoUsuario;
	}


}

