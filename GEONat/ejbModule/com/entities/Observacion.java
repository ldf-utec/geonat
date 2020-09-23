package com.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@NamedQuery(name="Observacion.obtenerUno", query="SELECT o FROM Observacion o WHERE o.Id_Observacion =:id")
@NamedQuery(name="Observacion.obtenerTodos", query="SELECT o FROM Observacion o")
//@NamedQuery(name="Observacion.obtenerTodosFiltro", query="SELECT o FROM Observacion o WHERE o.nombre LIKE :filtro")
public class Observacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id_Observacion;
	
	
	
	// Relación ManyToMany BIDIRECCIONAL con Caracteristicas mediante DetalleObservacion
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "observacion")
	private Set<DetalleObservacion> detalleObservaciones = new HashSet<>();
	
		
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name="Id_Usuario_Reg", nullable=true)
	private Usuario usuarioRegistro;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="Id_Usuario_Rev", nullable=true)
	private Usuario usuarioRevision;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="Id_Fenomeno", nullable=true) //TODO Cambiar a nullable false
	private Fenomeno fenomeno;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="Id_Localidad", nullable=true)
	private Localidad localidad;
	
	
	@Basic(optional=true)
	private Double geoLatitud;
	
	@Basic(optional=true)
	private Double geoLongitud;

	@Basic(optional=true)
	private Double geoAltitud;

	@Basic(optional = true)
	@Column(length = 200)
	private String descripcion;
	
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@Basic(optional = true)
	private Date fechaRevision;
	
	@Basic(optional = true)
	private Integer fiabilidadRevision;
	
	@Basic(optional = true)
	private String comentarioRevision;
	
	@Basic(optional = true)
	@Column(length = 20)
	private String criticidad;
	
	
	
	
	private static final long serialVersionUID = 1L;

	public Observacion() {
		super();
	}
	
	
	

	public Observacion(Integer id_Observacion, Set<DetalleObservacion> detalleObservaciones, Usuario usuarioRegistro,
			Usuario usuarioRevision, Fenomeno fenomeno, Localidad localidad, Double geoLatitud, Double geoLongitud,
			Double geoAltitud, String descripcion, Date fecha, Date fechaRevision, Integer fiabilidadRevision,
			String comentarioRevision, String criticidad) {
		super();
		Id_Observacion = id_Observacion;
		this.detalleObservaciones = detalleObservaciones;
		this.usuarioRegistro = usuarioRegistro;
		this.usuarioRevision = usuarioRevision;
		this.fenomeno = fenomeno;
		this.localidad = localidad;
		this.geoLatitud = geoLatitud;
		this.geoLongitud = geoLongitud;
		this.geoAltitud = geoAltitud;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.fechaRevision = fechaRevision;
		this.fiabilidadRevision = fiabilidadRevision;
		this.comentarioRevision = comentarioRevision;
		this.criticidad = criticidad;
	}




	public Integer getId_Observacion() {
		return Id_Observacion;
	}

	public void setId_Observacion(Integer id_Observacion) {
		Id_Observacion = id_Observacion;
	}

	public Set<DetalleObservacion> getDetalleObservaciones() {
		return detalleObservaciones;
	}

	public void setDetalleObservaciones(Set<DetalleObservacion> detalleObservaciones) {
		this.detalleObservaciones = detalleObservaciones;
	}

	public Usuario getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(Usuario usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public Fenomeno getFenomeno() {
		return fenomeno;
	}

	public void setFenomeno(Fenomeno fenomeno) {
		this.fenomeno = fenomeno;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Double getGeoLatitud() {
		return geoLatitud;
	}

	public void setGeoLatitud(Double geoLatitud) {
		this.geoLatitud = geoLatitud;
	}

	public Double getGeoLongitud() {
		return geoLongitud;
	}

	public void setGeoLongitud(Double geoLongitud) {
		this.geoLongitud = geoLongitud;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaRevision() {
		return fechaRevision;
	}

	public void setFechaRevision(Date fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	public Integer getFiabilidadRevision() {
		return fiabilidadRevision;
	}

	public void setFiabilidadRevision(Integer fiabilidadRevision) {
		this.fiabilidadRevision = fiabilidadRevision;
	}

	public String getComentarioRevision() {
		return comentarioRevision;
	}

	public void setComentarioRevision(String comentarioRevision) {
		this.comentarioRevision = comentarioRevision;
	}

	public Usuario getUsuarioRevision() {
		return usuarioRevision;
	}

	public void setUsuarioRevision(Usuario usuarioRevision) {
		this.usuarioRevision = usuarioRevision;
	}

	public Double getGeoAltitud() {
		return geoAltitud;
	}

	public void setGeoAltitud(Double geoAltitud) {
		this.geoAltitud = geoAltitud;
	}

	public String getCriticidad() {
		return criticidad;
	}

	public void setCriticidad(String criticidad) {
		this.criticidad = criticidad;
	}   
	
   
}

// https://thorben-janssen.com/hibernate-tip-many-to-many-association-with-additional-attributes/
