package com.presentacion.gui.usuarios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.DAO.interfaces.IUsuarioDAO;
import com.entities.Departamento;
import com.entities.TipoDocumento;
import com.entities.TipoUsuario;
import com.entities.Usuario;
import com.exception.ServiciosException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JPasswordField;

public class AltaUsuario extends JFrame {

	public JFrame frmGeonatAlta;
	private JTextField nombreUsuario;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField documento;
	private JTextField direccion;
	private JPasswordField password1;
	private JPasswordField password2;
	private JTextField correo;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaUsuario window = new AltaUsuario();
					window.frmGeonatAlta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AltaUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGeonatAlta = new JFrame();
		frmGeonatAlta.setTitle("GEONat - Alta de usuarios");
		frmGeonatAlta.setBounds(100, 100, 800, 600);
		frmGeonatAlta.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JLabel lblNombreDeUsuaio = new JLabel("Nombre de usuaio");
		
		nombreUsuario = new JTextField();
		nombreUsuario.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblApellido = new JLabel("Apellido");
		
		nombre = new JTextField();
		nombre.setColumns(10);
		
		apellido = new JTextField();
		apellido.setColumns(10);
		
		JLabel TipoDoc = new JLabel("Tipo de Documento");
		JComboBox comboTipoDocumento = new JComboBox();
		comboTipoDocumento.removeAllItems(); 
		comboTipoDocumento.addItem("");
		TipoDocumento[] tipoDocList = TipoDocumento.values();
		for (TipoDocumento tipoDocumento : tipoDocList) {
		comboTipoDocumento.addItem(tipoDocumento);
		}
		comboTipoDocumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventocombo) {
				comboTipoDocumento.getSelectedIndex();
				
			}
		});
			
		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario");
		JComboBox comboTipoUsuario = new JComboBox();
		comboTipoUsuario.removeAllItems(); 
		comboTipoUsuario.addItem("");
		TipoUsuario[] tipousrList = TipoUsuario.values();
		for (TipoUsuario tipoUsuario : tipousrList) {
		comboTipoUsuario.addItem(tipoUsuario);
		}
		comboTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventocombotu) {
				comboTipoUsuario.getSelectedIndex();
				
			}
		});
		
		
		
		
		JLabel lblDocumento = new JLabel("Documento");
		
		documento = new JTextField();
		documento.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion");
		
		direccion = new JTextField();
		direccion.setColumns(10);
		
		JCheckBox checkboxUsuarioActivo = new JCheckBox("Usuario Activo?");
		
		
		
		//Boton AltaUsuario
		JButton btnAltaUsuario = new JButton("Alta Usuario");
		btnAltaUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evento) {
					String strerror = "";
					Boolean errores = false;
					Usuario usr = new Usuario();
					
					usr.setNombreUsuario(nombreUsuario.getText());
					IUsuarioDAO usuarioBean=null;
					try {
						usuarioBean = (IUsuarioDAO) InitialContext.doLookup("/GEONat/UsuarioDAO!com.DAO.IUsuarioDAO");
						boolean enu = usuarioBean.existeNombreUsuario(usr);
						//JOptionPane.showMessageDialog(null,  "error en bean");
						if (enu) {
							errores = true;
							strerror = " El usuario ya existe. ";
						}
					} catch (NamingException e) {
						e.printStackTrace();
					} catch (ServiciosException e) {
						e.printStackTrace();
					}
					
					
					if ((nombre.getText().length())>50) {
						errores=true;
						strerror= strerror + " Nombre con mas de 50 caracteres. ";
					} else {
						usr.setNombre(nombre.getText());
					}
					if ((apellido.getText().length())>50) {
						errores=true;
						strerror= strerror + " Apellido con mas de 50 caracteres. ";
					} else {
						usr.setApellido(apellido.getText());
					}
					usr.setTipoDocumento(TipoDocumento.valueOf(comboTipoDocumento.getSelectedItem().toString()) );
					
					if ((documento.getText().length())>20) {
						errores=true;
						strerror= strerror + " Documento con mas de 20 caracteres. ";
					} else {
						usr.setNroDocumento(documento.getText());
					}
					if ((direccion.getText().length())>100) {
						errores=true;
						strerror= strerror + " Direccion con mas de 100 caracteres. ";
					} else {
						usr.setDireccion(direccion.getText());
					}


					if ((correo.getText().length())>50) {
						errores=true;
						strerror= strerror + " Direccion de email con mas de 50 caracteres. ";
					} else {
						usr.setEmail(correo.getText());
					}
					if (clavesIdenticas(password1.getPassword(), password2.getPassword())) {
						usr.setPassword(new String(password1.getPassword()));
					} else {
						errores=true;
						strerror= strerror + " Las contraseñas ingresadas no coinciden. ";
					}
									
					usr.setTipoUsuario(TipoUsuario.valueOf(comboTipoUsuario.getSelectedItem().toString()) );
										
					usr.setEstadoActivo(checkboxUsuarioActivo.isSelected());
										
					if (errores) {
						JOptionPane.showMessageDialog(null,  strerror);
					} else {
						try {
							usuarioBean.create(usr);
							JOptionPane.showMessageDialog(null,  "Usuario Creado");
						} catch (ServiciosException err) {
							
							err.printStackTrace();
						}
						
					}
	
	
				}
		});
		
 
		//Boton Cancelar
		JButton btnCancela = new JButton("Cancela");
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				frmGeonatAlta.hide();
			}
			
		});

		JLabel lblPassword = new JLabel("Password");
		
		JLabel lblReIngresarPassword = new JLabel("Re ingresar Password");
		
		password1 = new JPasswordField();
		
		password2 = new JPasswordField();
		
		JLabel lblCorreo = new JLabel("Correo");
		
		correo = new JTextField();
		correo.setColumns(10);
		
		
		GroupLayout groupLayout = new GroupLayout(frmGeonatAlta.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblReIngresarPassword, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTipoDeUsuario)
								.addComponent(lblCorreo))
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(comboTipoUsuario, Alignment.LEADING, 0, 337, Short.MAX_VALUE)
										.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(password2, Alignment.LEADING)
											.addComponent(password1, Alignment.LEADING, 195, 195, Short.MAX_VALUE)))
									.addGap(255))
								.addComponent(correo, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblDocumento, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNombreDeUsuaio, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
									.addComponent(lblNombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
									.addComponent(lblApellido, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
									.addComponent(TipoDoc, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lblDireccion))
							.addGap(47)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(direccion)
								.addComponent(documento)
								.addComponent(comboTipoDocumento, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(apellido)
								.addComponent(nombre)
								.addComponent(nombreUsuario, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(checkboxUsuarioActivo)
									.addGap(18)
									.addComponent(btnAltaUsuario)
									.addGap(18)
									.addComponent(btnCancela)))))
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreDeUsuaio, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(nombreUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellido, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(apellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboTipoDocumento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(TipoDoc))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDocumento)
						.addComponent(documento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDireccion)
						.addComponent(direccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCorreo)
						.addComponent(correo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(password1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblReIngresarPassword)
						.addComponent(password2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTipoDeUsuario)
						.addComponent(comboTipoUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(88)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAltaUsuario)
						.addComponent(btnCancela)
						.addComponent(checkboxUsuarioActivo))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		frmGeonatAlta.getContentPane().setLayout(groupLayout);

		
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
