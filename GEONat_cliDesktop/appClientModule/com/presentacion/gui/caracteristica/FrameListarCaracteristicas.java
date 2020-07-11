package com.presentacion.gui.caracteristica;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.DAO.interfaces.ICaracteristicaDAO;
import com.entities.Caracteristica;
import com.exception.ServiciosException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JFormattedTextField;
import java.awt.Dimension;

public class FrameListarCaracteristicas extends JFrame implements DocumentListener {

	
	JFrame frmListarCaracteristicas;
	private JTextField txtFiltroNombreCaracteristica;
	private JScrollPane scrollPane;
	private JTable table;
	private int idSeleccionado = 0;

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
		frmListarCaracteristicas.setMinimumSize(new Dimension(800, 600));
		frmListarCaracteristicas.setMaximumSize(new Dimension(800, 600));
		frmListarCaracteristicas.setTitle("GEONat - Lista de Caracteristicas");
		frmListarCaracteristicas.setBounds(100, 100, 800, 600);
		frmListarCaracteristicas.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JLabel lblNombreDeCaracteristica = new JLabel("Filtrar por nombre de caracteristica:");
		
		txtFiltroNombreCaracteristica = new JTextField();
		txtFiltroNombreCaracteristica.setColumns(10);
		
 
		//Boton Cerrar
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				frmListarCaracteristicas.hide();
			}
			
		});
		
		scrollPane = new JScrollPane();
		
		JButton btnEliminarCaracteristica = new JButton("Eliminar Caracteristica");
		btnEliminarCaracteristica.setEnabled(false);
		btnEliminarCaracteristica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ICaracteristicaDAO caracteristicaBean;
				try {
					caracteristicaBean = (ICaracteristicaDAO) InitialContext.doLookup("/GEONat/CaracteristicaDAO!com.DAO.CaracteristicaBeanRemote");
					caracteristicaBean.delete(idSeleccionado);
					cargarTabla();
					idSeleccionado=0;
					btnEliminarCaracteristica.setEnabled(false);
					
				} catch (NamingException | ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frmListarCaracteristicas.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(40, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 705, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNombreDeCaracteristica, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtFiltroNombreCaracteristica, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnEliminarCaracteristica, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(454)
							.addComponent(btnCerrar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
					.addGap(49))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreDeCaracteristica, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFiltroNombreCaracteristica, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCerrar)
						.addComponent(btnEliminarCaracteristica))
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
		frmListarCaracteristicas.getContentPane().setLayout(groupLayout);
		
		try {
			cargarTabla();

		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Listener para Filtrar cuando cambia algo en el Frame
		this.txtFiltroNombreCaracteristica.getDocument().addDocumentListener(this);
		
		// Listener para cuando se selecciona una Fila en la tabla, y habilita la Eliminación de caracteristica
		ListSelectionModel model = table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            if(!model.isSelectionEmpty()) {
		        	idSeleccionado = Integer.valueOf((table.getValueAt(table.getSelectedRow(), 0).toString()));
	                btnEliminarCaracteristica.setEnabled(true);
	            }
	            else {
	            	idSeleccionado = 0;
	                btnEliminarCaracteristica.setEnabled(false);
	            }
	        }
	    });
		
	
	}
	
	
	
	
	// Método para cargar el contenido de la tabla
	// Recordar que la tabla va dentro de un JScrollPane para que se vean los encabezados
	private void cargarTabla() throws ServiciosException {
		try {
			ICaracteristicaDAO caracteristicaBean;
			caracteristicaBean = (ICaracteristicaDAO) InitialContext.doLookup("/GEONat/CaracteristicaBean!com.DAO.CaracteristicaBeanRemote");

			ArrayList<Caracteristica> caracteristicas =  (ArrayList<Caracteristica>) caracteristicaBean.obtenerTodos(); //ControladorMascotas.obtenerTodasMascotas();

			String[] nombreColumnas = { "ID_Caracteristica", "Nombre", "Etiqueta"};
			
	
			/*
			 * El tamaño de la tabla es, 7 columnas (cantidad de datos a mostrar) y
			 * la cantidad de filas depende de la cantidad de caracteristicas
			 */
			Object[][] datos = new Object[caracteristicas.size()][7];
			
			/* Cargamos la matriz con todos los datos */
			int fila = 0;
	
			for (Caracteristica c : caracteristicas) {
	
				datos[fila][0] = c.getId_Caracteristica().toString();
				datos[fila][1] = c.getNombre().toString();
				datos[fila][2] = c.getEtiqPresentacion().toString();
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
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
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
			filters.add(RowFilter.regexFilter(this.txtFiltroNombreCaracteristica.getText(), 2));

			TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.table.getModel());
			filtro.setRowFilter(RowFilter.andFilter(filters));
			this.table.setRowSorter(filtro);

	}
}







