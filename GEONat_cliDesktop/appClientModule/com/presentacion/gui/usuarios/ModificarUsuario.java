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
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModificarUsuario extends JFrame {

	// Obtengo la instancia del servicio de capa lógica de negocios
	ServiciosUsuario serviciosUsuarios = ServiciosUsuario.getInstance();
	
	public JFrame frmModificarUsuario;
	private JTextField txtNombreUsuario;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDocumento;
	private JTextField txtDireccion;
	private JPasswordField txtPassword1;
	private JPasswordField txtPassword2;
	private JTextField txtCorreo;
	JComboBox comboTipoDocumento;
	JComboBox comboTipoUsuario;
	private JTextField txtFindByNombreUsuario;
	private JTextField txtFindByDocumento;
	JCheckBox checkboxUsuarioActivo;

	Integer id_usuario_editado = -1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarUsuario window = new ModificarUsuario();
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
	public ModificarUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
						
		frmModificarUsuario = new JFrame();
		frmModificarUsuario.getContentPane().setBounds(new Rectangle(0, 0, 300, 0));
		frmModificarUsuario.setResizable(false);
		frmModificarUsuario.setTitle("GEONat - Modificar Usuario");
		frmModificarUsuario.setBounds(100, 100, 1200, 800);
		frmModificarUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		lblDireccion.setBounds(64, 433, 212, 26);
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		checkboxUsuarioActivo = new JCheckBox("Activaci\u00F3n de usuario");
		checkboxUsuarioActivo.setBounds(276, 630, 284, 28);
		checkboxUsuarioActivo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		checkboxUsuarioActivo.setSelected(true);
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(64, 560, 212, 28);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblReIngresarPassword = new JLabel("Reingresar Password:");
		lblReIngresarPassword.setBounds(550, 552, 212, 40);
		lblReIngresarPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblCorreo = new JLabel("Correo electr\u00F3nico:");
		lblCorreo.setBounds(64, 494, 212, 28);
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setEnabled(false);
		txtNombreUsuario.setBounds(276, 229, 250, 40);
		txtNombreUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtNombreUsuario.getText().length()>=25&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }			
			}
		});		
		txtNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNombreUsuario.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(276, 293, 250, 40);
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtNombre.getText().length()>=25&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }	
			}
		});
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(715, 292, 250, 40);
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtApellido.getText().length()>=25&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }	
			}
		});
		txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtApellido.setColumns(10);
		
		txtDocumento = new JTextField();
		txtDocumento.setBounds(715, 357, 250, 40);
		txtDocumento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtDocumento.getText().length()>=25&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }
			}
		});
		txtDocumento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDocumento.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(276, 426, 450, 40);
		txtDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtDireccion.getText().length()>=50&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }
			}
		});
		txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDireccion.setColumns(10);
		
		txtPassword1 = new JPasswordField();
		txtPassword1.setBounds(276, 552, 206, 40);
		txtPassword1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtPassword1.getText().length()>=25&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }
			}
		});
		txtPassword1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtPassword2 = new JPasswordField();
		txtPassword2.setBounds(762, 552, 206, 40);
		txtPassword2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtPassword2.getText().length()>=25&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }
			}
		});
		txtPassword2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(276, 488, 450, 40);
		txtCorreo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtCorreo.getText().length()>=50&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            getToolkit().beep();
		            e.consume();
		         }
			}
		});
		txtCorreo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCorreo.setColumns(10);
		
		
		
		
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
					u.setId_Usuario(id_usuario_editado);
					
					if (hayCamposVacios()) {
						JOptionPane.showMessageDialog(frmModificarUsuario,  "Falta completar campos obligatorios(*)");
						return;
					}									
					
					u.setNombreUsuario(txtNombreUsuario.getText());
									
					try {
						// verifica si existe un usuario para este documento, y si ya le pertenece a otro usuario diferente al que estoy editando
						String str = txtDocumento.getText();
						Usuario usuarioBD = serviciosUsuarios.obtenerPorDocumento(txtDocumento.getText());
						if (usuarioBD != null) {
							boolean mismoUsuario = usuarioBD.getNombreUsuario().equals(txtNombreUsuario.getText()) ;
							if (!mismoUsuario) {
								// si existe otro usuario con el mismo documento, no puedo permitir que se repita este documento
								errores = true;
								strerror = strerror + "- El documento ingresado ya está en uso por otro usuario.\n";
							}
						}
						

					} catch (ServiciosException e) {
						e.printStackTrace();
					}
									

					u.setNombre(txtNombre.getText());
					u.setApellido(txtApellido.getText());
					u.setTipoDocumento(TipoDocumento.valueOf(comboTipoDocumento.getSelectedItem().toString()) );
					u.setNroDocumento(txtDocumento.getText());
					u.setDireccion(txtDireccion.getText());
					
					if (isEmailValid(txtCorreo.getText())) {
						u.setEmail(txtCorreo.getText());
					}else {
						errores=true;
						strerror= strerror + "- El correo ingresado no es válido.\n";
					}
					if (TipoDocumento.valueOf(comboTipoDocumento.getSelectedItem().toString()) == TipoDocumento.CI) {
						if (isCiValid(txtDocumento.getText())) {
							u.setEmail(txtCorreo.getText());
						}else {
							errores=true;
							strerror= strerror + "- Cédula de identidad inválida.\n";
						}
					}
					

					if (clavesIdenticas(txtPassword1.getPassword(), txtPassword2.getPassword())) {
						u.setPassword(new String(txtPassword1.getPassword()));
					} else {
						errores=true;
						strerror= strerror + "- Las contraseñas ingresadas no coinciden.\n";
					}
									
					u.setTipoUsuario(TipoUsuario.valueOf(comboTipoUsuario.getSelectedItem().toString()) );			
					u.setEstadoActivo(checkboxUsuarioActivo.isSelected());
										
					if (errores) {
						JOptionPane.showMessageDialog(null,  strerror);
					} else {
						// 0=ok, 2=cancel
						int input = JOptionPane.showConfirmDialog(null, "¿Finalizar y guardar los cambios?\nClick en Cancelar para continuar editando.",
								"Actualizar usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						
						if (input == 0) {
							try {
								serviciosUsuarios.update(u);
								JOptionPane.showMessageDialog(null,  "Usuario actualizado exitosamente.");
								frmModificarUsuario.dispose();
								
							} catch (ServiciosException err) {
								err.printStackTrace();
								JOptionPane.showMessageDialog(null,  "Error al actualizar usuario en la base de datos.\nError:\n"  + err.toString() );
							}
						}else {
							return;
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
				
				if (id_usuario_editado!=-1) {
					// 0=ok, 2=cancel
					int input = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cancelar la operación actual?\nClick en Cancelar para continuar editando.",
							"Actualizar usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if (input == 0) {
						frmModificarUsuario.dispose();;

					}else {
						return;
					}	
				}else {
					frmModificarUsuario.dispose();
				}
			}
		});
		
		
		frmModificarUsuario.getContentPane().setLayout(null);
		frmModificarUsuario.getContentPane().add(lblPassword);
		frmModificarUsuario.getContentPane().add(lblReIngresarPassword);
		frmModificarUsuario.getContentPane().add(lblTipoDeUsuario);
		frmModificarUsuario.getContentPane().add(lblCorreo);
		frmModificarUsuario.getContentPane().add(comboTipoUsuario);
		frmModificarUsuario.getContentPane().add(txtPassword2);
		frmModificarUsuario.getContentPane().add(txtPassword1);
		frmModificarUsuario.getContentPane().add(txtCorreo);
		frmModificarUsuario.getContentPane().add(lblDocumento);
		frmModificarUsuario.getContentPane().add(lblNombreDeUsuaio);
		frmModificarUsuario.getContentPane().add(lblNombre);
		frmModificarUsuario.getContentPane().add(lblApellido);
		frmModificarUsuario.getContentPane().add(TipoDoc);
		frmModificarUsuario.getContentPane().add(lblDireccion);
		frmModificarUsuario.getContentPane().add(txtDireccion);
		frmModificarUsuario.getContentPane().add(txtDocumento);
		frmModificarUsuario.getContentPane().add(comboTipoDocumento);
		frmModificarUsuario.getContentPane().add(txtApellido);
		frmModificarUsuario.getContentPane().add(txtNombre);
		frmModificarUsuario.getContentPane().add(txtNombreUsuario);
		frmModificarUsuario.getContentPane().add(checkboxUsuarioActivo);
		frmModificarUsuario.getContentPane().add(btnAltaUsuario);
		frmModificarUsuario.getContentPane().add(btnCancela);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1194, 59);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		frmModificarUsuario.getContentPane().add(panel);
		
		JLabel lblRegistroDeUsuario = new JLabel("Modificar usuario");
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
		frmModificarUsuario.getContentPane().add(label);
		
		JLabel label_2 = new JLabel("*");
		label_2.setBounds(527, 243, 33, 28);
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmModificarUsuario.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setBounds(527, 307, 33, 28);
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmModificarUsuario.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("*");
		label_4.setBounds(966, 306, 33, 28);
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmModificarUsuario.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("*");
		label_5.setBounds(527, 372, 33, 28);
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmModificarUsuario.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("*");
		label_6.setBounds(966, 380, 33, 17);
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmModificarUsuario.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("*");
		label_7.setBounds(728, 492, 33, 28);
		label_7.setForeground(Color.RED);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmModificarUsuario.getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("*");
		label_8.setBounds(483, 556, 33, 28);
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmModificarUsuario.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("*");
		label_9.setBounds(971, 552, 33, 40);
		label_9.setForeground(Color.RED);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmModificarUsuario.getContentPane().add(label_9);
		
		JLabel label_10 = new JLabel("*");
		label_10.setBounds(64, 714, 33, 28);
		label_10.setForeground(Color.RED);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmModificarUsuario.getContentPane().add(label_10);
		
		JLabel lblCamposObligatorios = new JLabel("Campos obligatorios");
		lblCamposObligatorios.setBounds(80, 714, 200, 28);
		lblCamposObligatorios.setForeground(Color.DARK_GRAY);
		lblCamposObligatorios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmModificarUsuario.getContentPane().add(lblCamposObligatorios);
		
		JLabel lblSinPuntosNi = new JLabel("Sin puntos ni guiones");
		lblSinPuntosNi.setBounds(966, 365, 200, 17);
		lblSinPuntosNi.setForeground(Color.DARK_GRAY);
		lblSinPuntosNi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmModificarUsuario.getContentPane().add(lblSinPuntosNi);
		
		JPanel panel_1 = new JPanel();
		//((javax.swing.border.TitledBorder) panel_1.getBorder()).setTitleFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar usuario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(33, 71, 1133, 75);
		frmModificarUsuario.getContentPane().add(panel_1);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombreDeUsuario.setBounds(31, 27, 184, 34);
		panel_1.add(lblNombreDeUsuario);
		
		
		
		
		txtFindByNombreUsuario = new JTextField();
		txtFindByNombreUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!txtFindByNombreUsuario.isEnabled()) {
					txtFindByDocumento.setEnabled(false);
					txtFindByNombreUsuario.setEnabled(true);
					txtFindByNombreUsuario.selectAll();
					txtFindByNombreUsuario.requestFocus();
				}
				
			}
		});
		txtFindByNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFindByNombreUsuario.setColumns(10);
		txtFindByNombreUsuario.setBounds(243, 24, 250, 40);
		panel_1.add(txtFindByNombreUsuario);
		
		
		
		
		JLabel lblDocumento_1 = new JLabel("Documento:");
		lblDocumento_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDocumento_1.setBounds(557, 27, 123, 34);
		panel_1.add(lblDocumento_1);
		
		txtFindByDocumento = new JTextField();
		txtFindByDocumento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!txtFindByDocumento.isEnabled()) {
					txtFindByNombreUsuario.setEnabled(false);
					txtFindByDocumento.setEnabled(true);
					txtFindByDocumento.selectAll();
					txtFindByDocumento.requestFocus();
				}
				
			}
		});
		txtFindByDocumento.setEnabled(false);
		txtFindByDocumento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFindByDocumento.setColumns(10);
		txtFindByDocumento.setBounds(682, 24, 250, 40);
		panel_1.add(txtFindByDocumento);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limpiarCampos();
				
				Usuario usuarioEncontrado = null;
				
				if (txtFindByNombreUsuario.isEnabled()) {
					// buscar por nombre de usuario
					txtFindByDocumento.setText("");
					try {
						usuarioEncontrado = serviciosUsuarios.obtenerPorNombre(txtFindByNombreUsuario.getText());						
					} catch (ServiciosException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,  "Error al obtener usuario. \nError: \n" + e1.getMessage().substring(0, 200));
					}
					
				} else {
					
					if(txtFindByDocumento.isEnabled()); {
						// buscar por documento
						txtFindByNombreUsuario.setText("");
						try {
							usuarioEncontrado = serviciosUsuarios.obtenerPorDocumento(txtFindByDocumento.getText());
						} catch (ServiciosException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null,  "Error al obtener usuario. \nError: \n" + e1.getMessage().substring(0, 200));
						}
					}
				}
				
				if (usuarioEncontrado != null) {
					cargarCampos(usuarioEncontrado);
				}else {
					JOptionPane.showMessageDialog(null,  "No se encontró ningún usuario.");
				}
				
				
				
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBuscar.setBounds(968, 24, 142, 40);
		panel_1.add(btnBuscar);

		
	}
	
	// Devuelve true si todos los campos obligatorios NO están vacíos
	private boolean hayCamposVacios() {
		List<JTextField> array = new ArrayList<JTextField>() {
			{
				add(txtNombreUsuario);
				add(txtNombre);
				add(txtApellido);
				add(txtDocumento);
				add(txtCorreo);
				add(txtPassword1);
				add(txtPassword2);
			}
		};

		// valido los campos de texto que no sean null
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
		Integer integ = ci.length();
		
		if((ci.length() != 7) & (ci.length() != 8)){
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
		
		if (suma == 0) {
			return false;
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

	private boolean cargarCampos(Usuario usuario) {
		try {
			id_usuario_editado = usuario.getId_Usuario();
			txtNombreUsuario.setText(usuario.getNombreUsuario());
			txtNombre.setText(usuario.getNombre());
			txtApellido.setText(usuario.getApellido());
			txtDocumento.setText(usuario.getNroDocumento());
			txtCorreo.setText(usuario.getEmail());
			txtDireccion.setText(usuario.getDireccion());
			checkboxUsuarioActivo.setSelected(usuario.getEstadoActivo());
			comboTipoDocumento.setSelectedIndex(usuario.getTipoDocumento().getId_TipoDocumento());
			comboTipoUsuario.setSelectedIndex(usuario.getTipoUsuario().getId_tipoUsuario());
			txtPassword1.setText(usuario.getPassword());
			txtPassword2.setText(usuario.getPassword());
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean limpiarCampos() {
		try {
			id_usuario_editado = -1;
			txtNombreUsuario.setText("");
			txtNombre.setText("");
			txtApellido.setText("");
			txtDocumento.setText("");
			txtCorreo.setText("");
			txtDireccion.setText("");
			checkboxUsuarioActivo.setSelected(false);
			comboTipoDocumento.setSelectedIndex(0);
			comboTipoUsuario.setSelectedIndex(0);
			txtPassword1.setText("");
			txtPassword2.setText("");
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
