package com.presentacion.gui.fenomenos;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.DAO.interfaces.IFenomenoDAO;
import com.entities.Fenomeno;
import com.entities.TipoDocumento;
import com.entities.TipoUsuario;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosGUI;
import com.presentacion.servicios.ServiciosFenomeno;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.SwingConstants;

public class AltaFenomeno {

	public JFrame frmAltaFenomenos;
	private JTextField txtFNombre;
	private JTextArea txtADescripcion;
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
		
		ServiciosFenomeno fenomeno = ServiciosFenomeno.getInstance();
	
		
		frmAltaFenomenos = new JFrame();
		frmAltaFenomenos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmAltaFenomenos.setResizable(false);
		frmAltaFenomenos.setTitle("GEONat - Registro de Fen\u00F3menos");
		frmAltaFenomenos.setResizable(false);
		frmAltaFenomenos.setBounds(10, 10, 1200, 800);
		frmAltaFenomenos.setSize(1200, 800);
		frmAltaFenomenos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAltaFenomenos.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre del fen\u00F3meno:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(40, 110, 250, 40);
		frmAltaFenomenos.getContentPane().add(lblNombre);
		
		txtFNombre = new JTextField();
		txtFNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtFNombre.getText().length() > limiteNombre) {
					JOptionPane.showMessageDialog(null,"Debe ingresar menos de "+limiteNombre+" caracteres");
					e.consume();
			}
			}
			});
		txtFNombre.setBounds(310, 110, 375, 40);
		frmAltaFenomenos.getContentPane().add(txtFNombre);
		txtFNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescripcion.setBounds(40, 210, 250, 40);
		frmAltaFenomenos.getContentPane().add(lblDescripcion);
		
		txtADescripcion = new JTextArea();
		txtADescripcion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtADescripcion.setLineWrap(true);
		txtADescripcion.setAutoscrolls(true);
		txtADescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtADescripcion.getText().length() > limiteDescripcion) {
					JOptionPane.showMessageDialog(null,"Debe ingresar menos de "+limiteDescripcion+" caracteres");
				e.consume();
				}
			}
		});
		txtADescripcion.setBounds(310, 210, 375, 165);
		frmAltaFenomenos.getContentPane().add(txtADescripcion);
		txtADescripcion.setColumns(10);
		
		JLabel lblTelEmergencia = new JLabel("Tel\u00E9fonos de Emergencia:");
		lblTelEmergencia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTelEmergencia.setBounds(40, 398, 250, 40);
		frmAltaFenomenos.getContentPane().add(lblTelEmergencia);
		
		txtFTelEmergencia = new JTextField();
		txtFTelEmergencia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFTelEmergencia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtFTelEmergencia.getText().length() > limiteTel) {
					JOptionPane.showMessageDialog(null,"Debe ingresar menos de "+limiteTel+" números");
				e.consume();
			}
			}
		});
		txtFTelEmergencia.setBounds(310, 408, 375, 40);
		frmAltaFenomenos.getContentPane().add(txtFTelEmergencia);
		txtFTelEmergencia.setColumns(10);
		
		
		
		JButton btnAltaDeFenomeno = new JButton("Confirmar");
		btnAltaDeFenomeno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAltaDeFenomeno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strerror = "";
				Fenomeno f = new Fenomeno();
				
				if (!(txtFNombre.getText().isEmpty()) && (txtFNombre.getText().length() < limiteNombre)) {
					f.setNombre(txtFNombre.getText().toLowerCase().toString());	
					
					try {
						boolean existeFenomeno = fenomeno.existeNombreFenomeno(f);

						if (existeFenomeno) {
							errores = true;
							strerror = "El fenomeno ya existe. ";
						}
					} catch (ServiciosException e1) {
						e1.printStackTrace();
					}
				} else {
					errores = true;
					strerror = "Debe ingresar un nombre valido";
				}
							
				if(!(txtADescripcion.getText().isEmpty()) && (txtADescripcion.getText().length()<limiteDescripcion) ) {
					f.setDescripcion(txtADescripcion.getText().toString());						
				} else {
					errores = true;
					strerror = "Debe ingresar una descripcion de menos de 200 caracteres";
				}
						
				if (!(txtFTelEmergencia.getText().isEmpty()) && (txtFTelEmergencia.getText().length() < limiteTel) && (txtFTelEmergencia.getText().toString().matches("[0-9]*"))) {
					f.setTelefono(txtFTelEmergencia.getText().toString());				
				} else {
					errores=true;
					strerror = "Debe ingresar un numero de telefono valido";
				}
				
				
				if(errores == false) {
					try {
						fenomeno.create(f);
						JOptionPane.showMessageDialog(null, "Fenomeno Creado");
						
					} catch (ServiciosException err) {
						
						err.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null,  strerror);
				}
				limpiarDatos();
				
				
			}
		});
		btnAltaDeFenomeno.setBounds(550, 700, 250, 40);
		frmAltaFenomenos.getContentPane().add(btnAltaDeFenomeno);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAltaFenomenos.hide();
			}
		});
		btnCancelar.setBounds(900, 700, 250, 40);
		frmAltaFenomenos.getContentPane().add(btnCancelar);
		
		JPanel banner = new JPanel();
		banner.setLayout(null);
		banner.setBackground(Color.WHITE);
		banner.setBounds(0, 0, 1195, 60);
		frmAltaFenomenos.getContentPane().add(banner);
		
		JLabel lblModificacinDeFenmenos = new JLabel("Registro de Fen\u00F3menos");
		lblModificacinDeFenmenos.setHorizontalAlignment(SwingConstants.LEFT);
		lblModificacinDeFenmenos.setForeground(Color.GRAY);
		lblModificacinDeFenmenos.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblModificacinDeFenmenos.setBounds(new Rectangle(10, 10, 10, 10));
		lblModificacinDeFenmenos.setBounds(14, 28, 1174, 25);
		banner.add(lblModificacinDeFenmenos);
		
		JLabel label = new JLabel("");
		label.setIconTextGap(0);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(1099, 0, 69, 53);
		banner.add(label);
		
		
		
	}
	
	public void limpiarDatos() {
		txtFNombre.setText(null);
		txtADescripcion.setText(null);
		txtFTelEmergencia.setText(null);
	}
}
