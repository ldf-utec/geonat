package com.presentacion.gui.caracteristica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.entities.Caracteristica;
import com.entities.Fenomeno;
import com.entities.TipoDato;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosCaracteristica;
import com.presentacion.servicios.ServiciosFenomeno;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ModificacionCaracteristica {

	ServiciosCaracteristica serviciosCaracteristicas = ServiciosCaracteristica.getInstance();
	ServiciosFenomeno serviciosFenomeno = ServiciosFenomeno.getInstance();

	public JFrame frmModificarCaracteristica;
	private JTextField txtFNombre;
	private JTextField txtFEtiqueta;
	private JComboBox comboIDCaracteristica;
	private JComboBox comboBTipoDato;
	private JComboBox comboBFenomAsoc;
	private int limiteNombre = 50;
	private int limiteEtiqueta = 50;
	private String caracteristicaNombre;


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
		frmModificarCaracteristica.setTitle("GEONat - Modificaci\u00F3n de una caracter\u00EDstica");
		frmModificarCaracteristica.setBounds(100, 100, 1200, 800);
		frmModificarCaracteristica.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmModificarCaracteristica.getContentPane().setLayout(null);
		frmModificarCaracteristica.setResizable(false);

		JLabel lblNombre = new JLabel("Nombre de la caracter\u00EDstica:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(150, 220, 250, 40);
		frmModificarCaracteristica.getContentPane().add(lblNombre);

		txtFNombre = new JTextField();
		txtFNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtFNombre.getText().length() > limiteNombre) {
					JOptionPane.showMessageDialog(null,"Debe ingresar menos de "+limiteNombre+" caracteres", null, JOptionPane.WARNING_MESSAGE);
					e.consume();
				}
			}
		});
		txtFNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFNombre.setBounds(500, 225, 350, 30);
		frmModificarCaracteristica.getContentPane().add(txtFNombre);
		txtFNombre.setColumns(10);

		JLabel lblEtiqueta = new JLabel("Etiqueta de presentaci\u00F3n:");
		lblEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEtiqueta.setBounds(150, 340, 250, 40);
		frmModificarCaracteristica.getContentPane().add(lblEtiqueta);

		txtFEtiqueta = new JTextField();
		txtFEtiqueta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtFEtiqueta.getText().length() > limiteEtiqueta) {
					JOptionPane.showMessageDialog(null,"Debe ingresar menos de "+limiteEtiqueta+" caracteres", null, JOptionPane.WARNING_MESSAGE);
					e.consume();
				}
			}
		});
		txtFEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFEtiqueta.setBounds(500, 345, 350, 30);
		frmModificarCaracteristica.getContentPane().add(txtFEtiqueta);
		txtFEtiqueta.setColumns(10);

		JLabel lblTipoDeDato = new JLabel("Tipo de Dato:");
		lblTipoDeDato.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTipoDeDato.setBounds(150, 460, 250, 40);
		frmModificarCaracteristica.getContentPane().add(lblTipoDeDato);

		JLabel lblFenomenoAsociado = new JLabel("Fen\u00F3meno Asociado:");
		lblFenomenoAsociado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFenomenoAsociado.setBounds(150, 580, 250, 40);
		frmModificarCaracteristica.getContentPane().add(lblFenomenoAsociado);

		JLabel lblErrorFen = new JLabel("A\u00FAn no se han ingresado fen\u00F3menos");
		lblErrorFen.setForeground(Color.DARK_GRAY);
		lblErrorFen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblErrorFen.setBounds(850, 585, 262, 30);
		lblErrorFen.setVisible(false);
		frmModificarCaracteristica.getContentPane().add(lblErrorFen);

		// Combo Fenomeno Asociado		
		comboBFenomAsoc = new JComboBox();
		comboBFenomAsoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBFenomAsoc.setBounds(500, 585, 350, 30);
		frmModificarCaracteristica.getContentPane().add(comboBFenomAsoc);
		//Carga inicial de datos de fenomeno
		try {
			List<Fenomeno> fen = cargarfenomenos();
			if (fen == null) {
				lblErrorFen.setVisible(true);
			}
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBFenomAsoc.addItem("No Tiene");
		//Accion		
		comboBFenomAsoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventocombo) {
				comboBFenomAsoc.getSelectedIndex();

			}
		});	

		// Combo TipoDato
		comboBTipoDato = new JComboBox();
		comboBTipoDato.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBTipoDato.setBounds(500, 465, 350, 30);
		frmModificarCaracteristica.getContentPane().add(comboBTipoDato);
		comboBTipoDato.removeAllItems(); 
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
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBounds(920, 705, 180, 40);
		frmModificarCaracteristica.getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				frmModificarCaracteristica.hide();
			}

		});

		//Boton de confirmar
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnConfirmar.setBounds(100, 705, 180, 40);
		frmModificarCaracteristica.getContentPane().add(btnConfirmar);

		JLabel lblIdCaracteritica = new JLabel("ID Caracter\u00EDstica");
		lblIdCaracteritica.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIdCaracteritica.setBounds(150, 100, 250, 40);
		frmModificarCaracteristica.getContentPane().add(lblIdCaracteritica);

		//Combo ID Caracteristica
		comboIDCaracteristica = new JComboBox();
		comboIDCaracteristica.setFont(new Font("Tahoma", Font.PLAIN, 18));

		//Para mostrar los datos en el combo por primera vez		
		try {
			cargarComboBox();
		} catch (ServiciosException e1) {
			e1.printStackTrace();
		}
		
		//Accion combo ID
		comboIDCaracteristica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = comboIDCaracteristica.getSelectedItem().toString();
				String[] parts = item.split(" - ");
				Integer ID = Integer.parseInt(parts[0]); 
				cargarDatos(ID);
			}
		});
		comboIDCaracteristica.setBounds(500, 105, 350, 30);
		frmModificarCaracteristica.getContentPane().add(comboIDCaracteristica);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1184, 59);
		frmModificarCaracteristica.getContentPane().add(panel);

		JLabel lblModificacinDeCaractersticas = new JLabel("Modificaci\u00F3n de caracter\u00EDsticas");
		lblModificacinDeCaractersticas.setHorizontalAlignment(SwingConstants.LEFT);
		lblModificacinDeCaractersticas.setForeground(Color.GRAY);
		lblModificacinDeCaractersticas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblModificacinDeCaractersticas.setBounds(new Rectangle(10, 10, 10, 10));
		lblModificacinDeCaractersticas.setBounds(14, 28, 1174, 25);
		panel.add(lblModificacinDeCaractersticas);

		JLabel lblErrorNombre = new JLabel("Debe ingresar un nombre");
		lblErrorNombre.setForeground(Color.RED);
		lblErrorNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblErrorNombre.setBounds(850, 225, 250, 30);
		lblErrorNombre.setVisible(false);
		frmModificarCaracteristica.getContentPane().add(lblErrorNombre);

		JLabel lblErrorEtiqueta = new JLabel("Debe ingresar una etiqueta de presentaci\u00F3n");
		lblErrorEtiqueta.setForeground(Color.RED);
		lblErrorEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblErrorEtiqueta.setBounds(850, 345, 338, 30);
		lblErrorEtiqueta.setVisible(false);
		frmModificarCaracteristica.getContentPane().add(lblErrorEtiqueta);

		//Accion boton confirmar
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (txtFNombre.getText().isEmpty()) {
					lblErrorNombre.setVisible(true);
				} else if (txtFEtiqueta.getText().isEmpty()) { 
					lblErrorEtiqueta.setVisible(true);
				}else {
					int select = JOptionPane.showConfirmDialog(null,  "¿Confirma la modificaci\u00F3n?", null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (select == JOptionPane.YES_OPTION) {
						try {
							modificarCaracteristica(txtFNombre.getText(), 
									txtFEtiqueta.getText(), 
									TipoDato.valueOf(comboBTipoDato.getSelectedItem().toString ()), 
									comboBFenomAsoc.getSelectedItem().toString());
						} catch (ServiciosException e) {
							e.printStackTrace();
						}
						lblErrorNombre.setVisible(false);
						lblErrorEtiqueta.setVisible(false);
						frmModificarCaracteristica.dispose();
					}
				}
			}
		});

	}

	//metodo para cargar combobox de id caracteristica
	private void cargarComboBox() throws ServiciosException {
		try {
			List<Caracteristica> carac =  serviciosCaracteristicas.obtenerTodos();
			if (carac != null) {
				for (Caracteristica c : carac) {
					comboIDCaracteristica.addItem(c.getId_Caracteristica()+" - "+c.getNombre());
				}
			} else {
				JOptionPane.showMessageDialog(null,  "A\u00FAn no se han cargado caracter\u00EDsticas", null, JOptionPane.INFORMATION_MESSAGE);
				frmModificarCaracteristica.dispose();
			}			
		}catch (Exception e) {
			System.out.println("Error al cargar datos en el comboBox ID Caracter\u00EDstica.");
			e.printStackTrace();
		}
	}


	//Metodo que carga los nombres de los fenomenos en el combo fenomenos asociados
	private List<Fenomeno> cargarfenomenos() throws ServiciosException {
		List<Fenomeno> fenomenos = null;
		try {
			fenomenos = serviciosFenomeno.obtenerTodos();
			if(fenomenos != null) {
				for (Fenomeno f : fenomenos) {
					comboBFenomAsoc.addItem(f.getNombre());
				}
			} else {
				fenomenos = null;
			}
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		return fenomenos;
	}

	//Metodo para cargar datos cuando se selecciona un ID de caracteristica
	private void cargarDatos (int filtro) {
		try {
			Caracteristica carac = new Caracteristica();
			carac = serviciosCaracteristicas.obtenerUno(filtro);
			if (!carac.equals(null)) {
				//comboIDCaracteristica.setSelectedItem(filtro);
				txtFNombre.setText(carac.getNombre().toString());
				caracteristicaNombre = carac.getNombre().toString();
				txtFEtiqueta.setText(carac.getEtiqPresentacion());
				comboBTipoDato.setSelectedItem(carac.getTipoDato());
				if (carac.getFenomeno() == null) {
					comboBFenomAsoc.setSelectedItem("No Tiene");
				} else {
					comboBFenomAsoc.setSelectedItem(carac.getFenomeno().getNombre());	
				}
			}		
		}catch (Exception e) {

			System.out.println("Error al cargar datos en el comboBox Fen\u00F3meno. ");
			e.printStackTrace();

		}
	}

	//Metodo para obtener un objeto fenomeno a partir de su nombre
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
			System.out.println("Error al obtener Fen\u00F3meno");
			e.printStackTrace();
		}	
		return fenom;
	}

	//metodo que se encarga del registro de caracteristica
	private void modificarCaracteristica(String nombre, String etiqueta, TipoDato tipoDato, String fenomeno) throws ServiciosException {
		String strerror = "";
		Boolean errores = false;
		Caracteristica caract = new Caracteristica();

		// VALIDACIONES de campos
		//Campo Nombre
		if (!(nombre.length()>50) && !nombre.startsWith(" ")) {
			if (caracteristicaNombre.equals(nombre)) {
				caract.setNombre(nombre);
			} else {
				if (serviciosCaracteristicas.existeNombreCaracteristica(nombre.toUpperCase())) {
					strerror= strerror + "- El nombre de la caracter\u00EDstica ya existe.\n";
					errores = true;
				}else {
					caract.setNombre(nombre);
				}
			}

		} else {
			errores=true;
			strerror= strerror + "- Nombre caracter\u00EDstica no v\u00E1lido.\n";
		}

		//Campo Etiqueta
		if (!(etiqueta.length()>50) && !etiqueta.startsWith(" ")) {
			caract.setEtiqPresentacion(etiqueta);
		} else {
			errores=true;
			strerror= strerror + "- Etiqueta de presentaci\u00F3n no v\u00E1lida.\n";
		}

		//Campo fenomeno
		Fenomeno f = new Fenomeno();
		if (fenomeno.equals("No tiene")) {
			f = null;
			caract.setFenomeno(f);
		} else {
			f = obtenerFenom (fenomeno);
			caract.setFenomeno(f);
		}

		//Campo tipo dato
		caract.setTipoDato(tipoDato);

		//El valor de ID no necesita validacion
		String item = comboIDCaracteristica.getSelectedItem().toString();
		String[] parts = item.split(" - ");
		Integer ID = Integer.parseInt(parts[0]); 
		caract.setId_Caracteristica(ID);

		//Se actualiza la caracteristica modificada
		if (errores) {
			JOptionPane.showMessageDialog(null,  strerror, null, JOptionPane.ERROR_MESSAGE);
		} else {
			try {	
				serviciosCaracteristicas.update(caract);
				JOptionPane.showMessageDialog(null,  "Caracter\u00EDstica actualizada.");	
			}catch ( ServiciosException e) {	
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,  "Error al modificar caracter\u00EDstica. " + e.getMessage());				
			}
		}
	}

}

