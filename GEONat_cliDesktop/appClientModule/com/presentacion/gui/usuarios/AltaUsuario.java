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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AltaUsuario extends JFrame {

	// Obtengo la instancia del servicio de capa l�gica de negocios
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
	JComboBox comboTipoDocumento;
	JComboBox comboTipoUsuario;

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
		frmGeonatAlta.setBounds(10, 10, 1200, 800);
		frmGeonatAlta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblNombreDeUsuaio = new JLabel("Nombre de usuario:");
		lblNombreDeUsuaio.setBounds(64, 235, 212, 28);
		lblNombreDeUsuaio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(64, 299, 200, 28);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(580, 293, 107, 40);
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel TipoDoc = new JLabel("Tipo de Documento:");
		TipoDoc.setBounds(64, 366, 212, 25);
		TipoDoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario:");
		lblTipoDeUsuario.setBounds(64, 174, 212, 26);
		lblTipoDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(580, 362, 113, 32);
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(64, 427, 212, 26);
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JCheckBox checkboxUsuarioActivo = new JCheckBox("Activaci\u00F3n de usuario");
		checkboxUsuarioActivo.setBounds(276, 630, 284, 28);
		checkboxUsuarioActivo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		checkboxUsuarioActivo.setSelected(true);
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(64, 550, 212, 28);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblReIngresarPassword = new JLabel("Reingresar Password:");
		lblReIngresarPassword.setBounds(550, 542, 212, 40);
		lblReIngresarPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblCorreo = new JLabel("Correo electr\u00F3nico:");
		lblCorreo.setBounds(64, 484, 212, 28);
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		nombreUsuario = new JTextField();
		nombreUsuario.setBounds(276, 229, 250, 40);
		nombreUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(nombreUsuario.getText().length()>=25&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }			
			}
		});		
		nombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nombreUsuario.setColumns(10);
		
		nombre = new JTextField();
		nombre.setBounds(276, 293, 250, 40);
		nombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(nombre.getText().length()>=25&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }	
			}
		});
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nombre.setColumns(10);
		
		apellido = new JTextField();
		apellido.setBounds(715, 292, 250, 40);
		apellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(apellido.getText().length()>=25&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }	
			}
		});
		apellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		apellido.setColumns(10);
		
		documento = new JTextField();
		documento.setBounds(715, 357, 250, 40);
		documento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(documento.getText().length()>=25&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }
			}
		});
		documento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		documento.setColumns(10);
		
		direccion = new JTextField();
		direccion.setBounds(276, 420, 450, 40);
		direccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(direccion.getText().length()>=50&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }
			}
		});
		direccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		direccion.setColumns(10);
		
		password1 = new JPasswordField();
		password1.setBounds(276, 542, 206, 40);
		password1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(password1.getText().length()>=25&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }
			}
		});
		password1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		password2 = new JPasswordField();
		password2.setBounds(762, 542, 206, 40);
		password2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(password2.getText().length()>=25&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }
			}
		});
		password2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		correo = new JTextField();
		correo.setBounds(276, 478, 450, 40);
		correo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(correo.getText().length()>=50&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }
			}
		});
		correo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		correo.setColumns(10);
		
		
		
		
		// Combobox Tipo de DOCUMENTO
		comboTipoDocumento = new JComboBox();
		comboTipoDocumento.setBounds(276, 359, 250, 40);
		comboTipoDocumento.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
		comboTipoUsuario = new JComboBox();
		comboTipoUsuario.setBounds(276, 167, 250, 40);
		comboTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
					
					if (hayCamposVacios()) {
						JOptionPane.showMessageDialog(frmGeonatAlta,  "Falta completar campos obligatorios(*)");
						return;
					}									
					
					u.setNombreUsuario(nombreUsuario.getText());
					
					try {

						if (serviciosUsuarios.existeNombreUsuario(u)) {
							errores = true;
							strerror = strerror +"- El nombre de usuario ingresado ya est� en uso.\n";
						}
					} catch (ServiciosException e) {
						e.printStackTrace();
					}
					
					try {
						boolean existeDocumento = serviciosUsuarios.obtenerPorDocumento(documento.getText())!=null;

						if (existeDocumento) {
							errores = true;
							strerror = strerror + "- El documento ingresado ya existe en la base de datos.\n";
						}
					} catch (ServiciosException e) {
						e.printStackTrace();
					}
									

					u.setNombre(nombre.getText());
					u.setApellido(apellido.getText());
					u.setTipoDocumento(TipoDocumento.valueOf(comboTipoDocumento.getSelectedItem().toString()) );
					u.setNroDocumento(documento.getText());
					u.setDireccion(direccion.getText());
					
					if (isEmailValid(correo.getText())) {
						u.setEmail(correo.getText());
					}else {
						errores=true;
						strerror= strerror + "- El correo ingresado no es v�lido.\n";
					}
					if (TipoDocumento.valueOf(comboTipoDocumento.getSelectedItem().toString()) == TipoDocumento.CI) {
						if (isCiValid(documento.getText())) {
							u.setEmail(correo.getText());
						}else {
							errores=true;
							strerror= strerror + "- C�dula de identidad inv�lida.\n";
						}
					}
					

					if (clavesIdenticas(password1.getPassword(), password2.getPassword())) {
						u.setPassword(new String(password1.getPassword()));
					} else {
						errores=true;
						strerror= strerror + "- Las contrase�as ingresadas no coinciden.\n";
					}
									
					u.setTipoUsuario(TipoUsuario.valueOf(comboTipoUsuario.getSelectedItem().toString()) );			
					u.setEstadoActivo(checkboxUsuarioActivo.isSelected());
										
					if (errores) {
						JOptionPane.showMessageDialog(null,  strerror);
					} else {
						try {
							serviciosUsuarios.create(u);
							JOptionPane.showMessageDialog(null,  "Usuario Creado");
							frmGeonatAlta.dispose();
						} catch (ServiciosException err) {
							
							err.printStackTrace();
							JOptionPane.showMessageDialog(null,  "Error al crear usuario en la base de datos");
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
				frmGeonatAlta.dispose();;
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
		panel.setBounds(0, 0, 1194, 59);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
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
		
		JLabel label = new JLabel("*");
		label.setBounds(527, 167, 33, 28);
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmGeonatAlta.getContentPane().add(label);
		
		JLabel label_2 = new JLabel("*");
		label_2.setBounds(527, 243, 33, 28);
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmGeonatAlta.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setBounds(527, 307, 33, 28);
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmGeonatAlta.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("*");
		label_4.setBounds(966, 306, 33, 28);
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmGeonatAlta.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("*");
		label_5.setBounds(527, 372, 33, 28);
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmGeonatAlta.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("*");
		label_6.setBounds(966, 380, 33, 17);
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmGeonatAlta.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("*");
		label_7.setBounds(728, 492, 33, 28);
		label_7.setForeground(Color.RED);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmGeonatAlta.getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("*");
		label_8.setBounds(483, 556, 33, 28);
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmGeonatAlta.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("*");
		label_9.setBounds(971, 552, 33, 40);
		label_9.setForeground(Color.RED);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmGeonatAlta.getContentPane().add(label_9);
		
		JLabel label_10 = new JLabel("*");
		label_10.setBounds(64, 94, 33, 28);
		label_10.setForeground(Color.RED);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmGeonatAlta.getContentPane().add(label_10);
		
		JLabel lblCamposObligatorios = new JLabel("Campos obligatorios");
		lblCamposObligatorios.setBounds(80, 94, 200, 28);
		lblCamposObligatorios.setForeground(Color.DARK_GRAY);
		lblCamposObligatorios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmGeonatAlta.getContentPane().add(lblCamposObligatorios);
		
		JLabel lblSinPuntosNi = new JLabel("Sin puntos ni guiones");
		lblSinPuntosNi.setBounds(966, 365, 200, 17);
		lblSinPuntosNi.setForeground(Color.DARK_GRAY);
		lblSinPuntosNi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmGeonatAlta.getContentPane().add(lblSinPuntosNi);

		
	}
	
	// Devuelve true si todos los campos obligatorios NO est�n vac�os
	private boolean hayCamposVacios() {
		List<JTextField> array = new ArrayList<JTextField>();
		array.add(nombreUsuario);
		array.add(nombre);
		array.add(apellido);
		array.add(documento);
		array.add(correo);
		array.add(password1);
		array.add(password2);
		

		// Valido los campos de texto que no sean null
		for (JTextField jTextField : array) {
			String s = jTextField.getText();
			if (s.equals("")) {
				return true;
			}
		}
		
		// valido los combo
		if (comboTipoDocumento.getSelectedItem().toString().equals("") || comboTipoUsuario.getSelectedItem().toString().equals("")) {
			return true;
		}
		
		return false;
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

	private boolean isEmailValid(String email) {
	// Patr�n para validar el email
    Pattern pattern = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    Matcher mather = pattern.matcher(email);

    if (mather.find() == true) {
        return true;
    } else {
        return false;
    }
}

	private boolean isCiValid(String ci) {
		if(ci.length() != 7 && ci.length() != 8){
			return false;
		}else{
			try{
				Integer.parseInt(ci);
			}catch (NumberFormatException e){
				throw e;
			}
		}

		int digVerificador = Integer.parseInt((ci.charAt(ci.length() - 1)) + "" ) ;
		int[] factores;
		if(ci.length() == 7){ // CI viejas
			factores = new int[]{9, 8, 7, 6, 3, 4};
		}else{
			factores = new int[]{2, 9, 8, 7, 6, 3, 4};
		}

		// Multiplico cada d�gito de la c�dula por cada factor dado
		int suma = 0;
		for (int i = 0; i < factores.length; i++) {
			int digito = Integer.parseInt(ci.charAt(i) + "");
			suma = suma + (digito * factores[i]);
		}
		
		
		// A la suma anterior, tengo que obtener el modulo de 10
		int resto = suma % 10;
		int checkdigit = 10 - resto;

		if(checkdigit == 10){
			return (digVerificador == 0);
		}else {
			return (checkdigit == digVerificador) ;
		}

	}
}
