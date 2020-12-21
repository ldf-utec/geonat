package com.presentacion.gui;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.presentacion.SessionData;
import com.presentacion.gui.caracteristica.AltaCaracteristica;
import com.presentacion.gui.caracteristica.FrameListarCaracteristicas;
import com.presentacion.gui.caracteristica.ModificacionCaracteristica;
import com.presentacion.gui.fenomenos.AltaFenomeno;
import com.presentacion.gui.fenomenos.FrmGestionFenomeno;
import com.presentacion.gui.fenomenos.FrameListarFenomenos;
import com.presentacion.gui.fenomenos.NewFrameModificar;
import com.presentacion.gui.fenomenos.frmBajaFenomenos;
import com.presentacion.gui.observaciones.GestionObservaciones;
import com.presentacion.gui.usuarios.AltaUsuario;
import com.presentacion.gui.usuarios.FrameListarUsuarios;
import com.presentacion.gui.usuarios.ModificacionUsuario_OLD;
import com.presentacion.gui.usuarios.ModificarUsuario;


import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
//import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Rectangle;

public class FramePrincipal {
JFrame frmGeonat;
	
	JButton btnObservacionesRegistrar, btnObservacionesModificar, btnObservacionesVerListado, btnUsuariosRegistrar;
	JButton btnUsuarioModificar;
	JButton btnUsuariosVerListado;
	JButton btnFenomenoRegistrar;
	JButton btnGestionFenomeno;
	JButton btnRegistrarCaracteristica;
	JButton btnModificarCaracteristica;
	JButton btnListarCaracterisitica;
	JButton btnCrearDatosPrueba;
	JButton btnGestin;
	JButton btnBajaFenomeno;
	JButton btnListadoFenomeno;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 18));
					UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 16));
					UIManager.put("TitledBorder.font", new Font("Tahoma", Font.PLAIN, 16));
					
					FramePrincipal window = new FramePrincipal();
					
					window.frmGeonat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public FramePrincipal() {
		initialize();
	}
	
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGeonat = new JFrame();
		frmGeonat.setMinimumSize(new Dimension(1200, 800));
		frmGeonat.setPreferredSize(new Dimension(800, 600));
		frmGeonat.setResizable(false);
		frmGeonat.setSize(new Dimension(800, 600));
		frmGeonat.setTitle("GEONat");
		frmGeonat.setBounds(100, 100, 552, 361);
		frmGeonat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGeonat.getContentPane().setLayout(null);
		
		JPanel panelUsuarios = new JPanel();
		panelUsuarios.setBorder(new TitledBorder(null, "Gesti\u00F3n de Usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelUsuarios.setBounds(10, 370, 350, 350);
		frmGeonat.getContentPane().add(panelUsuarios);
		panelUsuarios.setLayout(null);
		
		btnUsuariosRegistrar = new JButton("Registrar");
		btnUsuariosRegistrar.setBounds(new Rectangle(0, 0, 300, 0));
		btnUsuariosRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUsuariosRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		btnUsuariosRegistrar.setBounds(21, 50, 300, 50);
		panelUsuarios.add(btnUsuariosRegistrar);
		
		btnUsuariosVerListado = new JButton("Ver listado / Dar de Baja");
		btnUsuariosVerListado.setBounds(new Rectangle(0, 0, 300, 0));
		btnUsuariosVerListado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUsuariosVerListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FrameListarUsuarios window = new FrameListarUsuarios();
							window.frmListarUsuarios.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				});
			}
		});
		btnUsuariosVerListado.setBounds(21, 250, 300, 50);
		panelUsuarios.add(btnUsuariosVerListado);
		
		btnUsuarioModificar = new JButton("Modificar");
		btnUsuarioModificar.setBounds(new Rectangle(0, 0, 300, 0));
		btnUsuarioModificar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUsuarioModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		});
		btnUsuarioModificar.setBounds(21, 150, 300, 50);
		panelUsuarios.add(btnUsuarioModificar);
		
		JPanel panelObservaciones = new JPanel();
		panelObservaciones.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Gesti\u00F3n de Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelObservaciones.setBounds(10, 132, 1178, 226);
		frmGeonat.getContentPane().add(panelObservaciones);
		panelObservaciones.setLayout(null);
		
		btnObservacionesRegistrar = new JButton("Registrar");
		btnObservacionesRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnObservacionesRegistrar.setEnabled(false);
		btnObservacionesRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
			}
		});
		btnObservacionesRegistrar.setBounds(22, 34, 300, 50);
		panelObservaciones.add(btnObservacionesRegistrar);
		
		btnObservacionesVerListado = new JButton("Ver listado");
		btnObservacionesVerListado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnObservacionesVerListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GestionObservaciones window = new GestionObservaciones();
							window.frmGestionObservaciones.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
								
			}
			
		});
		btnObservacionesVerListado.setBounds(22, 151, 300, 50);
		panelObservaciones.add(btnObservacionesVerListado);
		
		btnObservacionesModificar = new JButton("Modificar");
		btnObservacionesModificar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnObservacionesModificar.setEnabled(false);
		btnObservacionesModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnObservacionesModificar.setBounds(22, 91, 300, 50);
		panelObservaciones.add(btnObservacionesModificar);
		
		JLabel lblTotalDeObservaciones = new JLabel("Total de observaciones Registradas:");
		lblTotalDeObservaciones.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTotalDeObservaciones.setEnabled(false);
		lblTotalDeObservaciones.setBounds(427, 49, 346, 26);
		panelObservaciones.add(lblTotalDeObservaciones);
		lblTotalDeObservaciones.setVisible(false);
		
		JLabel label_1 = new JLabel("?");
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label_1.setBounds(802, 51, 62, 24);
		panelObservaciones.add(label_1);
		label_1.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 1178, 109);
		frmGeonat.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel img_logo = new JLabel("");
		img_logo.setHorizontalAlignment(SwingConstants.CENTER);
		img_logo.setIcon(new ImageIcon(FramePrincipal.class.getResource("/logo.png")));
		img_logo.setBounds(474, 6, 225, 78);
		panel.add(img_logo);
		
		JLabel lblRegistroDeObservaciones = new JLabel("Registro de observaciones medioambientales");
		lblRegistroDeObservaciones.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblRegistroDeObservaciones.setForeground(Color.DARK_GRAY);
		lblRegistroDeObservaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeObservaciones.setBounds(6, 75, 1166, 25);
		panel.add(lblRegistroDeObservaciones);
		
		JLabel lblUsuarioActual = new JLabel("Usuario Actual:");
		lblUsuarioActual.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblUsuarioActual.setBounds(897, 21, 117, 25);
		panel.add(lblUsuarioActual);
		
		JLabel txtUsuarioActual = new JLabel("anonim");
		txtUsuarioActual.setHorizontalAlignment(SwingConstants.RIGHT);
		txtUsuarioActual.setFont(new Font("SansSerif", Font.ITALIC, 16));
		txtUsuarioActual.setBounds(1010, 20, 150, 24);
		txtUsuarioActual.setText(SessionData.usuarioActual);
		panel.add(txtUsuarioActual);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Gesti\u00F3n de Caracter\u00EDsticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(838, 370, 350, 350);
		frmGeonat.getContentPane().add(panel_1);
		
		btnRegistrarCaracteristica = new JButton("Registrar");
		btnRegistrarCaracteristica.setBounds(new Rectangle(0, 0, 300, 0));
		btnRegistrarCaracteristica.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRegistrarCaracteristica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AltaCaracteristica window = new AltaCaracteristica();
							window.frmG.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnRegistrarCaracteristica.setBounds(23, 51, 300, 50);
		panel_1.add(btnRegistrarCaracteristica);
		
		btnListarCaracterisitica = new JButton("Ver listado / Dar de Baja");
		btnListarCaracterisitica.setBounds(new Rectangle(0, 0, 300, 0));
		btnListarCaracterisitica.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnListarCaracterisitica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FrameListarCaracteristicas window = new FrameListarCaracteristicas();
							window.frmListarCaracteristicas.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnListarCaracterisitica.setBounds(23, 251, 300, 50);
		panel_1.add(btnListarCaracterisitica);
		
		btnModificarCaracteristica = new JButton("Modificar");
		btnModificarCaracteristica.setBounds(new Rectangle(0, 0, 300, 0));
		btnModificarCaracteristica.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnModificarCaracteristica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {				
						
						try {
							ModificacionCaracteristica window = new ModificacionCaracteristica();
							window.frmModificarCaracteristica.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnModificarCaracteristica.setBounds(23, 151, 300, 50);
		panel_1.add(btnModificarCaracteristica);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Gesti\u00F3n de Fen\u00F3menos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(429, 370, 350, 350);
		frmGeonat.getContentPane().add(panel_2);
		
		btnFenomenoRegistrar = new JButton("Registrar");
		btnFenomenoRegistrar.setBounds(new Rectangle(0, 0, 300, 0));
		btnFenomenoRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFenomenoRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AltaFenomeno window = new AltaFenomeno();
							window.frmAltaFenomenos.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnFenomenoRegistrar.setBounds(23, 110, 300, 50);
		panel_2.add(btnFenomenoRegistrar);

		
		btnGestionFenomeno = new JButton("Modificar");
		btnGestionFenomeno.setBounds(new Rectangle(0, 0, 300, 0));
		btnGestionFenomeno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGestionFenomeno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							NewFrameModificar frame = new NewFrameModificar();
							frame.frmGestionFenomenos.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnGestionFenomeno.setBounds(23, 190, 300, 50);
		panel_2.add(btnGestionFenomeno);
		
		btnBajaFenomeno = new JButton("Ver listado / Dar de Baja");
		btnBajaFenomeno.setBounds(new Rectangle(0, 0, 300, 0));
		btnBajaFenomeno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBajaFenomeno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBajaFenomenos window = new frmBajaFenomenos();
				window.frmListarFenomenos.setVisible(true);
			}
		});
		btnBajaFenomeno.setBounds(23, 270, 300, 50);
		panel_2.add(btnBajaFenomeno);
		
		btnGestin = new JButton("Gesti\u00F3n");
		btnGestin.setBounds(new Rectangle(0, 0, 300, 0));
		btnGestin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGestin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmGestionFenomeno window = new FrmGestionFenomeno();
				window.frmGestinDeFenmeno.setVisible(true);
			}
		});
		btnGestin.setBounds(23, 30, 300, 50);
		panel_2.add(btnGestin);
		
//		btnListadoFenomeno = new JButton("Listado");
//		btnListadoFenomeno.setEnabled(false);
//		btnListadoFenomeno.addActionListener(new ActionListener() {			
//			public void actionPerformed(ActionEvent e) {
//		}
//				FrameListarFenomenos window = new FrameListarFenomenos();
//				window.frmListarFenomenos.setVisible(true);
//			}
//		});
//		btnListadoFenomeno.setBounds(10, 173, 199, 23);
//		panel_2.add(btnListadoFenomeno);
		

//		btnCrearDatosPrueba = new JButton("Crear datos de prueba");
//		btnCrearDatosPrueba.setEnabled(false);
//		btnCrearDatosPrueba.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				DatosDePrueba.getInstance();
//			}
//		});
//		btnCrearDatosPrueba.setBounds(582, 537, 181, 23);
//		frmGeonat.getContentPane().add(btnCrearDatosPrueba);
	}
}
