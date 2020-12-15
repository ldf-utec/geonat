package com.presentacion.gui;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.presentacion.gui.caracteristica.AltaCaracteristica;
import com.presentacion.gui.caracteristica.FrameListarCaracteristicas;
import com.presentacion.gui.caracteristica.ModificacionCaracteristica;
import com.presentacion.gui.fenomenos.AltaFenomeno;
import com.presentacion.gui.fenomenos.FrmGestionFenomeno;
import com.presentacion.gui.fenomenos.ListarFenomeno;
import com.presentacion.gui.fenomenos.NewFrameModificar;
import com.presentacion.gui.observaciones.GestionObservaciones;
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
JFrame frmGeonat;
	
	JButton btnObservacionesRegistrar;
	JButton btnObservacionesModificar;
	JButton btnObservacionesVerListado;
	JButton btnUsuariosRegistrar;
	JButton btnUsuarioModificar;
	JButton btnUsuariosVerListado;
	JButton btnFenomenoRegistrar;
	JButton btnGestionFenomeno;
	JButton btnRegistrarCaracteristica;
	JButton btnModificarCaracteristica;
	JButton btnListarCaracterisitica;
	JButton btnCrearDatosPrueba;
	private JButton btnGestin;
	
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
		panelUsuarios.setBounds(10, 297, 220, 193);
		frmGeonat.getContentPane().add(panelUsuarios);
		panelUsuarios.setLayout(null);
		
		btnUsuariosRegistrar = new JButton("Registrar");
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
		
		btnUsuariosVerListado = new JButton("Dar de Baja");
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
		
		btnUsuarioModificar = new JButton("Modificar");
		btnUsuarioModificar.addActionListener(new ActionListener() {
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
		btnUsuarioModificar.setBounds(10, 66, 199, 23);
		panelUsuarios.add(btnUsuarioModificar);
		
		JPanel panelObservaciones = new JPanel();
		panelObservaciones.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Gesti\u00F3n de Observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelObservaciones.setBounds(10, 132, 774, 154);
		frmGeonat.getContentPane().add(panelObservaciones);
		panelObservaciones.setLayout(null);
		
		btnObservacionesRegistrar = new JButton("Registrar");
		btnObservacionesRegistrar.setEnabled(false);
		btnObservacionesRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
			}
		});
		btnObservacionesRegistrar.setBounds(10, 31, 199, 23);
		panelObservaciones.add(btnObservacionesRegistrar);
		
		btnObservacionesVerListado = new JButton("Ver listado");
		btnObservacionesVerListado.setBorder(null);
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
		btnObservacionesVerListado.setBounds(10, 99, 199, 23);
		panelObservaciones.add(btnObservacionesVerListado);
		
		btnObservacionesModificar = new JButton("Modificar");
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
		lblTotalDeObservaciones.setVisible(false);
		
		JLabel label_1 = new JLabel("?");
		label_1.setBounds(553, 35, 46, 14);
		panelObservaciones.add(label_1);
		label_1.setVisible(false);
		
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
		panel_1.setBorder(new TitledBorder(null, "Gesti\u00F3n de Caracter\u00EDsticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(564, 297, 220, 193);
		frmGeonat.getContentPane().add(panel_1);
		
		btnRegistrarCaracteristica = new JButton("Registrar");
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
		btnRegistrarCaracteristica.setBounds(10, 31, 199, 23);
		panel_1.add(btnRegistrarCaracteristica);
		
		btnListarCaracterisitica = new JButton("Dar de Baja");
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
		btnListarCaracterisitica.setBounds(10, 99, 199, 23);
		panel_1.add(btnListarCaracterisitica);
		
		btnModificarCaracteristica = new JButton("Modificar");
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
		btnModificarCaracteristica.setBounds(10, 65, 199, 23);
		panel_1.add(btnModificarCaracteristica);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Gesti\u00F3n de Fen\u00F3menos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(289, 297, 220, 193);
		frmGeonat.getContentPane().add(panel_2);
		
		btnFenomenoRegistrar = new JButton("Registrar");
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
		btnFenomenoRegistrar.setBounds(10, 60, 199, 23);
		panel_2.add(btnFenomenoRegistrar);

		
		btnGestionFenomeno = new JButton("Modificar");
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
		btnGestionFenomeno.setBounds(10, 98, 199, 23);
		panel_2.add(btnGestionFenomeno);
		
		JButton btnListadoFenomeno = new JButton("Dar de Baja");
		btnListadoFenomeno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarFenomeno frame = new ListarFenomeno();
				frame.frmGestionFenomenos.setVisible(true);
			}
		});
		btnListadoFenomeno.setBounds(10, 132, 199, 23);
		panel_2.add(btnListadoFenomeno);
		
		btnGestin = new JButton("Gesti\u00F3n");
		btnGestin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmGestionFenomeno window = new FrmGestionFenomeno();
				window.frmGestinDeFenmeno.setVisible(true);
			}
		});
		btnGestin.setBounds(10, 26, 199, 23);
		panel_2.add(btnGestin);
		

		btnCrearDatosPrueba = new JButton("Crear datos de prueba");
		btnCrearDatosPrueba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatosDePrueba.getInstance();
			}
		});
		btnCrearDatosPrueba.setBounds(582, 537, 181, 23);
		frmGeonat.getContentPane().add(btnCrearDatosPrueba);
	}
}
