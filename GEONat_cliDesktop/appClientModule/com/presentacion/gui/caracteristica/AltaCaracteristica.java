package com.presentacion.gui.caracteristica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.DAO.concrete.FenomenosDAO;
import com.DAO.interfaces.ICaracteristicaDAO;
import com.DAO.interfaces.IFenomenoDAO;
import com.DAO.interfaces.IUsuarioDAO;
import com.entities.Caracteristica;
import com.entities.Fenomeno;
import com.entities.TipoDato;
import com.entities.TipoDocumento;
import com.entities.TipoUsuario;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosCaracteristica;
import com.presentacion.servicios.ServiciosFenomeno;
import com.presentacion.servicios.ServiciosUsuario;

import javax.swing.JComboBox;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;

public class AltaCaracteristica {

	ServiciosCaracteristica serviciosCaracteristicas = ServiciosCaracteristica.getInstance();
	ServiciosFenomeno serviciosFenomeno = ServiciosFenomeno.getInstance();
	
	public JFrame frmG;
	private JTextField txtFNombre;
	private JTextField txtFEtiqueta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public AltaCaracteristica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frmG = new JFrame();
		frmG.setTitle("GEONat - Alta de una caracteristica");
		frmG.setBounds(100, 100, 450, 300);
		frmG.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmG.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre de la caracter\u00EDstica");
		lblNombre.setBounds(10, 25, 141, 14);
		frmG.getContentPane().add(lblNombre);
		
		txtFNombre = new JTextField();
		txtFNombre.setBounds(181, 22, 206, 20);
		frmG.getContentPane().add(txtFNombre);
		txtFNombre.setColumns(10);
		
		JLabel lblEtiqueta = new JLabel("Etiqueta de presentaci\u00F3n");
		lblEtiqueta.setBounds(10, 63, 141, 14);
		frmG.getContentPane().add(lblEtiqueta);
		
		txtFEtiqueta = new JTextField();
		txtFEtiqueta.setBounds(181, 60, 206, 34);
		frmG.getContentPane().add(txtFEtiqueta);
		txtFEtiqueta.setColumns(10);
		
		JLabel lblTipoDeDato = new JLabel("Tipo de Dato");
		lblTipoDeDato.setBounds(10, 117, 125, 14);
		frmG.getContentPane().add(lblTipoDeDato);
		
		JLabel lblFenomenoAsociado = new JLabel("Fen\u00F3meno Asociado");
		lblFenomenoAsociado.setBounds(10, 157, 125, 14);
		frmG.getContentPane().add(lblFenomenoAsociado);
		
		
		// Combo Fenomeno Asociado		
		JComboBox comboBFenomAsoc = new JComboBox();
		comboBFenomAsoc.setBounds(181, 154, 206, 20);
		frmG.getContentPane().add(comboBFenomAsoc);
		try {
			List<Fenomeno> fen = cargarfenomenos();
			for (Fenomeno f : fen) {
				comboBFenomAsoc.addItem(f.getNombre());
			}
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comboBFenomAsoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventocombo) {
				comboBFenomAsoc.getSelectedIndex();
				
			}
		});	
		
		
		// Combo TipoDato
		JComboBox comboBTipoDato = new JComboBox();
		comboBTipoDato.setBounds(181, 114, 206, 20);
		frmG.getContentPane().add(comboBTipoDato);
		comboBTipoDato.removeAllItems(); 
		comboBTipoDato.addItem("");
		TipoDato[] tipoDatoList = TipoDato.values();
		for (TipoDato tipoDato : tipoDatoList) {
			comboBTipoDato.addItem(tipoDato);
		}
		comboBTipoDato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventocombo) {
				comboBTipoDato.getSelectedIndex();
				
			}
		});
		
		// Bot�n CANCELAR
		JButton btnCancelarAlta = new JButton("Cancelar Alta");
		btnCancelarAlta.setBounds(10, 210, 104, 23);
		frmG.getContentPane().add(btnCancelarAlta);
		btnCancelarAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				frmG.hide();
			}
			
		});
		
		// Bot�n GUARDAR
		JButton btnDarDeAlta = new JButton("Confirmar alta");
		btnDarDeAlta.setBounds(271, 210, 116, 23);
		frmG.getContentPane().add(btnDarDeAlta);
		
		btnDarDeAlta.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			String strerror = "";
			Boolean errores = false;
			Caracteristica caract = new Caracteristica();
			
			
			// VALIDACIONES de campos	
			if ((txtFEtiqueta.getText().length())>50) {
				errores=true;
				strerror= strerror + " Etiqueta con mas de 50 caracteres. ";
			} else {
				caract.setEtiqPresentacion(txtFEtiqueta.getText());
			}
			if ((txtFNombre.getText().length())>50) {
				errores=true;
				strerror= strerror + " Nombre caracteristica con mas de 50 caracteres. ";
			} else {
				caract.setNombre(txtFNombre.getText());
			}
					
								
			if (errores) {
				JOptionPane.showMessageDialog(null,  strerror);
			} else {
				try {
					
					int idFenomeno =0;
					Fenomeno f = new Fenomeno();
					
					try {
						
						idFenomeno = comboBFenomAsoc.getSelectedIndex();
						
						f = serviciosFenomeno.obtenerUno(idFenomeno+1);
										
						} catch ( ServiciosException e) {
							e.printStackTrace();
							throw new ServiciosException("Error al obtener el fen�meno.");
						}
					
					caract.setFenomeno(f);;
											
					caract.setTipoDato(TipoDato.valueOf(comboBTipoDato.getSelectedItem().toString()));
					
					serviciosCaracteristicas.create(caract);
					JOptionPane.showMessageDialog(null,  "Caracteristica Creada");
					
				}catch ( ServiciosException e) {
					
					System.out.println("Error al crear caracter�stica.");	
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,  "Error al crear caracter�stica. " + e.getMessage());
						
				}
			}
		}
		});
	}
	
	
	
	
	private List<Fenomeno> cargarfenomenos() throws ServiciosException {
		ArrayList<Fenomeno> fenomenos = null;
		try {
			fenomenos = (ArrayList<Fenomeno>) serviciosFenomeno.obtenerTodos(); 
			
			} catch (ServiciosException e) {
				e.printStackTrace();
				}
		
		return fenomenos;
	
	
	}

}
