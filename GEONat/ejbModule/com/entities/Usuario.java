package com.entities;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

@Entity
@NamedQuery(name="Usuario.obtenerTodos", query="SELECT u FROM Usuario u")
@NamedQuery(name="Usuario.obtenerTodosFiltro", query="SELECT u FROM Usuario u WHERE u.nombre LIKE :filtro")
@NamedQuery(name="Usuario.existeNombreUsuario", query="SELECT count (NombreUsuario) FROM Usuario WHERE NombreUsuario=:filtro")
@NamedQuery(name="Usuario.obtenerUno", query="SELECT u FROM Usuario u WHERE u.nombreUsuario LIKE :filtro")
@NamedQuery(name="Usuario.obtenerPorNombre", query="SELECT u FROM Usuario u WHERE UPPER(u.nombreUsuario) LIKE :filtro")
@NamedQuery(name="Usuario.obtenerPorDocumento", query="SELECT u FROM Usuario u WHERE u.nroDocumento LIKE :filtro")
@NamedQuery(name="Usuario.obtenerPorEstado", query="SELECT u FROM Usuario u WHERE u.estadoActivo=:estado")
public class Usuario implements Serializable {

	@Id
	@SequenceGenerator(name = "usuarioSeq", sequenceName="USUARIO_SEQ", allocationSize=1, initialValue=1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioSeq")
	private Integer Id_Usuario;
	
	@Basic(optional = false)
	@Column(length = 25, unique=true)
	private String nombreUsuario;
	
	@Basic(optional = false)
	@Column(length = 25)
	private String nombre;
	
	@Basic(optional = false)
	@Column(length = 25)
	private String apellido;
	
	@Basic(optional = false)
	@Enumerated(value = EnumType.STRING)
	private TipoDocumento tipoDocumento;
	
	@Basic(optional = false)
	@Column(length = 25, unique=true)
	private String nroDocumento;
	
	@Basic(optional = false)
	@Column(length = 50)
	private String email;
	
	@Basic(optional = true)
	/**/ @Column(length=50)
	private String direccion;
		
	@Basic(optional = false)
	private Boolean estadoActivo;
	
	
	@Basic(optional = false)
	@Enumerated(value = EnumType.STRING)
	private TipoUsuario tipoUsuario;
		
	
	@Basic(optional = false)
	@Column(length = 25)
	private String password;
	
	
	
	
	
	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
	}

	public Integer getId_Usuario() {
		return Id_Usuario;
	}

	public void setId_Usuario(Integer id_Usuario) {
		Id_Usuario = id_Usuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Boolean getEstadoActivo() {
		return estadoActivo;
	}

	public void setEstadoActivo(Boolean estadoActivo) {
		this.estadoActivo = estadoActivo;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	
	
	
	
	


}
