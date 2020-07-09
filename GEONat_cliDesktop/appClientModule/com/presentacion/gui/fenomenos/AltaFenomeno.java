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
import javax.swing.JFormattedTextField;

public class AltaFenomeno {

	public JFrame frmAltaFenomenos;
	private JTextField txtFNombre;
	private JTextField txtFDescripcion;
	private JTextField txtFTelEmergencia;
	private int limiteNombre = 50;
	private int limiteDescripcion = 200;
	private int limiteTel = 20;
	private Boolean errores = false;
	private String strerror = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public AltaFenomeno() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAltaFenomenos = new JFrame();
		frmAltaFenomenos.setTitle("GEONat - Registro de Fen\u00F3menos");
		frmAltaFenomenos.setResizable(false);
		frmAltaFenomenos.setBounds(100, 100, 445, 243);
		frmAltaFenomenos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmAltaFenomenos.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre del fen\u00F3meno");
		lblNombre.setBounds(10, 22, 135, 14);
		frmAltaFenomenos.getContentPane().add(lblNombre);
		
		txtFNombre = new JTextField();
		txtFNombre.setBounds(170, 19, 229, 20);
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
		frmAltaFenomenos.getContentPane().add(txtFNombre);
		txtFNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setBounds(10, 71, 111, 14);
		frmAltaFenomenos.getContentPane().add(lblDescripcion);
		
		txtFDescripcion = new JTextField();
		txtFDescripcion.setBounds(170, 56, 229, 45);
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
		frmAltaFenomenos.getContentPane().add(txtFDescripcion);
		txtFDescripcion.setColumns(10);
		
		JLabel lblTelEmergencia = new JLabel("Tel\u00E9fonos de Emergencia");
		lblTelEmergencia.setBounds(10, 130, 143, 14);
		frmAltaFenomenos.getContentPane().add(lblTelEmergencia);
		
		txtFTelEmergencia = new JTextField();
		txtFTelEmergencia.setBounds(170, 127, 170, 20);
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
		frmAltaFenomenos.getContentPane().add(txtFTelEmergencia);
		txtFTelEmergencia.setColumns(10);
		
		
		JButton btnAltaDeFenomeno = new JButton("Alta de fen\u00F3meno");
		btnAltaDeFenomeno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean errores = false;
				IFenomenoDAO fenBean = null;
				Fenomeno fen = new Fenomeno();
				fen.setNombre(txtFNombre.getText().toUpperCase());
				try {
					fenBean = (IFenomenoDAO) 
							InitialContext.doLookup("GEONat/FenomenosDAO!com.DAO.FenomenoDAO11");
					boolean enu = fenBean.existeNombreFenomeno(fen);
					
					if (enu) {
						errores = true;
						JOptionPane.showMessageDialog(null, "El nombre de fenomeno ingresado, ya existe en la base de datos. ");
					}
				} catch (NamingException e1) {
					e1.printStackTrace();
				} catch (ServiciosException e2) {
					e2.printStackTrace();
				}
				if(txtFDescripcion.getText()== null) {
					errores = true;
					strerror = "Debe ingresar una descripcion";
				} else {
					fen.setDescripcion(txtFDescripcion.getText().toString());
				}
				
				if (txtFTelEmergencia.getText() == null) {
					errores = true;
					strerror = "Debe ingresar un telefono";
				} else if (txtFTelEmergencia.getText().matches("[0-9]*") ) {
					fen.setTelefono(txtFTelEmergencia.getText().toString());
				} else {
					errores=true;
					strerror = "Debe ingresar un numero de telefono valido";
				}
				
				if(errores == false) {
					try {
						fenBean.create(fen);
						JOptionPane.showMessageDialog(null, "Fenomeno Creado");
						
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
		btnAltaDeFenomeno.setBounds(170, 175, 143, 23);
		frmAltaFenomenos.getContentPane().add(btnAltaDeFenomeno);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAltaFenomenos.hide();
			}
		});
		btnCancelar.setBounds(335, 175, 89, 23);
		frmAltaFenomenos.getContentPane().add(btnCancelar);
		
		
		
	}
}
