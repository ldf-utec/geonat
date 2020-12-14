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
import com.entities.Caracteristica;
import com.entities.Fenomeno;
import com.entities.TipoDato;
import com.entities.TipoDocumento;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosCaracteristica;
import com.presentacion.servicios.ServiciosFenomeno;

import javax.swing.JComboBox;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AltaCaracteristica {

	ServiciosCaracteristica serviciosCaracteristicas = ServiciosCaracteristica.getInstance();
	ServiciosFenomeno serviciosFenomeno = ServiciosFenomeno.getInstance();
	
	public JFrame frmG;
	private JTextField txtFNombre;
	private JTextField txtFEtiqueta;
	private int limiteNombre = 50;
	private int limiteEtiqueta = 50;
	private JComboBox comboBFenomAsoc;
	private JComboBox comboBTipoDato;

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
		frmG.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmG.getContentPane().setLayout(null);
		frmG.setResizable(false);
		
		JLabel lblNombre = new JLabel("Nombre de la caracter\u00EDstica");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(150, 100, 250, 40);
		frmG.getContentPane().add(lblNombre);
		
		txtFNombre = new JTextField();
		txtFNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtFNombre.getText().length() > limiteNombre) {
					JOptionPane.showMessageDialog(null,"Debe ingresar menos de "+limiteNombre+" caracteres", null, JOptionPane.WARNING_MESSAGE);
					e.consume();
				}
			}
		});
		txtFNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFNombre.setBounds(500, 105, 350, 30);
		frmG.getContentPane().add(txtFNombre);
		txtFNombre.setColumns(10);
		
		JLabel lblErrorNombre = new JLabel("Debe ingresar un nombre");
		lblErrorNombre.setForeground(Color.RED);
		lblErrorNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblErrorNombre.setBounds(850, 105, 250, 30);
		lblErrorNombre.setVisible(false);
		frmG.getContentPane().add(lblErrorNombre);
		
		JLabel lblEtiqueta = new JLabel("Etiqueta de presentaci\u00F3n");
		lblEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEtiqueta.setBounds(150, 240, 250, 40);
		frmG.getContentPane().add(lblEtiqueta);
		
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
		txtFEtiqueta.setBounds(500, 245, 350, 30);
		frmG.getContentPane().add(txtFEtiqueta);
		txtFEtiqueta.setColumns(10);
		
		JLabel lblErrorEtiqueta = new JLabel("Debe ingresar una etiqueta de presentaci\u00F3n");
		lblErrorEtiqueta.setForeground(Color.RED);
		lblErrorEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblErrorEtiqueta.setBounds(850, 245, 338, 30);
		lblErrorEtiqueta.setVisible(false);
		frmG.getContentPane().add(lblErrorEtiqueta);
		
		JLabel lblTipoDeDato = new JLabel("Tipo de Dato");
		lblTipoDeDato.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTipoDeDato.setBounds(150, 380, 250, 40);
		frmG.getContentPane().add(lblTipoDeDato);
		
		
		JLabel lblFenomenoAsociado = new JLabel("Fen\u00F3meno Asociado");
		lblFenomenoAsociado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFenomenoAsociado.setBounds(150, 520, 250, 40);
		frmG.getContentPane().add(lblFenomenoAsociado);
		
		JLabel lblErrorFen = new JLabel("A\u00FAn no se han creado fen\u00F3menos");
		lblErrorFen.setForeground(Color.DARK_GRAY);
		lblErrorFen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblErrorFen.setBounds(850, 525, 262, 30);
		lblErrorFen.setVisible(false);
		frmG.getContentPane().add(lblErrorFen);
		
		// Combo Fenomeno Asociado		
		comboBFenomAsoc = new JComboBox();
		comboBFenomAsoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBFenomAsoc.setBounds(500, 525, 350, 30);
		frmG.getContentPane().add(comboBFenomAsoc);
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
		
		comboBFenomAsoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventocombo) {
				comboBFenomAsoc.getSelectedIndex();
			}
		});	
		
		
		// Combo TipoDato
		comboBTipoDato = new JComboBox();
		comboBTipoDato.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBTipoDato.setBounds(500, 385, 350, 30);
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
		JButton btnCancelarAlta = new JButton("Cancelar");
		btnCancelarAlta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelarAlta.setBounds(920, 660, 180, 40);
		frmG.getContentPane().add(btnCancelarAlta);
		btnCancelarAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				frmG.hide();
			}
			
		});
		
		// Botón Confirmar
		JButton btnDarDeAlta = new JButton("Confirmar");
		btnDarDeAlta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDarDeAlta.setBounds(100, 660, 180, 40);
		frmG.getContentPane().add(btnDarDeAlta);
		
		
		btnDarDeAlta.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (txtFNombre.getText().isEmpty()) {
				lblErrorNombre.setVisible(true);
				txtFEtiqueta.setText("");
				comboBTipoDato.setSelectedIndex(0);
				comboBFenomAsoc.setSelectedIndex(0);
			} else if (txtFEtiqueta.getText().isEmpty()) {
				lblErrorEtiqueta.setVisible(true);
				txtFNombre.setText("");
				comboBTipoDato.setSelectedIndex(0);
				comboBFenomAsoc.setSelectedIndex(0);
			}else {
				try {
					altaCaracteristica(
							txtFNombre.getText(), 
							txtFEtiqueta.getText(), 
							TipoDato.valueOf(comboBTipoDato.getSelectedItem().toString ()), 
							comboBFenomAsoc.getSelectedItem().toString());
				} catch (ServiciosException e) {
					e.printStackTrace();
				}
				txtFNombre.setText("");
				txtFEtiqueta.setText("");
				comboBTipoDato.setSelectedIndex(0);
				comboBFenomAsoc.setSelectedIndex(0);
				lblErrorNombre.setVisible(false);
				lblErrorEtiqueta.setVisible(false);
			}
		}
		});
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
	
	//metodo que se encarga del registro de caracteristica
	private void altaCaracteristica(String nombre, String etiqueta, TipoDato tipoDato, String fenomeno) throws ServiciosException {
		String strerror = "";
		Boolean errores = false;
		Caracteristica caract = new Caracteristica();
			
		// VALIDACIONES de campos
		//Campo Nombre
		if (!(nombre.length() >50)) {
			if (serviciosCaracteristicas.existeNombreCaracteristica(nombre.toUpperCase())) {
				strerror= strerror + "El nombre de la característica ya existe.";
				errores = true;
			}else {
				caract.setNombre(nombre);
			}			
		} else {
			errores=true;
			strerror= strerror + "Nombre caracteristica con mas de 50 caracteres. ";
		}
		
		//Campo Etiqueta
		if (!(etiqueta.length()>50)) {
			caract.setEtiqPresentacion(etiqueta);
		} else {
			errores=true;
			strerror= strerror + " Etiqueta con mas de 50 caracteres. ";
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
		
		if (errores) {
			JOptionPane.showMessageDialog(null,  strerror);
		} else {
			try {	
				serviciosCaracteristicas.create(caract);
				JOptionPane.showMessageDialog(null,  "Característica Creada");	
			}catch ( ServiciosException e) {	
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,  "Error al crear característica. " + e.getMessage());				
			}
		}
	}
	
	//Metodo para obtener datos del fenomeno a traves de su nombre
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
