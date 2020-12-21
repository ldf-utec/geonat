package com.presentacion.gui.caracteristica;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Caracteristica;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosCaracteristica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameListarCaracteristicas extends JFrame implements DocumentListener {

	ServiciosCaracteristica serviciosCaracteristicas = ServiciosCaracteristica.getInstance();
	
	public JFrame frmListarCaracteristicas;
	private JTextField txtFiltroNombreCaracteristica;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JLabel lblBajaDeCaractersticas;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameListarCaracteristicas window = new FrameListarCaracteristicas();
					window.frmListarCaracteristicas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameListarCaracteristicas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListarCaracteristicas = new JFrame();
		frmListarCaracteristicas.setResizable(false);
		frmListarCaracteristicas.setTitle("GEONat -Ver listado / Dar de Baja");
		frmListarCaracteristicas.setBounds(10, 10, 1200, 800);
		frmListarCaracteristicas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblNombreDeCaracteristica = new JLabel("Filtrar por nombre de caracter\u00EDstica:");
		lblNombreDeCaracteristica.setBounds(38, 88, 300, 40);
		lblNombreDeCaracteristica.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtFiltroNombreCaracteristica = new JTextField();
		txtFiltroNombreCaracteristica.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFiltroNombreCaracteristica.setBounds(350, 85, 350, 46);
		txtFiltroNombreCaracteristica.setColumns(10);
		
 
		//Boton Cerrar
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCerrar.setBounds(970, 715, 180, 40);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				frmListarCaracteristicas.dispose();
			}
			
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 150, 1112, 532);
		
		JButton btnEliminar = new JButton("Eliminar Caracter\u00EDstica");
		btnEliminar.setForeground(Color.RED);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminar.setBounds(38, 715, 210, 40);
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if (i >= 0) {
					int resp = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la caracter\u00EDstica seleccionada?", "Atenci\u00F3n", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (resp == JOptionPane.YES_OPTION ) {
						eliminarCaracteristica(i);
					}
				}
				btnEliminar.setEnabled(false);
			}
		});
		frmListarCaracteristicas.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEliminar.setEnabled(true);
			}
		});
		
		frmListarCaracteristicas.getContentPane().add(scrollPane);
		frmListarCaracteristicas.getContentPane().add(lblNombreDeCaracteristica);
		frmListarCaracteristicas.getContentPane().add(txtFiltroNombreCaracteristica);
		frmListarCaracteristicas.getContentPane().add(btnEliminar);
		frmListarCaracteristicas.getContentPane().add(btnCerrar);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1194, 59);
		frmListarCaracteristicas.getContentPane().add(panel);
		
		lblBajaDeCaractersticas = new JLabel("Caracter\u00EDsticas");
		lblBajaDeCaractersticas.setHorizontalAlignment(SwingConstants.LEFT);
		lblBajaDeCaractersticas.setForeground(Color.GRAY);
		lblBajaDeCaractersticas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBajaDeCaractersticas.setBounds(new Rectangle(10, 10, 10, 10));
		lblBajaDeCaractersticas.setBounds(14, 17, 1174, 25);
		panel.add(lblBajaDeCaractersticas);
		
		try {
			cargarTabla();
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
		// Listener para Filtrar cuando cambia algo en el Frame
		this.txtFiltroNombreCaracteristica.getDocument().addDocumentListener(this);

	}
	
	// Método para cargar el contenido de la tabla
	private void cargarTabla() throws ServiciosException {
		
		int fila = 0;
		int largoFilas = 0;
		boolean tablaVacia = true;
		String[] nombreColumnas = { "ID_Caracter\u00EDstica", "Nombre", "Etiqueta", "Tipo de Dato", "Fenómeno Asociado"};
		Object[][] datos = new Object[largoFilas][5];
		
		try {

			List<Caracteristica> caracteristicas =  serviciosCaracteristicas.obtenerTodos(); 

			datos = new Object[caracteristicas.size()][5];
			
			if (caracteristicas.size() > 0) {
				
				for (Caracteristica c : caracteristicas) {
					
					datos[fila][0] = c.getId_Caracteristica().toString();
					datos[fila][1] = c.getNombre().toString();
					datos[fila][2] = c.getEtiqPresentacion().toString();
					datos[fila][3] = c.getTipoDato().toString();
					if (c.getFenomeno() != null) {
						datos[fila][4] = c.getFenomeno().getNombre();
					}else {
						datos[fila][4] = "No tiene";
					}
					
					fila++;
				}
			} 	else {
				JOptionPane.showMessageDialog(null,  "A\u00FAn no se han cargado caracter\u00EDsticas", null, JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());		
		}
		
		model = new DefaultTableModel(datos, nombreColumnas);
		model.setColumnIdentifiers(nombreColumnas);
		table.setModel(model);
		
	}
	
	//Metodo para eliminar una caracteristica
	private void eliminarCaracteristica(int row) {
		try {
			ServiciosCaracteristica caracteristicaServ = ServiciosCaracteristica.getInstance();
			if (!(model.getValueAt(row, 4).toString().equals("No tiene"))) {
				JOptionPane.showMessageDialog(null,  "No se puede eliminar la caracter\u00EDstica debido a que tiene un fen\u00F3meno asociado.", null, JOptionPane.ERROR_MESSAGE);
			} else {
				caracteristicaServ.delete(Integer.parseInt(model.getValueAt(row, 0).toString()));
				JOptionPane.showMessageDialog(null,  "Caracter\u00EDstica Eliminada correctamente", null, JOptionPane.INFORMATION_MESSAGE);	
			}
			cargarTabla();	
		} catch ( ServiciosException e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}
	}

	
	@Override
	public void changedUpdate(DocumentEvent e) {
		this.filtrar();

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		this.filtrar();

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		this.filtrar();

	}
	
	public void filtrar() {
		
			List<RowFilter<Object,Object>> filters = new ArrayList<>(1);
			filters.add(RowFilter.regexFilter(this.txtFiltroNombreCaracteristica.getText(), 1));

			TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.table.getModel());
			filtro.setRowFilter(RowFilter.andFilter(filters));
			this.table.setRowSorter(filtro);

	}
}







