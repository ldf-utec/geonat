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
		frmGeonatAlta.setBounds(100, 100, 1200, 800);
		frmGeonatAlta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblNombreDeUsuaio = new JLabel("Nombre de usuario:");
		lblNombreDeUsuaio.setBounds(new Rectangle(0, 0, 300, 0));
		lblNombreDeUsuaio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombreDeUsuaio.setBounds(64, 175, 212, 28);
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(new Rectangle(0, 0, 300, 0));
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(64, 247, 200, 28);
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(new Rectangle(0, 0, 300, 0));
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblApellido.setBounds(580, 241, 107, 40);
		JLabel TipoDoc = new JLabel("Tipo de Documento:");
		TipoDoc.setBounds(new Rectangle(0, 0, 300, 0));
		TipoDoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		TipoDoc.setBounds(64, 344, 212, 25);
		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario:");
		lblTipoDeUsuario.setBounds(new Rectangle(0, 0, 300, 0));
		lblTipoDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTipoDeUsuario.setBounds(64, 102, 212, 26);
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(new Rectangle(0, 0, 300, 0));
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDocumento.setBounds(580, 334, 113, 32);
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(new Rectangle(0, 0, 300, 0));
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDireccion.setBounds(64, 411, 212, 26);
		JCheckBox checkboxUsuarioActivo = new JCheckBox("Activaci\u00F3n de usuario");
		checkboxUsuarioActivo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		checkboxUsuarioActivo.setSelected(true);
		checkboxUsuarioActivo.setBounds(276, 664, 284, 28);
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(new Rectangle(0, 0, 300, 0));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(64, 539, 212, 28);
		JLabel lblReIngresarPassword = new JLabel("Reingresar Password:");
		lblReIngresarPassword.setBounds(new Rectangle(0, 0, 300, 0));
		lblReIngresarPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblReIngresarPassword.setBounds(64, 583, 212, 40);
		JLabel lblCorreo = new JLabel("Correo electr\u00F3nico:");
		lblCorreo.setBounds(new Rectangle(0, 0, 300, 0));
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCorreo.setBounds(64, 474, 212, 28);
		
		nombreUsuario = new JTextField();
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
		nombreUsuario.setBounds(276, 169, 250, 40);
		nombreUsuario.setColumns(10);
		
		nombre = new JTextField();
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
		nombre.setBounds(276, 241, 250, 40);
		nombre.setColumns(10);
		
		apellido = new JTextField();
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
		apellido.setBounds(715, 240, 250, 40);
		apellido.setColumns(10);
		
		documento = new JTextField();
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
		documento.setBounds(715, 335, 250, 40);
		documento.setColumns(10);
		
		direccion = new JTextField();
		direccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(direccion.getText().length()>=50&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }
			}
		});
		direccion.setBounds(new Rectangle(0, 0, 0, 40));
		direccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		direccion.setBounds(276, 404, 450, 40);
		direccion.setColumns(10);
		
		password1 = new JPasswordField();
		password1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(password1.getText().length()>=25&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }
			}
		});
		password1.setBounds(new Rectangle(0, 0, 0, 40));
		password1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		password1.setBounds(276, 531, 206, 40);
		
		password2 = new JPasswordField();
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
		password2.setBounds(276, 583, 206, 40);
		
		correo = new JTextField();
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
		correo.setBounds(276, 468, 450, 40);
		correo.setColumns(10);
		
		
		
		
		// Combobox Tipo de DOCUMENTO
		comboTipoDocumento = new JComboBox();
		comboTipoDocumento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboTipoDocumento.setBounds(276, 337, 250, 40);
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
		comboTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboTipoUsuario.setBounds(276, 95, 250, 40);
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
						boolean existeUsuario = serviciosUsuarios.existeNombreUsuario(u);

						if (serviciosUsuarios.existeNombreUsuario(u)) {
							errores = true;
							strerror = " El usuario ya existe. ";
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
						strerror= strerror + " El correo ingresado no es válido. ";
					}
					if (TipoDocumento.valueOf(comboTipoDocumento.getSelectedItem().toString()) == TipoDocumento.CI) {
						if (isCiValid(documento.getText())) {
							u.setEmail(correo.getText());
						}else {
							errores=true;
							strerror= strerror + " Cédula de identidad inválida. ";
						}
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
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(new Rectangle(0, 0, 300, 0));
		label.setBounds(527, 107, 33, 28);
		frmGeonatAlta.getContentPane().add(label);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(new Rectangle(0, 0, 300, 0));
		label_2.setBounds(527, 183, 33, 28);
		frmGeonatAlta.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_3.setBounds(new Rectangle(0, 0, 300, 0));
		label_3.setBounds(527, 255, 33, 28);
		frmGeonatAlta.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_4.setBounds(new Rectangle(0, 0, 300, 0));
		label_4.setBounds(966, 254, 33, 28);
		frmGeonatAlta.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_5.setBounds(new Rectangle(0, 0, 300, 0));
		label_5.setBounds(527, 350, 33, 28);
		frmGeonatAlta.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("*");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_6.setBounds(new Rectangle(0, 0, 300, 0));
		label_6.setBounds(966, 358, 33, 17);
		frmGeonatAlta.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("*");
		label_7.setForeground(Color.RED);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_7.setBounds(new Rectangle(0, 0, 300, 0));
		label_7.setBounds(728, 482, 33, 28);
		frmGeonatAlta.getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("*");
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_8.setBounds(new Rectangle(0, 0, 300, 0));
		label_8.setBounds(483, 545, 33, 28);
		frmGeonatAlta.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("*");
		label_9.setForeground(Color.RED);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_9.setBounds(new Rectangle(0, 0, 300, 0));
		label_9.setBounds(483, 595, 33, 28);
		frmGeonatAlta.getContentPane().add(label_9);
		
		JLabel label_10 = new JLabel("*");
		label_10.setForeground(Color.RED);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_10.setBounds(new Rectangle(0, 0, 300, 0));
		label_10.setBounds(48, 737, 33, 28);
		frmGeonatAlta.getContentPane().add(label_10);
		
		JLabel lblCamposObligatorios = new JLabel("Campos obligatorios");
		lblCamposObligatorios.setForeground(Color.DARK_GRAY);
		lblCamposObligatorios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCamposObligatorios.setBounds(new Rectangle(0, 0, 300, 0));
		lblCamposObligatorios.setBounds(64, 737, 200, 28);
		frmGeonatAlta.getContentPane().add(lblCamposObligatorios);
		
		JLabel lblSinPuntosNi = new JLabel("Sin puntos ni guiones");
		lblSinPuntosNi.setForeground(Color.DARK_GRAY);
		lblSinPuntosNi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSinPuntosNi.setBounds(new Rectangle(0, 0, 300, 0));
		lblSinPuntosNi.setBounds(966, 343, 200, 17);
		frmGeonatAlta.getContentPane().add(lblSinPuntosNi);

		
	}
	
	// Devuelve true si todos los campos obligatorios NO están vacíos
	private boolean hayCamposVacios() {
		List<JTextField> array = new ArrayList<JTextField>() {
			{
				getContentPane().add(nombreUsuario);
				getContentPane().add(nombre);
				getContentPane().add(apellido);
				getContentPane().add(documento);
				getContentPane().add(correo);
				getContentPane().add(password1);
				getContentPane().add(password2);
			}
		};

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
	// Patrón para validar el email
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

		// Multiplico cada dígito de la cédula por cada factor dado
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
