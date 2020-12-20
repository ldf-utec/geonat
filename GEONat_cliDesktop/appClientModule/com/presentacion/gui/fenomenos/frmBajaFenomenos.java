package com.presentacion.gui.fenomenos;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Fenomeno;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosFenomeno;

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
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class frmBajaFenomenos extends JFrame implements DocumentListener {

	// Obtengo la instancia del servicio de capa lógica de negocios
	ServiciosFenomeno serviciosFenomeno = ServiciosFenomeno.getInstance();
	
	public JFrame frmListarFenomenos;
	private JTextField txtFiltroNombreFenomeno;
	private JScrollPane scrollPane;
	private JTable table;
	private int idSeleccionado = 0;
	private JLabel lblNombreDeFenomeno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmBajaFenomenos window = new frmBajaFenomenos();
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
	public frmBajaFenomenos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListarFenomenos = new JFrame();
		frmListarFenomenos.setResizable(false);
		frmListarFenomenos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmListarFenomenos.setBounds(10, 10, 1200, 800);
		frmListarFenomenos.setSize(1200, 800);
		frmListarFenomenos.setTitle("GEONat - Baja de Fen\u00F3menos");
		frmListarFenomenos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmListarFenomenos.getContentPane().setLayout(null);
		
		
		lblNombreDeFenomeno = new JLabel("Filtrar por nombre de fen\u00F3meno:");
		lblNombreDeFenomeno.setLocation(30, 70);
		lblNombreDeFenomeno.setSize(300, 40);
		lblNombreDeFenomeno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtFiltroNombreFenomeno = new JTextField();
		txtFiltroNombreFenomeno.setLocation(340, 70);
		txtFiltroNombreFenomeno.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtFiltroNombreFenomeno.setAlignmentY(Component.TOP_ALIGNMENT);
		txtFiltroNombreFenomeno.setColumns(10);
		txtFiltroNombreFenomeno.setSize(300, 40);
		txtFiltroNombreFenomeno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		
 
		//Boton Cerrar
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setLocation(1000, 700);
		btnCerrar.setSize(150, 40);
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				frmListarFenomenos.hide();
			}
			
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 120, 1150, 550);
		
		// Botón ELIMINAR
		JButton btnEliminarFenomeno = new JButton("Eliminar Fenomeno");
		btnEliminarFenomeno.setForeground(Color.RED);
		btnEliminarFenomeno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminarFenomeno.setBounds(30, 700, 200, 40);
		btnEliminarFenomeno.setEnabled(false);
		btnEliminarFenomeno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int continuar= JOptionPane.showConfirmDialog(null, "¿Confirma eliminación?", null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (continuar==JOptionPane.YES_OPTION) {
					if (idSeleccionado>=0) {
						try {
							serviciosFenomeno.delete(idSeleccionado);
							cargarTabla();
							idSeleccionado=0;
							btnEliminarFenomeno.setEnabled(false);
							
						} catch ( ServiciosException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					btnEliminarFenomeno.setEnabled(false);	
				}
			}
		});
		
		JPanel banner = new JPanel();
		banner.setBounds(1153, 218, 1, 1);
		banner.setLayout(null);
		banner.setBackground(Color.WHITE);
		
		JLabel lblModificacinDeFenmenos = new JLabel("Baja de Fen\u00F3menos");
		lblModificacinDeFenmenos.setHorizontalAlignment(SwingConstants.LEFT);
		lblModificacinDeFenmenos.setForeground(Color.GRAY);
		lblModificacinDeFenmenos.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblModificacinDeFenmenos.setBounds(new Rectangle(10, 10, 10, 10));
		lblModificacinDeFenmenos.setBounds(14, 28, 1174, 25);
		banner.add(lblModificacinDeFenmenos);
		
		JLabel label = new JLabel("");
		label.setIconTextGap(0);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(1099, 0, 69, 53);
		banner.add(label);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(25);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(new Font("Tahoma", Font.PLAIN, 18));

		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(table);
		frmListarFenomenos.getContentPane().setLayout(null);
		frmListarFenomenos.getContentPane().add(txtFiltroNombreFenomeno);
		frmListarFenomenos.getContentPane().add(btnCerrar);
		frmListarFenomenos.getContentPane().add(lblNombreDeFenomeno);
		frmListarFenomenos.getContentPane().add(scrollPane);
		frmListarFenomenos.getContentPane().add(btnEliminarFenomeno);
		frmListarFenomenos.getContentPane().add(banner);
		
		JPanel banner_1 = new JPanel();
		banner_1.setLayout(null);
		banner_1.setBackground(Color.WHITE);
		banner_1.setBounds(0, 0, 1195, 60);
		frmListarFenomenos.getContentPane().add(banner_1);
		
		JLabel lblListadoDeFenmenos = new JLabel("Baja de Fen\u00F3menos");
		lblListadoDeFenmenos.setHorizontalAlignment(SwingConstants.LEFT);
		lblListadoDeFenmenos.setForeground(Color.GRAY);
		lblListadoDeFenmenos.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblListadoDeFenmenos.setBounds(new Rectangle(10, 10, 10, 10));
		lblListadoDeFenmenos.setBounds(14, 28, 1174, 25);
		banner_1.add(lblListadoDeFenmenos);
		
		JLabel label_1 = new JLabel("");
		label_1.setIconTextGap(0);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(1099, 0, 69, 53);
		banner_1.add(label_1);
		
		try {
			cargarTabla();

		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Listener para Filtrar cuando cambia algo en el Frame
		this.txtFiltroNombreFenomeno.getDocument().addDocumentListener(this);
		
		// Listener para cuando se selecciona una Fila en la tabla, y habilita la Eliminación de Fenomeno
		ListSelectionModel model = table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            if(!model.isSelectionEmpty()) {
	            	System.out.println(Integer.valueOf((table.getValueAt(table.getSelectedRow(), 0).toString())));
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
			
			List<Fenomeno> fenomenos =  serviciosFenomeno.obtenerTodos();
			if (fenomenos!=null) {
				String[] nombreColumnas = { "ID", "Nombre", "Descripción", "Teléfono"};
		
				/*
				 * El tamaño de la tabla es, 7 columnas (cantidad de datos a mostrar) y
				 * la cantidad de filas depende de la cantidad de usuarios
				 */
				Object[][] datos = new Object[fenomenos.size()][4];
				
				/* Cargamos la matriz con todos los datos */
				int fila = 0;
		
				for (Fenomeno f : fenomenos) {
		
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
			} else {
				JOptionPane.showMessageDialog(null, "No se han cargado Fenomenos", null, JOptionPane.INFORMATION_MESSAGE);
				frmListarFenomenos.dispose();
			}
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







