package com.presentacion.gui;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.presentacion.gui.caracteristica.AltaCaracteristica;
import com.presentacion.gui.fenomenos.AltaFenomeno;
import com.presentacion.gui.fenomenos.FrameListarFenomenos;
import com.presentacion.gui.fenomenos.ModificacionFenomeno;
import com.presentacion.gui.usuarios.AltaUsuario;
import com.presentacion.gui.usuarios.FrameListarUsuarios;
import com.presentacion.gui.usuarios.ModificacionUsuario;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
//import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FramePrincipal {
// prueba jj d
	JFrame frmGeonat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		frmGeonat.setMinimumSize(new Dimension(800, 600));
		frmGeonat.setPreferredSize(new Dimension(800, 600));
		frmGeonat.setResizable(false);
		frmGeonat.setSize(new Dimension(800, 600));
		frmGeonat.setTitle("GEONat");
		frmGeonat.setBounds(100, 100, 552, 361);
		frmGeonat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGeonat.getContentPane().setLayout(null);
		
		JPanel panelUsuarios = new JPanel();
		panelUsuarios.setBorder(new TitledBorder(null, "Gesti\u00F3n de Usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelUsuarios.setBounds(10, 297, 220, 263);
		frmGeonat.getContentPane().add(panelUsuarios);
		panelUsuarios.setLayout(null);
		
		JButton btnUsuariosRegistrar = new JButton("Registrar");
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
		btnUsuariosRegistrar.setBounds(10, 32, 199, 23);
		panelUsuarios.add(btnUsuariosRegistrar);
		
		JButton btnUsuariosVerListado = new JButton("Ver listado / Dar de Baja");
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
		btnUsuariosVerListado.setBounds(10, 100, 199, 23);
		panelUsuarios.add(btnUsuariosVerListado);
		
		JButton button = new JButton("Modificar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		});
		button.setBounds(10, 66, 199, 23);
		panelUsuarios.add(button);
		
		JPanel panel_TiposDeUsuario = new JPanel();
		panel_TiposDeUsuario.setLayout(null);
		panel_TiposDeUsuario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipos de Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_TiposDeUsuario.setBounds(10, 134, 200, 125);
		panelUsuarios.add(panel_TiposDeUsuario);
		
		JButton button_7 = new JButton("Registrar");
		button_7.setBounds(10, 23, 157, 23);
		panel_TiposDeUsuario.add(button_7);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(10, 91, 157, 23);
		panel_TiposDeUsuario.add(btnEliminar);
		
		JButton button_9 = new JButton("Modificar");
		button_9.setBounds(10, 57, 157, 23);
		panel_TiposDeUsuario.add(button_9);
		
		JPanel panelObservaciones = new JPanel();
		panelObservaciones.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Gesti\u00F3n de Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelObservaciones.setBounds(10, 132, 774, 154);
		frmGeonat.getContentPane().add(panelObservaciones);
		panelObservaciones.setLayout(null);
		
		JButton btnObservacionesRegistrar = new JButton("Registrar");
		btnObservacionesRegistrar.setEnabled(false);
		btnObservacionesRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
			}
		});
		btnObservacionesRegistrar.setBounds(10, 31, 199, 23);
		panelObservaciones.add(btnObservacionesRegistrar);
		
		JButton btnObservacionesVerListado = new JButton("Ver listado");
		btnObservacionesVerListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
								
			}
			
		});
		btnObservacionesVerListado.setBounds(10, 99, 199, 23);
		panelObservaciones.add(btnObservacionesVerListado);
		
		JButton btnObservacionesModificar = new JButton("Modificar");
		btnObservacionesModificar.setEnabled(false);
		btnObservacionesModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnObservacionesModificar.setBounds(10, 65, 199, 23);
		panelObservaciones.add(btnObservacionesModificar);
		
		JLabel lblTotalDeObservaciones = new JLabel("Total de observaciones Registradas:");
		lblTotalDeObservaciones.setBounds(307, 35, 246, 14);
		panelObservaciones.add(lblTotalDeObservaciones);
		
		JLabel label_1 = new JLabel("?");
		label_1.setBounds(553, 35, 46, 14);
		panelObservaciones.add(label_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 774, 100);
		frmGeonat.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel img_logo = new JLabel("");
		img_logo.setHorizontalAlignment(SwingConstants.CENTER);
		img_logo.setIcon(new ImageIcon(FramePrincipal.class.getResource("/logo.png")));
		img_logo.setBounds(274, 11, 225, 78);
		panel.add(img_logo);
		
		JLabel lblRegistroDeObservaciones = new JLabel("Registro de observaciones medioambientales");
		lblRegistroDeObservaciones.setForeground(Color.DARK_GRAY);
		lblRegistroDeObservaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeObservaciones.setBounds(10, 75, 754, 25);
		panel.add(lblRegistroDeObservaciones);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Gesti\u00F3n de Caracter\u00EDsticas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(564, 297, 220, 193);
		frmGeonat.getContentPane().add(panel_1);
		
		
		// Alta CARACTERÍSTICA
		JButton button_1 = new JButton("Registrar");
		button_1.addActionListener(new ActionListener() {
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
		button_1.setBounds(10, 31, 199, 23);
		panel_1.add(button_1);
		
		JButton btnVerListado = new JButton("Ver listado / Dar de Baja");
		btnVerListado.setBounds(10, 99, 199, 23);
		panel_1.add(btnVerListado);
		
		JButton button_3 = new JButton("Modificar");
		button_3.setBounds(10, 65, 199, 23);
		panel_1.add(button_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Gesti\u00F3n de Fen\u00F3menos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(289, 297, 220, 263);
		frmGeonat.getContentPane().add(panel_2);
		
		JButton button_4 = new JButton("Registrar");
		button_4.addActionListener(new ActionListener() {
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
		button_4.setBounds(10, 31, 199, 23);
		panel_2.add(button_4);
		
		JButton button_5 = new JButton("Ver listado");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FrameListarFenomenos window = new FrameListarFenomenos();
							window.frmListarFenomenos.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		button_5.setBounds(10, 99, 199, 23);
		panel_2.add(button_5);
		
		JButton button_6 = new JButton("Modificar");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ModificacionFenomeno window = new ModificacionFenomeno();
							window.getFrmModificacionFenomeno().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		button_6.setBounds(10, 65, 199, 23);
		panel_2.add(button_6);
		
		JButton btnCrearDatosPrueba = new JButton("Crear datos de prueba");
		btnCrearDatosPrueba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatosDePrueba.getInstance();
			}
		});
		btnCrearDatosPrueba.setBounds(582, 537, 181, 23);
		frmGeonat.getContentPane().add(btnCrearDatosPrueba);
	}
}
