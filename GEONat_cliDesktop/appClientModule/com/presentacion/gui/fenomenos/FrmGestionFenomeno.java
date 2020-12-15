package com.presentacion.gui.fenomenos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.entities.Fenomeno;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosFenomeno;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Rectangle;

public class FrmGestionFenomeno {

	public JFrame frmGestinDeFenmeno;
	private JTextField txtFID;
	private JTextField txtFNombre;
	private JTextField txtFTelefono;
	private JTextArea txtADescripcion;
	private JTable table;
	DefaultTableModel model;
	//DefaultTableModel model = new DefaultTableModel();
//	Object[] column = {"ID", "Nombre", "Descripción", "Teléfono"};
//	final Object[] row = new Object [4];
	private  ServiciosFenomeno fenomenoServ = ServiciosFenomeno.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmGestionFenomeno window = new FrmGestionFenomeno();
					window.frmGestinDeFenmeno.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmGestionFenomeno() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frmGestinDeFenmeno = new JFrame();
		frmGestinDeFenmeno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmGestinDeFenmeno.setResizable(false);
		frmGestinDeFenmeno.setTitle("GeoNat - Gesti\u00F3n de Fen\u00F3menos");
		frmGestinDeFenmeno.setBounds(10, 10, 719, 433);
		frmGestinDeFenmeno.setSize(1200, 800);
		frmGestinDeFenmeno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGestinDeFenmeno.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("background"));
		panel.setBounds(0, 0, 1200, 800);
		frmGestinDeFenmeno.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId.setBounds(23, 135, 113, 22);
		panel.add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(23, 210, 113, 22);
		panel.add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescripcion.setBounds(23, 280, 113, 22);
		panel.add(lblDescripcion);
		
		txtADescripcion = new JTextArea();
		txtADescripcion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtADescripcion.setBounds(192, 281, 222, 120);
		panel.add(txtADescripcion);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTelefono.setBounds(23, 440, 113, 22);
		panel.add(lblTelefono);
		
		txtFID = new JTextField();
		txtFID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFID.setEditable(false);
		txtFID.setBounds(192, 127, 222, 40);
		panel.add(txtFID);
		txtFID.setColumns(10);
		
		txtFNombre = new JTextField();
		txtFNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFNombre.setBounds(192, 202, 222, 40);
		panel.add(txtFNombre);
		txtFNombre.setColumns(10);
		
		txtFTelefono = new JTextField();
		txtFTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFTelefono.setBounds(192, 432, 222, 40);
		panel.add(txtFTelefono);
		txtFTelefono.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setBounds(442, 74, 732, 676);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(25);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				txtFID.setText(model.getValueAt(i, 0).toString());
				txtFNombre.setText(model.getValueAt(i, 1).toString());
				txtADescripcion.setText(model.getValueAt(i, 2).toString());
				txtFTelefono.setText(model.getValueAt(i, 3).toString());
			}
		});
		table.setBackground(UIManager.getColor("EditorPane.background"));
		//table.setForeground(new Color(220, 220, 220));
		table.setForeground(Color.black);
//		model = new DefaultTableModel();
//		Object[] column = {"ID", "Nombre", "Descripción", "Teléfono"};
//		final Object[] row = new Object [4];
//		model.setColumnIdentifiers(column);
//		table.setModel(model);
		scrollPane.setViewportView(table);
		
		//Boton crear
		JButton btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!gestionFenomeno("alta")) {
//					row [0]=txtFID.getText();
//					row [1]=txtFNombre.getText();
//					row [2]=txtFDescripcion.getText();
//					row [3]=txtFTelefono.getText();
//					model.addRow(row);
					try {
						cargarTabla();
					} catch (ServiciosException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				limpiarVista();
			}
		});
		btnCrear.setBounds(28, 604, 165, 40);
		panel.add(btnCrear);
		
		//Boton modificar
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
//				model.setValueAt(txtFID.getText(), i, 0);
//				model.setValueAt(txtFNombre.getText(), i, 1);
//				model.setValueAt(txtFDescripcion.getText(), i, 2);
//				model.setValueAt(txtFTelefono.getText(), i, 3);
				if (i>=0) {
					int resp = JOptionPane.showConfirmDialog(null, "¿Desea actualizar el fen\u00F3meno seleccionado?", "Atenci\u00F3n", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (resp == JOptionPane.YES_OPTION && !gestionFenomeno("modificar") ) {
						try {
							cargarTabla();
						} catch (ServiciosException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "No se actualiz\u00F3 el fen\u00F3meno");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Para modificar primero debe seleccionar un fen\u00F3meno");
				}
				limpiarVista();
			}
		});
		btnModificar.setBounds(236, 604, 165, 40);
		panel.add(btnModificar);
		
		//Accion de boton eliminar
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if (i>=0) {
					int resp = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el fen\u00F3meno seleccionado?", "Atenci\u00F3n", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (resp == JOptionPane.YES_OPTION) {
						gestionFenomeno("eliminar");
						try {
							cargarTabla();
						} catch (ServiciosException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}								
				}else {
					JOptionPane.showMessageDialog(null, "Para eliminar primero debe seleccionar un fen\u00F3meno.", null, JOptionPane.ERROR_MESSAGE);
				}
				limpiarVista();
			}
		});
		btnEliminar.setBounds(28, 683, 165, 40);
		panel.add(btnEliminar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGestinDeFenmeno.hide();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(236, 683, 165, 40);
		panel.add(btnCancelar);
		
		JPanel banner = new JPanel();
		banner.setLayout(null);
		banner.setBackground(Color.WHITE);
		banner.setBounds(0, 6, 1200, 60);
		panel.add(banner);
		
		JLabel lblReporteDeObservaciones = new JLabel("Gesti\u00F3n de Fen\u00F3menos");
		lblReporteDeObservaciones.setHorizontalAlignment(SwingConstants.LEFT);
		lblReporteDeObservaciones.setForeground(Color.GRAY);
		lblReporteDeObservaciones.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblReporteDeObservaciones.setBounds(new Rectangle(10, 10, 10, 10));
		lblReporteDeObservaciones.setBounds(14, 28, 1174, 25);
		banner.add(lblReporteDeObservaciones);
		
		JLabel label = new JLabel("");
		label.setIconTextGap(0);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(1099, 0, 69, 53);
		banner.add(label);
		
		
		
		try {
			cargarTabla();
		} catch (ServiciosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	

	private boolean gestionFenomeno(String op) {
		int limiteNombre = 50;
		int limiteDescripcion = 200;
		int limiteTel = 20;
		Boolean errores = false;
		String strerror = null;
	 	Fenomeno f = new Fenomeno();
			//Validacion de nombre
			if (!(txtFNombre.getText().isEmpty()) && (txtFNombre.getText().length() < limiteNombre)) {
				f.setNombre(txtFNombre.getText().toLowerCase());
				if(op.equals("alta") || op.equals("modificar")) {
					try {
						boolean existeFenomeno = fenomenoServ.existeNombreFenomeno(f);
						if (existeFenomeno) {
							errores = true;
							strerror = "El fenómeno ya existe. ";
						} 
					} catch (ServiciosException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				errores = true;
				strerror = "Debe ingresar un nombre de fenómeno valido";
			}
			
			//Validacion de descripcion
			if(!(txtADescripcion.getText().isEmpty()) && (txtADescripcion.getText().length()<limiteDescripcion) ) {
				f.setDescripcion(txtADescripcion.getText());						
			} else {
				errores = true;
				strerror = "Debe ingresar una descripcion de menos de 200 caracteres";
			}			
		
		//Validacion Telefono
		if (!(txtFTelefono.getText().isEmpty()) && (txtFTelefono.getText().length() < limiteTel) && (txtFTelefono.getText().toString().matches("[0-9]*"))) {
			f.setTelefono(txtFTelefono.getText().toString());				
		} else {
			errores=true;
			strerror = "Debe ingresar un numero de telefono valido";
		}
		
		//Si no hay errores se crea o actualiza el fenómeno
		if(errores == false) {
			if (op.equals("alta")) {
				try {
					fenomenoServ.create(f);
					JOptionPane.showMessageDialog(null, "Fenomeno Creado");			
				} catch (ServiciosException err) {			
					err.printStackTrace();
				}
			}
			if (op.equals("modificar")) {
				try {
					f.setId_Fenomeno(Integer.parseInt(txtFID.getText()));
					fenomenoServ.update(f);
					JOptionPane.showMessageDialog(null, "Fenomeno Actualizado");
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
			}
			if(op.equals("eliminar")) {
				try {
					fenomenoServ.delete(Integer.parseInt(txtFID.getText()));
				} catch (NumberFormatException | ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Fenomeno eliminado correctamente");
			}
			
		} else {
			JOptionPane.showMessageDialog(null,  strerror);
		}			
		return errores;
	}
	
	//Metodo para limpiar vista
	
	private void limpiarVista() {
		txtFID.setText("");
		txtFNombre.setText("");
		txtADescripcion.setText("");
		txtFTelefono.setText("");
	}
	
	// Método para cargar el contenido de la tabla
	
	private void cargarTabla() throws ServiciosException {		
		
		try {
				
			List<Fenomeno> fen =  fenomenoServ.obtenerTodos(); 

//			String[] nombreColumnas = { "ID", "Nombre", "Descripcion", "Telefono de Emergenica" };
//
//				/*
//				 * El tamaño de la tabla es, 4 columnas (cantidad de datos a mostrar) y
//				 * la cantidad de filas depende de la cantidad de fenomenos
//				 */
			Object[] column = {"ID", "Nombre", "Descripción", "Teléfono"};
			Object[][] datos = new Object[fen.size()][4];

				/* Cargamos la matriz con todos los datos */
				int fila = 0;
				
				
				for (Fenomeno f : fen) {
					
					datos[fila][0] = f.getId_Fenomeno().toString();
					datos[fila][1] = f.getNombre().toString();
					datos[fila][2] = f.getDescripcion().toString();
					datos[fila][3] = f.getTelefono().toString();

					fila++;
				}
				//DefaultTableModel model = new DefaultTableModel(datos, column);
				model = new DefaultTableModel(datos, column);
				model.setColumnIdentifiers(column);
				table.setModel(model);
				
			} catch (Exception e) {

				System.out.println("Error al cargar datos en la tabla Lista Fenomenos. ");
				e.printStackTrace();

			}
		
		}
}
