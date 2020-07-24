package com.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Fenomeno
 *
 */

@Entity
@NamedQuery(name="Fenomeno.obtenerTodos", query="SELECT f FROM Fenomeno f")
@NamedQuery(name="Fenomeno.obtenerTodosFiltro", query="SELECT f FROM Fenomeno f WHERE f.nombre LIKE :filtro")
@NamedQuery(name="Fenomeno.existeNombreFenomeno", query="SELECT count (nombre) FROM Fenomeno WHERE nombre=:filtro")
@NamedQuery(name="Fenomeno.obtenerId", query="SELECT f FROM Fenomeno f WHERE f.Id_Fenomeno =:filtro")


public class Fenomeno implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id_Fenomeno;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fenomeno")
	private Set<Caracteristica> caracteristicas = new HashSet<>();
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fenomeno")
	private Set<Observacion> observaciones = new HashSet<>();
	
	
	@Basic(optional = false)
	@Column(length = 50, unique = true)
	private String nombre;
	
	@Basic(optional = true)
	@Column(length = 20) 
	private String telefono;
	
	@Basic(optional = true)
	@Column(length = 200)
	private String descripcion;
	
	
	
	
	private static final long serialVersionUID = 1L;

	public Fenomeno() {
		super();
	}   
	public Integer getId_Fenomeno() {
		return this.Id_Fenomeno;
	}

	public void setId_Fenomeno(Integer Id_Fenomeno) {
		this.Id_Fenomeno = Id_Fenomeno;
	}   
	
	public Set<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(Set<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}   
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
   
}
