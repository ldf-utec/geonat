package com.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.*;

import javax.persistence.*;


@Entity
@NamedQuery(name="Caracteristica.obtenerTodos", query="SELECT c FROM Caracteristica c")
@NamedQuery(name="Caracteristica.obtenerTodosFiltro", query="SELECT c FROM Caracteristica c WHERE c.nombre LIKE :filtro")
@NamedQuery(name="Caracteristica.existeNombreUsuario", query="SELECT count (nombre) FROM Caracteristica WHERE nombre=:filtro")
@NamedQuery(name="Caracteristica.obtenerUnoxNombre", query="SELECT c FROM Caracteristica c WHERE c.nombre LIKE :filtro")
@NamedQuery(name="Caracteristica.existeNombreCaracteristica", query="SELECT count(nombre) FROM Caracteristica WHERE nombre=:filtro")
public class Caracteristica implements Serializable {
	
	@Id
	@SequenceGenerator(name = "caracteristicaSeq", sequenceName="CARACTERISTICA_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caracteristicaSeq")
	private Integer Id_Caracteristica;

	
	
	// Relación ManyToMany BIDIRECCIONAL mediante DetalleObservacion (El FetchType.EAGER se puso ya que daba error al obtener una caracteristica, ver nota al pié..)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "observacion") 
	private Set<DetalleObservacion> detalleObservaciones = new HashSet<>();
	
	
	// Relación con Fenomeno : Muchos Caracteristicas estan asociadas a Un Fenomeno
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Id_Fenomeno")
	private Fenomeno fenomeno;

	
	@Basic(optional = false)
	@Column(length = 50)
	private String nombre;
	
	@Basic(optional = true)
	
	@Column(length = 50)
	private String etiqPresentacion;
		
	@Basic(optional = true)
	//@Column(name = "TIPO_DATO", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoDato tipoDato;
	
			
	
	
	private static final long serialVersionUID = 1L;

	public Caracteristica() {
		super();
	}
	
		
	public Caracteristica(Integer id_Caracteristica, Set<DetalleObservacion> detalleObservaciones, Fenomeno fenomeno,
			String nombre, String etiqPresentacion, TipoDato tipoDato) {
		super();
		Id_Caracteristica = id_Caracteristica;
		this.detalleObservaciones = detalleObservaciones;
		this.fenomeno = fenomeno;
		this.nombre = nombre;
		this.etiqPresentacion = etiqPresentacion;
		this.tipoDato = tipoDato;
	}




	public Integer getId_Caracteristica() {
		return Id_Caracteristica;
	}

	public void setId_Caracteristica(Integer id_Caracteristica) {
		Id_Caracteristica = id_Caracteristica;
	}

	public Set<DetalleObservacion> getDetalleObservaciones() {
		return detalleObservaciones;
	}

	public void setDetalleObservaciones(Set<DetalleObservacion> detalleObservaciones) {
		this.detalleObservaciones = detalleObservaciones;
	}

	public Fenomeno getFenomeno() {
		return fenomeno;
	}

	public void setFenomeno(Fenomeno fenomeno) {
		this.fenomeno = fenomeno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEtiqPresentacion() {
		return etiqPresentacion;
	}

	public void setEtiqPresentacion(String etiqPresentacion) {
		this.etiqPresentacion = etiqPresentacion;
	}

	public TipoDato getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(TipoDato tipoDato) {
		this.tipoDato = tipoDato;
	}   
	
	@Override
    public String toString() {
          return "Característica [id=" + Id_Caracteristica + ", name=" + nombre + "]";
    }
	
   
}

// ERROR que daba al intentar obtener una Característica. Se tuvo que poner FetchType.EAGER en el set DetalleObservacin
// https://www.baeldung.com/hibernate-initialize-proxy-exception
