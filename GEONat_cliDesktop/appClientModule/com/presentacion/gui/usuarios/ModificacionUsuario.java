package com.presentacion.gui.usuarios;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.DAO.interfaces.IUsuarioDAO;
import com.entities.TipoDocumento;
import com.entities.TipoUsuario;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosUsuario;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.Dimension;


public class ModificacionUsuario {

	// Obtengo la instancia del servicio de capa lógica de negocios
	ServiciosUsuario serviciosUsuarios = ServiciosUsuario.getInstance();
	
	public JFrame frmModificarUsuario;
	private JTextField txtNombreUsr;
	private JButton btnBuscarUsuario;
	private JButton btnCancela;
	private JCheckBox checkBoxActivo;
	private JButton btnModifica;
	private JComboBox comboBoxTUsuario;
	private JLabel label_1;
	private JLabel label_2;
	private JPasswordField password2;
	private JPasswordField password1;
	private JLabel label_3;
	private JLabel label_4;
	private JTextField txtemail;
	private JTextField txtDireccion;
	private JLabel lblDireccin;
	private JLabel label_6;
	private JTextField txtDocumento;
	private JComboBox comboTDocumento;
	private JLabel label_7;
	private JLabel label_8;
	private JTextField txtApellido;
	private JTextField txtNombre;
	private JLabel label_9;

	private int id_user;
	private boolean enu;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificacionUsuario window = new ModificacionUsuario();
					window.frmModificarUsuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModificacionUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificarUsuario = new JFrame();
		frmModificarUsuario.setTitle("Modificar Usuario");
		frmModificarUsuario.setMinimumSize(new Dimension(800, 600));
		frmModificarUsuario.setSize(new Dimension(800, 600));
		frmModificarUsuario.setResizable(false);
		frmModificarUsuario.setBounds(100, 100, 800, 600);
		frmModificarUsuario.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmModificarUsuario.getContentPane().setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario");
		lblNombreDeUsuario.setBounds(20, 32, 120, 20);
		frmModificarUsuario.getContentPane().add(lblNombreDeUsuario);
		
		txtNombreUsr = new JTextField();
		txtNombreUsr.setColumns(10);
		txtNombreUsr.setBounds(150, 32, 430, 20);
		frmModificarUsuario.getContentPane().add(txtNombreUsr);
		
		btnBuscarUsuario = new JButton("Buscar Usuario");
		btnBuscarUsuario.setBounds(626, 31, 148, 23);
		frmModificarUsuario.getContentPane().add(btnBuscarUsuario);
		btnBuscarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Usuario usr = new Usuario();
				
				usr.setNombreUsuario(txtNombreUsr.getText());
				IUsuarioDAO usuarioBean=null;
				try {
					
					boolean enu = serviciosUsuarios.existeNombreUsuario(usr);
					if (!enu) {
						JOptionPane.showMessageDialog(null,  "No existe el usuario el la base de datos");
					} else {
						Usuario usuario = new Usuario();
						usuario = serviciosUsuarios.obtenerUno(usr);
						id_user = usuario.getId_Usuario();
						txtNombre.setText(usuario.getNombre());
						txtApellido.setText(usuario.getApellido());
						txtDocumento.setText(usuario.getNroDocumento());
						txtemail.setText(usuario.getEmail());
						txtDireccion.setText(usuario.getDireccion());
						checkBoxActivo.setSelected(usuario.getEstadoActivo());
						comboTDocumento.setSelectedIndex(usuario.getTipoDocumento().getId_TipoDocumento());
						comboBoxTUsuario.setSelectedIndex(usuario.getTipoUsuario().getId_tipoUsuario());
							
					}
				} catch (ServiciosException e) {
					e.printStackTrace();
				}
			}
			});
		
		///
		checkBoxActivo = new JCheckBox("Usuario Activo?");
		checkBoxActivo.setBounds(213, 458, 99, 23);
		frmModificarUsuario.getContentPane().add(checkBoxActivo);
		

		comboBoxTUsuario = new JComboBox();
		comboBoxTUsuario.setBounds(213, 395, 337, 20);
		frmModificarUsuario.getContentPane().add(comboBoxTUsuario);
		
		label_1 = new JLabel("Tipo de Usuario");
		label_1.setBounds(46, 395, 141, 14);
		frmModificarUsuario.getContentPane().add(label_1);
		
		label_2 = new JLabel("Re ingresar Password");
		label_2.setBounds(46, 355, 141, 14);
		frmModificarUsuario.getContentPane().add(label_2);
		
		password2 = new JPasswordField();
		password2.setBounds(213, 355, 195, 20);
		frmModificarUsuario.getContentPane().add(password2);
		
		password1 = new JPasswordField();
		password1.setBounds(213, 315, 195, 20);
		frmModificarUsuario.getContentPane().add(password1);
		
		label_3 = new JLabel("Password");
		label_3.setBounds(46, 318, 107, 14);
		frmModificarUsuario.getContentPane().add(label_3);
		
		label_4 = new JLabel("Correo");
		label_4.setBounds(46, 278, 94, 14);
		frmModificarUsuario.getContentPane().add(label_4);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(213, 275, 450, 20);
		frmModificarUsuario.getContentPane().add(txtemail);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(213, 235, 450, 20);
		frmModificarUsuario.getContentPane().add(txtDireccion);
		
		lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(46, 238, 120, 14);
		frmModificarUsuario.getContentPane().add(lblDireccin);
		
		label_6 = new JLabel("Documento");
		label_6.setBounds(46, 201, 120, 14);
		frmModificarUsuario.getContentPane().add(label_6);
		
		txtDocumento = new JTextField();
		txtDocumento.setColumns(10);
		txtDocumento.setBounds(213, 195, 450, 20);
		frmModificarUsuario.getContentPane().add(txtDocumento);
		
		comboTDocumento = new JComboBox();
		comboTDocumento.setBounds(213, 155, 450, 20);
		frmModificarUsuario.getContentPane().add(comboTDocumento);
		
		label_7 = new JLabel("Tipo de Documento");
		label_7.setBounds(46, 158, 120, 14);
		frmModificarUsuario.getContentPane().add(label_7);
		
		label_8 = new JLabel("Apellido");
		label_8.setBounds(46, 115, 120, 20);
		frmModificarUsuario.getContentPane().add(label_8);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(213, 115, 450, 20);
		frmModificarUsuario.getContentPane().add(txtApellido);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(213, 75, 450, 20);
		frmModificarUsuario.getContentPane().add(txtNombre);
		
		label_9 = new JLabel("Nombre");
		label_9.setBounds(46, 75, 120, 20);
		frmModificarUsuario.getContentPane().add(label_9);		
		
		
		////
		
		//Combo Tipo Documento
		comboTDocumento.removeAllItems(); 
		comboTDocumento.addItem("");
		TipoDocumento[] tipoDocList = TipoDocumento.values();
		for (TipoDocumento tipoDocumento : tipoDocList) {
		comboTDocumento.addItem(tipoDocumento);
		}
		comboTDocumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventocombo) {
				comboTDocumento.getSelectedIndex();
				
			}
		});
		
		//ComboTipoUsuario
		comboBoxTUsuario.removeAllItems(); 
		comboBoxTUsuario.addItem("");
		TipoUsuario[] tipousrList = TipoUsuario.values();
		for (TipoUsuario tipoUsuario : tipousrList) {
		comboBoxTUsuario.addItem(tipoUsuario);
		}
		comboBoxTUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventocombotu) {
				comboBoxTUsuario.getSelectedIndex();
				
			}
		});
		
		//Boton cancela
		btnCancela = new JButton("Cancela");
		btnCancela.setBounds(647, 503, 90, 23);
		frmModificarUsuario.getContentPane().add(btnCancela);
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				frmModificarUsuario.hide();
			}
			
		});
		
		
		btnModifica = new JButton("Guardar cambios");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				String strerror = "";
				Boolean errores = false;
				Usuario usr = new Usuario();
				
				
				usr.setNombreUsuario(txtNombreUsr.getText());
				IUsuarioDAO usuarioBean=null;
				try {
					usuarioBean = (IUsuarioDAO) InitialContext.doLookup("/GEONat/UsuarioDAO!com.DAO.IUsuarioDAO");
					enu = usuarioBean.existeNombreUsuario(usr);
					usr.setId_Usuario(id_user);
					
				} catch (NamingException e) {
					e.printStackTrace();
				} catch (ServiciosException e) {
					e.printStackTrace();
				}
				if (!enu) {
					errores = true;
					strerror = " Nombre de usuario no existe en la base de datos. ";
				} else {
					if ((txtNombre.getText().length())>50) {
						errores=true;
						strerror= strerror + " Nombre con mas de 50 caracteres. ";
					} else {
						usr.setNombre(txtNombre.getText());
					}
					if ((txtApellido.getText().length())>50) {
						errores=true;
						strerror= strerror + " Apellido con mas de 50 caracteres. ";
					} else {
						usr.setApellido(txtApellido.getText());
					}
					usr.setTipoDocumento(TipoDocumento.valueOf(comboTDocumento.getSelectedItem().toString()) );
					if ((txtDocumento.getText().length())>20) {
						errores=true;
						strerror= strerror + " Documento con mas de 20 caracteres. ";
					} else {
					usr.setNroDocumento(txtDocumento.getText());
					}
					if ((txtDireccion.getText().length())>100) {
						errores=true;
						strerror= strerror + " Direccion con mas de 100 caracteres. ";
					} else {
						usr.setDireccion(txtDireccion.getText());
					}
					if ((txtemail.getText().length())>50) {
						errores=true;
						strerror= strerror + " Direccion de email con mas de 50 caracteres. ";
					} else {
						usr.setEmail(txtemail.getText());
					}
					if (clavesIdenticas(password1.getPassword(), password2.getPassword())) {
						usr.setPassword(new String(password1.getPassword()));
					} else {
						errores=true;
						strerror= strerror + " Las password ingresadas no coinciden. ";
					}
					usr.setTipoUsuario(TipoUsuario.valueOf(comboBoxTUsuario.getSelectedItem().toString()) );
					usr.setEstadoActivo(checkBoxActivo.isSelected());
				}					
				if (errores) {
					JOptionPane.showMessageDialog(null,  strerror);
				} else {
					try {
						usuarioBean.update(usr);
						JOptionPane.showMessageDialog(null,  "Usuario Actualizado");
					} catch (ServiciosException err) {
						
						err.printStackTrace();
					}
					
				}
			}	
		});
		btnModifica.setBounds(456, 503, 156, 23);
		frmModificarUsuario.getContentPane().add(btnModifica);
		
		
	}
	private boolean clavesIdenticas(char[] j1,char[] j2) {
		boolean valor = true;
		int puntero = 0;
		if (j1.length != j2.length){
			valor = false;
		} else {
			while((valor)&&(puntero < j1.length)){
				if (j1[puntero] != j2[puntero]){
					valor = false;
				}
				puntero++;
			}
		}
		return valor;
		}
}
