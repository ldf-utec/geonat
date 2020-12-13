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

import com.DAO.concrete.UsuarioDAO;
import com.DAO.interfaces.IUsuarioDAO;
import com.entities.Departamento;
import com.entities.TipoDocumento;
import com.entities.TipoUsuario;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosGUI;
import com.presentacion.servicios.ServiciosUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.SwingConstants;

public class AltaUsuario extends JFrame {

	// Obtengo la instancia del servicio de capa lógica de negocios
	ServiciosUsuario serviciosUsuarios = ServiciosUsuario.getInstance();
	
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
		
		// Obtengo la instancia del DAO Usuario
		// IUsuarioDAO usuarioDAO = ServiciosGUI.getInstance().getUsuarioBean();
				
		frmGeonatAlta = new JFrame();
		frmGeonatAlta.getContentPane().setBounds(new Rectangle(0, 0, 300, 0));
		frmGeonatAlta.setResizable(false);
		frmGeonatAlta.setTitle("GEONat - Registro de usuario");
		frmGeonatAlta.setBounds(100, 100, 1200, 800);
		frmGeonatAlta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblNombreDeUsuaio = new JLabel("Nombre de usuario:");
		lblNombreDeUsuaio.setBounds(new Rectangle(0, 0, 300, 0));
		lblNombreDeUsuaio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombreDeUsuaio.setBounds(64, 145, 212, 28);
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(new Rectangle(0, 0, 300, 0));
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(64, 193, 200, 28);
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(new Rectangle(0, 0, 300, 0));
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblApellido.setBounds(644, 193, 107, 28);
		JLabel TipoDoc = new JLabel("Tipo de Documento:");
		TipoDoc.setBounds(new Rectangle(0, 0, 300, 0));
		TipoDoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		TipoDoc.setBounds(64, 290, 212, 25);
		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario:");
		lblTipoDeUsuario.setBounds(new Rectangle(0, 0, 300, 0));
		lblTipoDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTipoDeUsuario.setBounds(64, 587, 238, 26);
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(new Rectangle(0, 0, 300, 0));
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDocumento.setBounds(644, 290, 113, 24);
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(new Rectangle(0, 0, 300, 0));
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDireccion.setBounds(64, 357, 212, 26);
		JCheckBox checkboxUsuarioActivo = new JCheckBox("Activaci\u00F3n de usuario");
		checkboxUsuarioActivo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		checkboxUsuarioActivo.setSelected(true);
		checkboxUsuarioActivo.setBounds(276, 643, 276, 18);
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(new Rectangle(0, 0, 300, 0));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(64, 487, 212, 28);
		JLabel lblReIngresarPassword = new JLabel("Reingresar Password:");
		lblReIngresarPassword.setBounds(new Rectangle(0, 0, 300, 0));
		lblReIngresarPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblReIngresarPassword.setBounds(64, 535, 212, 28);
		JLabel lblCorreo = new JLabel("Correo electr\u00F3nico:");
		lblCorreo.setBounds(new Rectangle(0, 0, 300, 0));
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCorreo.setBounds(64, 431, 212, 28);
		
		nombreUsuario = new JTextField();
		nombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nombreUsuario.setBounds(276, 145, 250, 28);
		nombreUsuario.setColumns(10);
		nombre = new JTextField();
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nombre.setBounds(276, 193, 250, 28);
		nombre.setColumns(10);
		apellido = new JTextField();
		apellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		apellido.setBounds(756, 193, 250, 28);
		apellido.setColumns(10);
		documento = new JTextField();
		documento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		documento.setBounds(756, 288, 250, 28);
		documento.setColumns(10);
		direccion = new JTextField();
		direccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		direccion.setBounds(276, 355, 450, 28);
		direccion.setColumns(10);
		password1 = new JPasswordField();
		password1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		password1.setBounds(276, 487, 195, 28);
		password2 = new JPasswordField();
		password2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		password2.setBounds(276, 535, 195, 28);
		correo = new JTextField();
		correo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		correo.setBounds(276, 431, 450, 28);
		correo.setColumns(10);
		
		
		
		
		// Combobox Tipo de DOCUMENTO
		JComboBox comboTipoDocumento = new JComboBox();
		comboTipoDocumento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboTipoDocumento.setBounds(276, 289, 250, 26);
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
			
		
		// ComboBox Tipo de USUARIO
		JComboBox comboTipoUsuario = new JComboBox();
		comboTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboTipoUsuario.setBounds(276, 587, 195, 26);
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
				
		
		//Boton AltaUsuario
		JButton btnAltaUsuario = new JButton("Confirmar");
		btnAltaUsuario.setBounds(821, 708, 155, 34);
		btnAltaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAltaUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evento) {
					String strerror = "";
					Boolean errores = false;
					Usuario u = new Usuario();
					
					u.setNombreUsuario(nombreUsuario.getText());
					
					try {
						boolean existeUsuario = serviciosUsuarios.existeNombreUsuario(u);

						if (existeUsuario) {
							errores = true;
							strerror = " El usuario ya existe. ";
						}
					} catch (ServiciosException e) {
						e.printStackTrace();
					}
					
					
					if ((nombre.getText().length())>50) {
						errores=true;
						strerror= strerror + " Nombre con mas de 50 caracteres. ";
					} else {
						u.setNombre(nombre.getText());
					}
					if ((apellido.getText().length())>50) {
						errores=true;
						strerror= strerror + " Apellido con mas de 50 caracteres. ";
					} else {
						u.setApellido(apellido.getText());
					}
					u.setTipoDocumento(TipoDocumento.valueOf(comboTipoDocumento.getSelectedItem().toString()) );
					
					if ((documento.getText().length())>20) {
						errores=true;
						strerror= strerror + " Documento con mas de 20 caracteres. ";
					} else {
						u.setNroDocumento(documento.getText());
					}
					if ((direccion.getText().length())>100) {
						errores=true;
						strerror= strerror + " Direccion con mas de 100 caracteres. ";
					} else {
						u.setDireccion(direccion.getText());
					}


					if ((correo.getText().length())>50) {
						errores=true;
						strerror= strerror + " Direccion de email con mas de 50 caracteres. ";
					} else {
						u.setEmail(correo.getText());
					}
					if (clavesIdenticas(password1.getPassword(), password2.getPassword())) {
						u.setPassword(new String(password1.getPassword()));
					} else {
						errores=true;
						strerror= strerror + " Las contraseñas ingresadas no coinciden. ";
					}
									
					u.setTipoUsuario(TipoUsuario.valueOf(comboTipoUsuario.getSelectedItem().toString()) );
										
					u.setEstadoActivo(checkboxUsuarioActivo.isSelected());
										
					if (errores) {
						JOptionPane.showMessageDialog(null,  strerror);
					} else {
						try {
							serviciosUsuarios.create(u);
							JOptionPane.showMessageDialog(null,  "Usuario Creado");
						} catch (ServiciosException err) {
							
							err.printStackTrace();
						}
						
					}
	
	
				}
		});
		
 
		//Boton Cancelar
		JButton btnCancela = new JButton("Cancelar");
		btnCancela.setBounds(1003, 708, 148, 34);
		btnCancela.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				frmGeonatAlta.hide();
			}
		});
		frmGeonatAlta.getContentPane().setLayout(null);
		frmGeonatAlta.getContentPane().add(lblPassword);
		frmGeonatAlta.getContentPane().add(lblReIngresarPassword);
		frmGeonatAlta.getContentPane().add(lblTipoDeUsuario);
		frmGeonatAlta.getContentPane().add(lblCorreo);
		frmGeonatAlta.getContentPane().add(comboTipoUsuario);
		frmGeonatAlta.getContentPane().add(password2);
		frmGeonatAlta.getContentPane().add(password1);
		frmGeonatAlta.getContentPane().add(correo);
		frmGeonatAlta.getContentPane().add(lblDocumento);
		frmGeonatAlta.getContentPane().add(lblNombreDeUsuaio);
		frmGeonatAlta.getContentPane().add(lblNombre);
		frmGeonatAlta.getContentPane().add(lblApellido);
		frmGeonatAlta.getContentPane().add(TipoDoc);
		frmGeonatAlta.getContentPane().add(lblDireccion);
		frmGeonatAlta.getContentPane().add(direccion);
		frmGeonatAlta.getContentPane().add(documento);
		frmGeonatAlta.getContentPane().add(comboTipoDocumento);
		frmGeonatAlta.getContentPane().add(apellido);
		frmGeonatAlta.getContentPane().add(nombre);
		frmGeonatAlta.getContentPane().add(nombreUsuario);
		frmGeonatAlta.getContentPane().add(checkboxUsuarioActivo);
		frmGeonatAlta.getContentPane().add(btnAltaUsuario);
		frmGeonatAlta.getContentPane().add(btnCancela);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1194, 59);
		frmGeonatAlta.getContentPane().add(panel);
		
		JLabel lblRegistroDeUsuario = new JLabel("Registro de usuario");
		lblRegistroDeUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistroDeUsuario.setForeground(Color.GRAY);
		lblRegistroDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRegistroDeUsuario.setBounds(new Rectangle(10, 10, 10, 10));
		lblRegistroDeUsuario.setBounds(14, 28, 1174, 25);
		panel.add(lblRegistroDeUsuario);
		
		JLabel label_1 = new JLabel("");
		label_1.setIconTextGap(0);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(1099, 0, 69, 53);
		panel.add(label_1);

		
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
