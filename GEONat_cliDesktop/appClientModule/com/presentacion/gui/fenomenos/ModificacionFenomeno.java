package com.presentacion.gui.fenomenos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.DAO.interfaces.IFenomenoDAO;
import com.entities.Fenomeno;
import com.exception.ServiciosException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;


public class ModificacionFenomeno {

	private JFrame frmModificacionFenomeno;
	private JTextField txtFNombre;
	private JTextField txtFDescripcion;
	private JTextField txtFTelEmergencia;
	private JTextField txtFCodFenomeno;
	private int limiteNombre = 50;
	private int limiteDescripcion = 200;
	private int limiteTel = 20;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public ModificacionFenomeno() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmModificacionFenomeno(new JFrame());
		getFrmModificacionFenomeno().setTitle("GEONat - Modificaci\u00F3n de fen\u00F3meno");
		getFrmModificacionFenomeno().setBounds(100, 100, 450, 300);
		getFrmModificacionFenomeno().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getFrmModificacionFenomeno().getContentPane().setLayout(null);
		
		JLabel lblCodFenomeno = new JLabel("C\u00F3digo del Fen\u00F3meno");
		lblCodFenomeno.setBounds(10, 11, 141, 14);
		getFrmModificacionFenomeno().getContentPane().add(lblCodFenomeno);
	
		
		txtFCodFenomeno = new JTextField();
		txtFCodFenomeno.setBounds(145, 8, 111, 20);
		getFrmModificacionFenomeno().getContentPane().add(txtFCodFenomeno);
		txtFCodFenomeno.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre del Fen\u00F3meno");
		lblNombre.setBounds(10, 49, 118, 14);
		getFrmModificacionFenomeno().getContentPane().add(lblNombre);
		
		txtFNombre = new JTextField();
		txtFNombre.setBounds(145, 46, 207, 20);
		getFrmModificacionFenomeno().getContentPane().add(txtFNombre);
		txtFNombre.setColumns(10);
		txtFNombre.setVisible(false);
		txtFNombre.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if(txtFNombre.getText().length() > limiteNombre) {
					JOptionPane.showMessageDialog(null,"Debe ingresar menos de "+limiteNombre+" caracteres");
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setBounds(10, 92, 118, 14);
		getFrmModificacionFenomeno().getContentPane().add(lblDescripcion);
		
		txtFDescripcion = new JTextField();
		txtFDescripcion.setBounds(145, 89, 207, 56);
		getFrmModificacionFenomeno().getContentPane().add(txtFDescripcion);
		txtFDescripcion.setColumns(10);
		txtFDescripcion.setVisible(false);
		txtFDescripcion.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if(txtFDescripcion.getText().length() > limiteDescripcion) {
					JOptionPane.showMessageDialog(null,"Debe ingresar menos de "+limiteDescripcion+" caracteres");
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});
		
		JLabel lblTelEmergencia = new JLabel("Tel\u00E9fonos de Emergencia");
		lblTelEmergencia.setBounds(10, 166, 141, 14);
		getFrmModificacionFenomeno().getContentPane().add(lblTelEmergencia);
		
		txtFTelEmergencia = new JTextField();
		txtFTelEmergencia.setBounds(145, 156, 207, 24);
		getFrmModificacionFenomeno().getContentPane().add(txtFTelEmergencia);
		txtFTelEmergencia.setColumns(10);
		txtFTelEmergencia.setVisible(false);
		txtFTelEmergencia.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if(txtFTelEmergencia.getText().length() > limiteTel) {
					JOptionPane.showMessageDialog(null,"Debe ingresar menos de "+limiteTel+" números");
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});
		
				
		JButton btnBuscarFenomeno = new JButton("Buscar Fen\u00F3meno");
		btnBuscarFenomeno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    IFenomenoDAO fenBean = null;
				Fenomeno fen = new Fenomeno();
				if (txtFCodFenomeno.getText() == null) {
					JOptionPane.showMessageDialog(null,  "Debe ingresar un codigo de fenomeno");
				} else {
					int id = Integer.parseInt(txtFCodFenomeno.getText());
					try {
						fenBean = (IFenomenoDAO) InitialContext.doLookup("GEONat/FenomenosDAO!com.DAO.FenomenoDAO11");
						boolean enu = fenBean.existeID(id);
						if (!enu) {
							JOptionPane.showMessageDialog(null,  "No existe el fenomeno en la base de datos");
						} else {
							fen = new Fenomeno();
							fen = fenBean.obtenerUno(id);
							txtFNombre.setVisible(true);
							txtFDescripcion.setVisible(true);
							txtFTelEmergencia.setVisible(true);
							txtFNombre.setText(fen.getNombre());
							txtFDescripcion.setText(fen.getDescripcion());
							txtFTelEmergencia.setText(fen.getTelefono());
						}
					} catch (NamingException e) {
						e.printStackTrace();
					} catch (ServiciosException e) {
						e.printStackTrace();
					}
				}	
			}
		});
		
		btnBuscarFenomeno.setBounds(266, 7, 158, 23);
		getFrmModificacionFenomeno().getContentPane().add(btnBuscarFenomeno);
		
		
		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean errores = false;
				String strerror = null;
				IFenomenoDAO fenBean2 = null;
				Fenomeno fen2 = new Fenomeno();
				try {
					fenBean2 = (IFenomenoDAO) 
							InitialContext.doLookup("GEONat/FenomenosDAO!com.DAO.FenomenoDAO11");
				} catch (NamingException e1) {
					e1.printStackTrace();
				} 
				fen2.setNombre(txtFNombre.getText().toUpperCase());
				fen2.setId_Fenomeno(Integer.parseInt(txtFCodFenomeno.getText()));
				
				if(txtFDescripcion.getText()== null) {
					errores = true;
					strerror = "Debe ingresar una descripcion";
				} else {
					fen2.setDescripcion(txtFDescripcion.getText().toString());
				}
				
				if (txtFTelEmergencia.getText() == null) {
					errores = true;
					strerror = "Debe ingresar un telefono";
				} else if (txtFTelEmergencia.getText().matches("[0-9]*")) {
					fen2.setTelefono(txtFTelEmergencia.getText().toString());
				} else {
					errores = true;
					strerror = "Debe ingresar un numero de telefono valido";
				}
				
				if(errores == false) {
					try {
						fenBean2.update(fen2);
						JOptionPane.showMessageDialog(null, "Fenomeno Actualizado");
					} catch (ServiciosException err) {
						
						err.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null,  strerror);
				}
				txtFNombre.setText(null);
				txtFDescripcion.setText(null);
				txtFTelEmergencia.setText(null);
			}
		});
		btnGuardarCambios.setBounds(57, 211, 126, 23);
		getFrmModificacionFenomeno().getContentPane().add(btnGuardarCambios);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrmModificacionFenomeno().hide();
			}
		});
		btnCancelar.setBounds(305, 211, 89, 23);
		getFrmModificacionFenomeno().getContentPane().add(btnCancelar);
		
		
		
		
	}

	public JFrame getFrmModificacionFenomeno() {
		return frmModificacionFenomeno;
	}

	public void setFrmModificacionFenomeno(JFrame frmModificacionFenomeno) {
		this.frmModificacionFenomeno = frmModificacionFenomeno;
	}
}
