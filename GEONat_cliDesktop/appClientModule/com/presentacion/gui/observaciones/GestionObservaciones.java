package com.presentacion.gui.observaciones;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.entities.Fenomeno;
import com.entities.Observacion;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosFenomeno;
import com.presentacion.servicios.ServiciosObservacion;
import com.presentacion.servicios.ServiciosUsuario;

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
import com.toedter.calendar.JDateChooser;

public class GestionObservaciones  {

	// Obtengo la instancia del servicio de capa lógica de negocios
	ServiciosObservacion serviciosObservaciones = ServiciosObservacion.getInstance();

	public JFrame frmGestionObservaciones;
	private JPanel contentPane;
	private JTable table;
	private JButton btnCancelar;
	private JComboBox comboBox; 

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionObservaciones frame = new GestionObservaciones();
					frame.frmGestionObservaciones.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionObservaciones() {
		
		frmGestionObservaciones = new JFrame();
		frmGestionObservaciones.setTitle("GEONat - Observaciones");
		frmGestionObservaciones.setResizable(false);
		frmGestionObservaciones.setBounds(100, 100, 982, 496);
		frmGestionObservaciones.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmGestionObservaciones.getContentPane().setLayout(null);
		
		
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
		btnCargarTabla.setBounds(807, 29, 138, 56);
		frmGestionObservaciones.getContentPane().add(btnCargarTabla);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 88, 913, 315);
		frmGestionObservaciones.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGestionObservaciones.hide();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.setBounds(143, 409, 138, 50);
		frmGestionObservaciones.getContentPane().add(btnCancelar);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboBox.setBounds(445, 412, 108, 42);
		frmGestionObservaciones.getContentPane().add(comboBox);
		
		JLabel lblSeleccioneElId = new JLabel("Seleccione el ID");
		lblSeleccioneElId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeleccioneElId.setBounds(346, 416, 163, 42);
		frmGestionObservaciones.getContentPane().add(lblSeleccioneElId);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(329, 43, 163, 42);
		frmGestionObservaciones.getContentPane().add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(585, 43, 163, 42);
		frmGestionObservaciones.getContentPane().add(dateChooser_1);
		
		JLabel lblFechaDesde = new JLabel("Fecha desde:");
		lblFechaDesde.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaDesde.setBounds(329, 27, 163, 15);
		frmGestionObservaciones.getContentPane().add(lblFechaDesde);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta:");
		lblFechaHasta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaHasta.setBounds(585, 29, 163, 15);
		frmGestionObservaciones.getContentPane().add(lblFechaHasta);
		
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
			
			List<Observacion> listaObservaciones =  serviciosObservaciones.obtenerTodos(); 

			String[] nombreColumnas = { "ID", "Fecha", "Descripcion", "Tipo de Fenómeno" };

			/*
			 * El tamaño de la tabla es, 4 columnas (cantidad de datos a mostrar) y
			 * la cantidad de filas depende de la cantidad de observaciones
			 */
			Object[][] datos = new Object[listaObservaciones.size()][4];

			/* Cargamos la matriz con todos los datos */
			int fila = 0;

			for (Observacion o : listaObservaciones) {

				datos[fila][0] = o.getId_Observacion().toString();
				datos[fila][1] = o.getFecha().toString();
				datos[fila][2] = o.getDescripcion().toString();
				datos[fila][3] = "fenomeno";//o.getFenomeno().getNombre().toString();
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

			System.out.println("Error al cargar datos en la tabla. ");
			e.printStackTrace();

		}
	}
	
	
	
}
