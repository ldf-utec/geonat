package com.presentacion.gui.fenomenos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.entities.Fenomeno;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosFenomeno;

import javax.persistence.PersistenceException;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListarFenomeno  {
	
	public JFrame frmGestionFenomenos;
	private JPanel contentPane;
	private JTable table;

	private ServiciosFenomeno fenomenoSrv = ServiciosFenomeno.getInstance();
	private JTextField txtFFiltroNombre;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarFenomeno frame = new ListarFenomeno();
					frame.frmGestionFenomenos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListarFenomeno() {
		
		frmGestionFenomenos = new JFrame();
		frmGestionFenomenos.setTitle("GEONat - Registro de Fen\u00F3menos");
		frmGestionFenomenos.setResizable(false);
		frmGestionFenomenos.setBounds(100, 100, 681, 495);
		frmGestionFenomenos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmGestionFenomenos.getContentPane().setLayout(null);
		
		
		JButton btnCargarTabla = new JButton("Listar Fen\u00F3menos Existentes");
		btnCargarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cargarTabla();
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCargarTabla.setBounds(428, 74, 201, 23);
		frmGestionFenomenos.getContentPane().add(btnCargarTabla);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 616, 348);
		frmGestionFenomenos.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblFiltrarPorNombre = new JLabel("Filtrar por nombre de Fenomeno:");
		lblFiltrarPorNombre.setBounds(10, 34, 167, 23);
		frmGestionFenomenos.getContentPane().add(lblFiltrarPorNombre);
		
		txtFFiltroNombre = new JTextField();
		txtFFiltroNombre.setBounds(174, 35, 201, 22);
		frmGestionFenomenos.getContentPane().add(txtFFiltroNombre);
		txtFFiltroNombre.setColumns(10);
		
		try {
			cargarTabla();
		} catch (ServiciosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	// Método para cargar el contenido de la tabla
	
	private void cargarTabla() throws ServiciosException {
		try {
			
			List<Fenomeno> fen =  fenomenoSrv.obtenerTodos(); 

			String[] nombreColumnas = { "ID", "Nombre", "Descripcion", "Telefono de Emergenica" };

			/*
			 * El tamaño de la tabla es, 4 columnas (cantidad de datos a mostrar) y
			 * la cantidad de filas depende de la cantidad de fenomenos
			 */
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

			/*
			 * Este codigo indica que las celdas no son editables y que son todas
			 * del tipos String
			 */
			DefaultTableModel model = new DefaultTableModel(datos, nombreColumnas) {
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}

				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return String.class;
				}
			};
			
			
			table.setModel(model);
			table.setAutoscrolls(true);
			table.setCellSelectionEnabled(false);

		} catch (Exception e) {

			System.out.println("Error al cargar datos en la tabla Lista Fenomenos. ");
			e.printStackTrace();

		}
	}
}
