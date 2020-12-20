package com.presentacion.gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Usuario;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JPasswordField;
import javax.swing.InputMap;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField jpassClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login en el sistema GEONat");
		setForeground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login GEONat");
		lblLogin.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblLogin.setBounds(158, 25, 172, 37);
		contentPane.add(lblLogin);
		
		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(38, 100, 86, 20);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a :");
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasea.setBounds(38, 142, 86, 20);
		contentPane.add(lblContrasea);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(134, 97, 221, 26);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		jpassClave = new JPasswordField();
		jpassClave.setBounds(134, 139, 221, 26);
		contentPane.add(jpassClave);
		
		JButton btIngresar = new JButton("Ingresar");
		btIngresar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				validarLogin();
				
//				char[] clave = jpassClave.getPassword();
//				String claveFinal = new String(clave);
				
//			if(textUsuario.getText().toUpperCase().equals("ADMINISTRADOR") && claveFinal.toUpperCase().equals("ADMINISTRADOR")) {
//				dispose();
//				JOptionPane.showConfirmDialog(null,"Bienvenido al Sistema","Login Correcto",
//					JOptionPane.in);
//				Login p = new Login();
//				p.setVisible(false);
//				
//				EventQueue.invokeLater(new Runnable() {
//					public void run() {
//						try {
//							FramePrincipal window = new FramePrincipal();
//							window.frmGeonat.setVisible(true);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//				});
//				
//				
//			}
//			else {
//				JOptionPane.showMessageDialog(null,"Usuario o Cantraseña Incorrectos","ERROR",
//					JOptionPane.ERROR_MESSAGE);
//				
//				textUsuario.setText("");
//				jpassClave.setText("");
//				textUsuario.requestFocus();
//				}
			}
		});
		
		InputMap im = btIngresar.getInputMap();
        im.put(KeyStroke.getKeyStroke("ENTER"), "pressed");
        im.put(KeyStroke.getKeyStroke("released ENTER"), "released");

        
        getRootPane().setDefaultButton(btIngresar);
		
		
		
		btIngresar.setBounds(298, 231, 115, 29);
		contentPane.add(btIngresar);
		
		JButton btSalir = new JButton("Salir");
		btSalir.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(WIDTH);
			}
		});
		btSalir.setBounds(38, 231, 115, 29);
		contentPane.add(btSalir);
	}


	protected void validarLogin() {
		// TODO Auto-generated method stub
		//Comienzo validaciones de usuario:
		ServiciosUsuario serviciosUsuario = ServiciosUsuario.getInstance();
		//Variables que ingreso en el text del login
		String nombreUsuario = textUsuario.getText();
		String clave = String.valueOf(jpassClave.getPassword());
		
		
		//Varibles para comparar los resultas obtenido de la lista.
		String perfil = null;
		String claveUsuario = null;
		String naUsuario = null;
		boolean existeUsuario = false;
		
		
		
		//Usuario usuario = new Usuario();
		//usuario.setNombreUsuario(nombreUsuario);
		Usuario usuarioObtenido = new Usuario();
		try {
			usuarioObtenido = serviciosUsuario.obtenerPorNombreOld(nombreUsuario);
			System.out.println(usuarioObtenido.getNombreUsuario());
		}catch (ServiciosException err) {
			err.printStackTrace();
		}
	
		if(!usuarioObtenido.getNombreUsuario().isEmpty()) {
			existeUsuario = true;
			naUsuario = usuarioObtenido.getNombreUsuario();
			claveUsuario = usuarioObtenido.getPassword();
			perfil = String.valueOf(usuarioObtenido.getTipoUsuario());
		}else  {
			JOptionPane.showMessageDialog(null,"Usuario o Cantraseña Incorrectos","ERROR",JOptionPane.ERROR_MESSAGE);
			textUsuario.setText("");
			jpassClave.setText("");
			textUsuario.requestFocus();
		}

		if(existeUsuario) {

			if(nombreUsuario.toUpperCase().equals(naUsuario.toUpperCase()) &&  clave.equals(claveUsuario) && perfil.toUpperCase().equals("ADMINISTRADOR")) {
				dispose();
				JOptionPane.showMessageDialog(null,"Bienvenido al Sistema","Login Correcto",JOptionPane.INFORMATION_MESSAGE);
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
							FramePrincipal window = new FramePrincipal();
							window.frmGeonat.setVisible(true);
						
					}
				});
				
			}else if(nombreUsuario.toUpperCase().equals(naUsuario.toUpperCase()) &&  clave.equals(claveUsuario) && perfil.toUpperCase().equals("EXPERTO")) {
				dispose();
				JOptionPane.showMessageDialog(null,"Bienvenido al Sistema","Login Correcto",JOptionPane.INFORMATION_MESSAGE);
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
							FramePrincipal window = new FramePrincipal();
							window.btnObservacionesRegistrar.setEnabled(false);
							window.btnObservacionesModificar.setEnabled(false);
							window.btnObservacionesVerListado.setEnabled(true);
							window.btnUsuariosRegistrar.setEnabled(false);
							window.btnUsuarioModificar.setEnabled(false);
							window.btnUsuariosVerListado.setEnabled(false);
							window.btnFenomenoRegistrar.setEnabled(false);
							window.btnRegistrarCaracteristica.setEnabled(false);
							window.btnModificarCaracteristica.setEnabled(false);
							window.btnListarCaracterisitica.setEnabled(false);
							//window.btnCrearDatosPrueba.setEnabled(false);
							window.btnGestionFenomeno.setEnabled(false);
							window.btnGestin.setEnabled(false);
							window.frmGeonat.setVisible(true);
							
						
					}
				});
			}
				//Para ONG
				else if(nombreUsuario.toUpperCase().equals(naUsuario.toUpperCase()) &&  clave.equals(claveUsuario) && perfil.toUpperCase().equals("ONG") ) {
					dispose();
					JOptionPane.showMessageDialog(null,"Bienvenido al Sistema","Login Correcto",JOptionPane.INFORMATION_MESSAGE);
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {
								FramePrincipal window = new FramePrincipal();
								window.btnObservacionesRegistrar.setEnabled(false);
								window.btnObservacionesModificar.setEnabled(false);
								window.btnObservacionesVerListado.setEnabled(true);
								window.btnUsuariosRegistrar.setEnabled(false);
								window.btnUsuarioModificar.setEnabled(false);
								window.btnUsuariosVerListado.setEnabled(false);
								window.btnFenomenoRegistrar.setEnabled(false);
								window.btnRegistrarCaracteristica.setEnabled(false);
								window.btnModificarCaracteristica.setEnabled(false);
								window.btnListarCaracterisitica.setEnabled(false);
								//window.btnCrearDatosPrueba.setEnabled(false);
								window.btnUsuariosRegistrar.setEnabled(false);
								window.btnGestionFenomeno.setEnabled(false);
								window.btnGestin.setEnabled(false);
								window.btnBajaFenomeno.setEnabled(false);
								window.frmGeonat.setVisible(true);
							
						}
					});
				}
					else if(nombreUsuario.toUpperCase().equals(naUsuario.toUpperCase()) &&  clave.equals(claveUsuario) && perfil.toUpperCase().equals("ORGANISMOPRIVADO") ) {
						dispose();
						JOptionPane.showMessageDialog(null,"Bienvenido al Sistema","Login Correcto",JOptionPane.INFORMATION_MESSAGE);
						
						EventQueue.invokeLater(new Runnable() {
							public void run() {
									FramePrincipal window = new FramePrincipal();
									window.btnObservacionesRegistrar.setEnabled(false);
									window.btnObservacionesModificar.setEnabled(false);
									window.btnObservacionesVerListado.setEnabled(true);
									window.btnUsuariosRegistrar.setEnabled(false);
									window.btnUsuarioModificar.setEnabled(false);
									window.btnUsuariosVerListado.setEnabled(false);
									window.btnFenomenoRegistrar.setEnabled(false);
									window.btnRegistrarCaracteristica.setEnabled(false);
									window.btnModificarCaracteristica.setEnabled(false);
									window.btnListarCaracterisitica.setEnabled(false);
									//window.btnCrearDatosPrueba.setEnabled(false);
									window.btnUsuariosRegistrar.setEnabled(false);
									window.btnGestionFenomeno.setEnabled(false);
									window.btnGestin.setEnabled(false);
									window.btnBajaFenomeno.setEnabled(false);
									window.frmGeonat.setVisible(true);
								
							}
						});
					}
						else if(nombreUsuario.toUpperCase().equals(naUsuario.toUpperCase()) &&  clave.equals(claveUsuario) && perfil.toUpperCase().equals("USUARIO") ) {
							dispose();
							JOptionPane.showMessageDialog(null,"Bienvenido al Sistema","Login Correcto",JOptionPane.INFORMATION_MESSAGE);
							
							EventQueue.invokeLater(new Runnable() {
								public void run() {
										FramePrincipal window = new FramePrincipal();
										window.btnObservacionesRegistrar.setEnabled(false);
										window.btnObservacionesModificar.setEnabled(false);
										window.btnObservacionesVerListado.setEnabled(true);
										window.btnUsuariosRegistrar.setEnabled(false);
										window.btnUsuarioModificar.setEnabled(false);
										window.btnUsuariosVerListado.setEnabled(false);
										window.btnFenomenoRegistrar.setEnabled(false);
										window.btnRegistrarCaracteristica.setEnabled(false);
										window.btnModificarCaracteristica.setEnabled(false);
										window.btnListarCaracterisitica.setEnabled(false);
										//window.btnCrearDatosPrueba.setEnabled(false);
										window.btnUsuariosRegistrar.setEnabled(false);
										window.btnGestionFenomeno.setEnabled(false);
										window.btnGestin.setEnabled(false);
										window.btnBajaFenomeno.setEnabled(false);
										window.frmGeonat.setVisible(true);
									
								}
							});
						}
							//Solo para ver si hay problemas
							else {
								//dispose();
								JOptionPane.showMessageDialog(null,"Usuario o Cantraseña Incorrectos","ERROR",JOptionPane.ERROR_MESSAGE);
								textUsuario.setText("");
								jpassClave.setText("");
								textUsuario.requestFocus();
								
								//JOptionPane.showMessageDialog(null,"Bienvenido al Sistema","Login Correcto // Sin Perfil",JOptionPane.INFORMATION_MESSAGE);
								
								//EventQueue.invokeLater(new Runnable() {
								//	public void run() {
								//			FramePrincipal window = new FramePrincipal();
								//			window.btnObservacionesRegistrar.setEnabled(false);
								//			window.btnObservacionesModificar.setEnabled(false);
								//			window.btnObservacionesVerListado.setEnabled(false);
								//			window.btnUsuariosRegistrar.setEnabled(false);
								//			window.btnUsuarioModificar.setEnabled(false);
								//			window.btnUsuariosVerListado.setEnabled(false);
								//			window.btnFenomenoRegistrar.setEnabled(false);
								//			window.btnRegistrarCaracteristica.setEnabled(false);
								//			window.btnModificarCaracteristica.setEnabled(false);
								//			window.btnListarCaracterisitica.setEnabled(false);
								//			window.btnCrearDatosPrueba.setEnabled(false);
								//			window.btnUsuariosRegistrar.setEnabled(false);
								//			window.btnGestionFenomeno.setEnabled(false);
								//			window.frmGeonat.setVisible(true);
								//		
								//	}
								//});
								// HOLA MUNDO
							}

	}
		
	}
}
