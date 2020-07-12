package com.presentacion.gui.fenomenos;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Fenomeno;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosFenomeno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Dimension;

public class FrameListarFenomenos extends JFrame implements DocumentListener {

	public JFrame frmListarFenomenos;
	private JTextField txtFiltroNombreFenomeno;
	private JScrollPane scrollPane;
	private JTable table;
	private int idSeleccionado = 0;
	private ServiciosFenomeno fenomenoSrv = ServiciosFenomeno.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameListarFenomenos window = new FrameListarFenomenos();
					window.frmListarFenomenos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameListarFenomenos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		

		frmListarFenomenos = new JFrame();
		frmListarFenomenos.setResizable(false);
		frmListarFenomenos.setMinimumSize(new Dimension(800, 600));
		frmListarFenomenos.setMaximumSize(new Dimension(800, 600));
		frmListarFenomenos.setTitle("GEONat - Lista de fen\\u00F3menos");
		frmListarFenomenos.setBounds(100, 100, 800, 600);
		frmListarFenomenos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JLabel lblNombreDeFen = new JLabel("Filtrar por nombre de fenomeno:");

		txtFiltroNombreFenomeno = new JTextField();
		txtFiltroNombreFenomeno.setColumns(10);


		//Boton Cerrar
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				frmListarFenomenos.hide();
			}

		});

		scrollPane = new JScrollPane();

		// Botón ELIMINAR 
		JButton btnEliminarFenomeno = new JButton("Eliminar Fenomeno");
		btnEliminarFenomeno.setEnabled(false);
		btnEliminarFenomeno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	if(idSeleccionado != 0) {
					try {
						fenomenoSrv.delete(idSeleccionado);
						cargarTabla();
						idSeleccionado=0;
						btnEliminarFenomeno.setEnabled(false);

					} catch ( ServiciosException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			//	}


			}
		});

		GroupLayout groupLayout = new GroupLayout(frmListarFenomenos.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(40, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 705, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNombreDeFen, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(txtFiltroNombreFenomeno, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnEliminarFenomeno, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
										.addGap(454)
										.addComponent(btnCerrar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
						.addGap(49))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(53)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombreDeFen, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFiltroNombreFenomeno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(30)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCerrar)
								.addComponent(btnEliminarFenomeno))
						.addGap(23))
				);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(table);
		frmListarFenomenos.getContentPane().setLayout(groupLayout);

		try {
			cargarTabla();

		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Listener para Filtrar cuando cambia algo en el Frame
		this.txtFiltroNombreFenomeno.getDocument().addDocumentListener(this);

		// Listener para cuando se selecciona una Fila en la tabla, y habilita la Eliminación de fenomeno
		ListSelectionModel model = table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				if(!model.isSelectionEmpty()) {
					idSeleccionado = Integer.valueOf((table.getValueAt(table.getSelectedRow(), 0).toString()));
					btnEliminarFenomeno.setEnabled(true);
				}
				else {
					idSeleccionado = 0;
					btnEliminarFenomeno.setEnabled(false);
				}
			}
		});


	}




	// Método para cargar el contenido de la tabla
	// Recordar que la tabla va dentro de un JScrollPane para que se vean los encabezados
	private void cargarTabla() throws ServiciosException {
		try {
			System.out.println("Estoy en el try de cargar tabla");
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
				System.out.println("Estoy en el for de cargar tabla");
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
		filters.add(RowFilter.regexFilter(this.txtFiltroNombreFenomeno.getText(), 2));

		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.table.getModel());
		filtro.setRowFilter(RowFilter.andFilter(filters));
		this.table.setRowSorter(filtro);

	}
}







