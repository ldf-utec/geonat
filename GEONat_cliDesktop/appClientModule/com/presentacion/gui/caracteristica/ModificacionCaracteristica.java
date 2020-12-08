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

import javax.swing.JComboBox;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.Color;

public class ModificacionCaracteristica {

	ServiciosCaracteristica serviciosCaracteristicas = ServiciosCaracteristica.getInstance();
	ServiciosFenomeno serviciosFenomeno = ServiciosFenomeno.getInstance();
	
	public JFrame frmModificarCaracteristica;
	private JTextField txtFNombre;
	private JTextField txtFEtiqueta;
	private List<Fenomeno> fenomenos;
	private JComboBox<Integer> comboIDCaracteristica;
	private JComboBox comboBTipoDato;
	private JComboBox comboBFenomAsoc;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public ModificacionCaracteristica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificarCaracteristica = new JFrame();
		frmModificarCaracteristica.setTitle("GEONat - Modificacion de una caracteristica");
		frmModificarCaracteristica.setBounds(100, 100, 621, 307);
		frmModificarCaracteristica.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmModificarCaracteristica.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre de la caracter\u00EDstica");
		lblNombre.setBounds(10, 66, 141, 14);
		frmModificarCaracteristica.getContentPane().add(lblNombre);
		
		txtFNombre = new JTextField();
		txtFNombre.setBounds(181, 63, 206, 20);
		frmModificarCaracteristica.getContentPane().add(txtFNombre);
		txtFNombre.setColumns(10);
		
		JLabel lblEtiqueta = new JLabel("Etiqueta de presentaci\u00F3n");
		lblEtiqueta.setBounds(10, 104, 141, 14);
		frmModificarCaracteristica.getContentPane().add(lblEtiqueta);
		
		txtFEtiqueta = new JTextField();
		txtFEtiqueta.setBounds(181, 101, 206, 34);
		frmModificarCaracteristica.getContentPane().add(txtFEtiqueta);
		txtFEtiqueta.setColumns(10);
		
		JLabel lblTipoDeDato = new JLabel("Tipo de Dato");
		lblTipoDeDato.setBounds(10, 158, 125, 14);
		frmModificarCaracteristica.getContentPane().add(lblTipoDeDato);
		
		JLabel lblFenomenoAsociado = new JLabel("Fen\u00F3meno Asociado");
		lblFenomenoAsociado.setBounds(10, 198, 125, 14);
		frmModificarCaracteristica.getContentPane().add(lblFenomenoAsociado);
		
		
		// Combo Fenomeno Asociado		
		comboBFenomAsoc = new JComboBox();
		comboBFenomAsoc.setBounds(181, 195, 206, 20);
		frmModificarCaracteristica.getContentPane().add(comboBFenomAsoc);
		//Carga de datos inicial
		try {
			fenomenos = cargarfenomenos();
			for (Fenomeno f : fenomenos) {
				comboBFenomAsoc.addItem(f.getNombre().toString());
			}
			comboBFenomAsoc.addItem("No tiene");
		} catch (ServiciosException e1) {

			e1.printStackTrace();
		}
		//Accion		
		comboBFenomAsoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventocombo) {
				comboBFenomAsoc.getSelectedIndex();
				
			}
		});	
				
		// Combo TipoDato
		comboBTipoDato = new JComboBox();
		comboBTipoDato.setBounds(181, 155, 206, 20);
		frmModificarCaracteristica.getContentPane().add(comboBTipoDato);
		comboBTipoDato.removeAllItems(); 
		//comboBTipoDato.addItem("");
		//Carga de datos inicial
		TipoDato[] tipoDatoList = TipoDato.values();
		for (TipoDato tipoDato : tipoDatoList) {
		comboBTipoDato.addItem(tipoDato);
		}
		//Accion
		comboBTipoDato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventocombo) {
				comboBTipoDato.getSelectedIndex();			
			}
		});
		
		//Boton de cancelar
		JButton btnCancelarAlta = new JButton("Cancelar Modificacion");
		btnCancelarAlta.setForeground(Color.RED);
		btnCancelarAlta.setBounds(424, 153, 150, 23);
		frmModificarCaracteristica.getContentPane().add(btnCancelarAlta);
		btnCancelarAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				frmModificarCaracteristica.hide();
			}
			
		});
		
		//Boton de confirmar
		JButton btnConfirmarModificacion = new JButton("Confirmar Modificacion");
		btnConfirmarModificacion.setBounds(424, 192, 150, 23);
		frmModificarCaracteristica.getContentPane().add(btnConfirmarModificacion);
		
		JLabel lblIdCaracteritica = new JLabel("ID Caracteritica");
		lblIdCaracteritica.setBounds(10, 25, 141, 14);
		frmModificarCaracteristica.getContentPane().add(lblIdCaracteritica);
		
		//Combo ID Caracteristica
		comboIDCaracteristica = new JComboBox();
		
		//Para mostrar los datos por primera vez		
		try {
			cargarComboBox();
		} catch (ServiciosException e1) {
			e1.printStackTrace();
		}
		//Accion
		comboIDCaracteristica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatos((Integer)comboIDCaracteristica.getSelectedItem());
			}
		});
		comboIDCaracteristica.setBounds(181, 22, 58, 20);
		frmModificarCaracteristica.getContentPane().add(comboIDCaracteristica);
		
		//Accion boton confirmae
		btnConfirmarModificacion.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent evento) {
			String strerror = "";
			Boolean errores = false;
			Caracteristica caract = new Caracteristica();
			
			caract.setId_Caracteristica((Integer)comboIDCaracteristica.getSelectedItem());
			
			if ((txtFNombre.getText().length())>50) {
				errores=true;
				strerror= strerror + "Nombre caracteristica con mas de 50 caracteres. ";
			} else {
				caract.setNombre(txtFNombre.getText());
			}
			if ((txtFEtiqueta.getText().length())>50) {
				errores=true;
				strerror= strerror + "Etiqueta con mas de 50 caracteres. ";
			} else {
				caract.setEtiqPresentacion(txtFEtiqueta.getText());
			}
			
		    TipoDato td=(TipoDato.valueOf(comboBTipoDato.getSelectedItem().toString()) );
			caract.setTipoDato(td);
			
			if (!(comboBFenomAsoc.getSelectedItem().toString().equals("No tiene"))) {
				caract.setFenomeno(obtenerFenom(comboBFenomAsoc.getSelectedItem().toString()));
			} else {
				caract.setFenomeno(null);
			}
	
			if (errores) {
				JOptionPane.showMessageDialog(null,  strerror);
			} else {
				try {
					serviciosCaracteristicas.update(caract);
					JOptionPane.showMessageDialog(null,  "Caracteristica Actualizada");
					cargarDatos(1);
				} catch ( ServiciosException e) {
						e.printStackTrace();
				}	
				
			}
		}
		});
 
	}
	
	//metodo para cargar combobox de id caracteristica
	private void cargarComboBox() throws ServiciosException {
		try {
			List<Caracteristica> carac =  serviciosCaracteristicas.obtenerTodos();
			if (!carac.equals(null)) {
				for (Caracteristica c : carac) {
					comboIDCaracteristica.addItem((Integer)c.getId_Caracteristica());
				}
			} else {
				JOptionPane.showMessageDialog(null,  "Aun no se han cargado caracteristicas");
				frmModificarCaracteristica.hide();
			}
			
			
		}catch (Exception e) {
			System.out.println("Error al cargar datos en el comboBox ID Caracteristica. ");
			e.printStackTrace();
		}
	}
	
	//metodo para cargar datos cuando se selecciona un ID
	private void cargarDatos (int filtro) {
		try {
			Caracteristica carac = new Caracteristica();
			carac = serviciosCaracteristicas.obtenerUno(filtro);
			if (!carac.equals(null)) {
				comboIDCaracteristica.setSelectedItem(filtro);
				txtFNombre.setText(carac.getNombre().toString());
				txtFEtiqueta.setText(carac.getEtiqPresentacion());		
			}
			
		}catch (Exception e) {

			System.out.println("Error al cargar datos en el comboBox Fenomeno. ");
			e.printStackTrace();

		}
	}
	
	//metodo para cargar fenomenos en el combo box de fenomenos
	private List<Fenomeno> cargarfenomenos() throws ServiciosException {
		List<Fenomeno> fenomenos = null;
		try {
			fenomenos =  (List<Fenomeno>) serviciosFenomeno.obtenerTodos(); 	
		} catch (ServiciosException e) {
			e.printStackTrace();
		}	
		return fenomenos;
	}
	
	//metodo para obtener un objeto fenomeno a partir de su nombre
	private Fenomeno obtenerFenom (String filtro) {
		List<Fenomeno> fen;	
		Fenomeno fenom = null;
		try {
			fen =  serviciosFenomeno.obtenerTodosPorNombre(filtro);
			if (!fen.isEmpty()) {	
				fenom = new Fenomeno();
				fenom.setId_Fenomeno(fen.get(0).getId_Fenomeno());
				fenom.setDescripcion(fen.get(0).getDescripcion());
				fenom.setNombre(fen.get(0).getNombre());
				fenom.setTelefono(fen.get(0).getTelefono());
			}
			
		}catch (Exception e) {
			System.out.println("Error al obtener Fenomenos");
			e.printStackTrace();
		}
		
		return fenom;
	}
	
	}

