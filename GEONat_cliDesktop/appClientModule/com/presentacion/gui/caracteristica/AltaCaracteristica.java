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
import java.awt.Font;

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
		frmG.setTitle("GEONat - Registro de una caracter\u00EDstica");
		frmG.setBounds(100, 100, 1200, 800);
		frmG.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmG.getContentPane().setLayout(null);
		frmG.setResizable(false);
		
		JLabel lblNombre = new JLabel("Nombre de la caracter\u00EDstica");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(150, 100, 250, 40);
		frmG.getContentPane().add(lblNombre);
		
		txtFNombre = new JTextField();
		txtFNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFNombre.setBounds(600, 105, 350, 30);
		frmG.getContentPane().add(txtFNombre);
		txtFNombre.setColumns(10);
		
		JLabel lblEtiqueta = new JLabel("Etiqueta de presentaci\u00F3n");
		lblEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEtiqueta.setBounds(150, 240, 250, 40);
		frmG.getContentPane().add(lblEtiqueta);
		
		txtFEtiqueta = new JTextField();
		txtFEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFEtiqueta.setBounds(600, 245, 350, 30);
		frmG.getContentPane().add(txtFEtiqueta);
		txtFEtiqueta.setColumns(10);
		
		JLabel lblTipoDeDato = new JLabel("Tipo de Dato");
		lblTipoDeDato.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTipoDeDato.setBounds(150, 380, 250, 40);
		frmG.getContentPane().add(lblTipoDeDato);
		
		JLabel lblFenomenoAsociado = new JLabel("Fen\u00F3meno Asociado");
		lblFenomenoAsociado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFenomenoAsociado.setBounds(150, 520, 250, 40);
		frmG.getContentPane().add(lblFenomenoAsociado);
		
		
		// Combo Fenomeno Asociado		
		JComboBox comboBFenomAsoc = new JComboBox();
		comboBFenomAsoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBFenomAsoc.setBounds(600, 525, 350, 30);
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
		comboBFenomAsoc.addItem("No Tiene");
		
		comboBFenomAsoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventocombo) {
				comboBFenomAsoc.getSelectedIndex();
				
			}
		});	
		
		
		// Combo TipoDato
		JComboBox comboBTipoDato = new JComboBox();
		comboBTipoDato.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBTipoDato.setBounds(600, 385, 350, 30);
		frmG.getContentPane().add(comboBTipoDato);
		comboBTipoDato.removeAllItems();
		TipoDato[] tipoDatoList = TipoDato.values();
		for (TipoDato tipoDato : tipoDatoList) {
			comboBTipoDato.addItem(tipoDato);
		}
		comboBTipoDato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventocombo) {
				comboBTipoDato.getSelectedIndex();
				
			}
		});
		
		// Botón CANCELAR
		JButton btnCancelarAlta = new JButton("Cancelar Alta");
		btnCancelarAlta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelarAlta.setBounds(920, 660, 180, 40);
		frmG.getContentPane().add(btnCancelarAlta);
		btnCancelarAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				frmG.hide();
			}
			
		});
		
		// Botón GUARDAR
		JButton btnDarDeAlta = new JButton("Confirmar alta");
		btnDarDeAlta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDarDeAlta.setBounds(100, 660, 180, 40);
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
							throw new ServiciosException("Error al obtener el fenómeno.");
						}
					
					caract.setFenomeno(f);;
											
					caract.setTipoDato(TipoDato.valueOf(comboBTipoDato.getSelectedItem().toString()));
					
					serviciosCaracteristicas.create(caract);
					JOptionPane.showMessageDialog(null,  "Caracteristica Creada");
					
				}catch ( ServiciosException e) {
					
					System.out.println("Error al crear característica.");	
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,  "Error al crear característica. " + e.getMessage());
						
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
