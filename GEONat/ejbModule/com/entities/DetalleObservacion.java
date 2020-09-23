package com.entities;

import java.io.Serializable;
import java.lang.Float;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DetalleObservacion
 *
 */
@Entity
//@NamedQuery(name="DetalleObservacion.obtenerTodos", query="SELECT d FROM DetalleObservacion d")
//@NamedQuery(name="DetalleObservacion.obtenerTodosFiltro", query="SELECT d FROM DetalleObservacion d WHERE d.nombre LIKE :filtro")
public class DetalleObservacion implements Serializable {

	   
	@EmbeddedId
	DetalleObservacionPK Id_DetalleObservacion;
	
	@ManyToOne//(fetch = FetchType.EAGER)
    @MapsId("Id_Observacion")
	@JoinColumn(name="Id_Observacion", nullable=false)
    private Observacion observacion;
 
    @ManyToOne//(fetch = FetchType.EAGER)
    @MapsId("Id_Caracteristica")
    @JoinColumn(name="Id_Caracteristica", nullable=false)
    private Caracteristica caracteristica;
	
    @Basic(optional=true)
	private Date fecha;
    
    @Basic(optional=true)
	private String valorTexto;
    
    @Basic(optional=true)
	private Float valorNumerico;
    
    
    
	private static final long serialVersionUID = 1L;

	public DetalleObservacion() {
		super();
	}

	public DetalleObservacionPK getId_DetalleObservacion() {
		return Id_DetalleObservacion;
	}

	public void setId_DetalleObservacion(DetalleObservacionPK id_DetalleObservacion) {
		Id_DetalleObservacion = id_DetalleObservacion;
	}

	public Observacion getObservacion() {
		return observacion;
	}

	public void setObservacion(Observacion observacion) {
		this.observacion = observacion;
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getValorTexto() {
		return valorTexto;
	}

	public void setValorTexto(String valorTexto) {
		this.valorTexto = valorTexto;
	}

	public Float getValorNumerico() {
		return valorNumerico;
	}

	public void setValorNumerico(Float valorNumerico) {
		this.valorNumerico = valorNumerico;
	}   
	

	
	

   
}
