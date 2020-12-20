package com.presentacion.gui.usuarios;
import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Usuario;
import com.exception.ServiciosException;
import com.presentacion.servicios.ServiciosUsuario;

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
import java.awt.Font;
import javax.swing.SwingConstants;

public class FrameListarUsuarios extends JFrame implements DocumentListener {

	// Obtengo la instancia del servicio de capa lógica de negocios
	ServiciosUsuario serviciosUsuarios = ServiciosUsuario.getInstance();
	
	public JFrame frmListarUsuarios;
	private JTextField txtFiltroNombreUsuario;
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
					FrameListarUsuarios window = new FrameListarUsuarios();
					window.frmListarUsuarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameListarUsuarios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListarUsuarios = new JFrame();
		frmListarUsuarios.setResizable(false);
		frmListarUsuarios.setMinimumSize(new Dimension(800, 600));
		frmListarUsuarios.setMaximumSize(new Dimension(800, 600));
		frmListarUsuarios.setTitle("GEONat -Ver listado / Dar de Baja");
		frmListarUsuarios.setBounds(10, 10, 1200, 800);
		frmListarUsuarios.setSize(1200, 800);
		frmListarUsuarios.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JLabel lblNombreDeUsuario = new JLabel("Filtrar por nombre de usuario:");
		lblNombreDeUsuario.setBounds(18, 82, 259, 20);
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtFiltroNombreUsuario = new JTextField();
		txtFiltroNombreUsuario.setLocation(313, 72);
		txtFiltroNombreUsuario.setSize(327, 40);
		txtFiltroNombreUsuario.setColumns(10);
		txtFiltroNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
 
		//Boton Cerrar
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setLocation(1067, 714);
		btnCerrar.setSize(105, 34);
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				frmListarUsuarios.hide();
			}
			
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 124, 1154, 569);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		// Botón ELIMINAR
		JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
		btnEliminarUsuario.setForeground(Color.RED);
		btnEliminarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminarUsuario.setBounds(18, 714, 211, 34);
		btnEliminarUsuario.setEnabled(false);		
		btnEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					serviciosUsuarios.delete(idSeleccionado);
					cargarTabla();
					idSeleccionado=0;
					btnEliminarUsuario.setEnabled(false);
					
				} catch ( ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JPanel banner_1 = new JPanel();
		banner_1.setBounds(0, 0, 1195, 60);
		banner_1.setLayout(null);
		banner_1.setBackground(Color.WHITE);
		
		JLabel lblListadoDeFenmenos = new JLabel("Usuarios");
		lblListadoDeFenmenos.setHorizontalAlignment(SwingConstants.LEFT);
		lblListadoDeFenmenos.setForeground(Color.GRAY);
		lblListadoDeFenmenos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblListadoDeFenmenos.setBounds(new Rectangle(10, 10, 10, 10));
		lblListadoDeFenmenos.setBounds(15, 16, 1174, 25);
		banner_1.add(lblListadoDeFenmenos);
		
		JLabel label_1 = new JLabel("");
		label_1.setIconTextGap(0);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(1099, 0, 69, 53);
		banner_1.add(label_1);
		
		table = new JTable();
		table.setRowHeight(25);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(table);
		frmListarUsuarios.getContentPane().setLayout(null);
		frmListarUsuarios.getContentPane().add(btnEliminarUsuario);
		frmListarUsuarios.getContentPane().add(btnCerrar);
		frmListarUsuarios.getContentPane().add(banner_1);
		frmListarUsuarios.getContentPane().add(lblNombreDeUsuario);
		frmListarUsuarios.getContentPane().add(txtFiltroNombreUsuario);
		frmListarUsuarios.getContentPane().add(scrollPane);
		
		try {
			cargarTabla();

		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Listener para Filtrar cuando cambia algo en el Frame
		this.txtFiltroNombreUsuario.getDocument().addDocumentListener(this);
		
		// Listener para cuando se selecciona una Fila en la tabla, y habilita la Eliminación de usuario
		ListSelectionModel model = table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            if(!model.isSelectionEmpty()) {
		        	idSeleccionado = Integer.valueOf((table.getValueAt(table.getSelectedRow(), 0).toString()));
	                btnEliminarUsuario.setEnabled(true);
	            }
	            else {
	            	idSeleccionado = 0;
	                btnEliminarUsuario.setEnabled(false);
	            }
	        }
	    });
		
	
	}
	
	
	
	
	// Método para cargar el contenido de la tabla
	// Recordar que la tabla va dentro de un JScrollPane para que se vean los encabezados
	private void cargarTabla() throws ServiciosException {
		try {
			
			ArrayList<Usuario> usuarios =  (ArrayList<Usuario>) serviciosUsuarios.obtenerTodos(); //ControladorMascotas.obtenerTodasMascotas();

			String[] nombreColumnas = { "ID", "Documento", "Nombre de Usuario", "Nombre", "Apellido", "Tipo Usuario", "Email" };
	
			/*
			 * El tamaño de la tabla es, 7 columnas (cantidad de datos a mostrar) y
			 * la cantidad de filas depende de la cantidad de usuarios
			 */
			Object[][] datos = new Object[usuarios.size()][7];
			
			/* Cargamos la matriz con todos los datos */
			int fila = 0;
	
			for (Usuario u : usuarios) {
	
				datos[fila][0] = u.getId_Usuario().toString();
				datos[fila][1] = u.getNroDocumento().toString();
				datos[fila][2] = u.getNombreUsuario().toString();
				datos[fila][3] = u.getNombre().toString();
				datos[fila][4] = u.getApellido().toString();
				datos[fila][5] = u.getTipoUsuario().getnombre();
				datos[fila][6] = u.getEmail().toString();
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
			
			System.out.println("Error al cargar datos en la tabla Lista Usuarios. ");
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
			filters.add(RowFilter.regexFilter(this.txtFiltroNombreUsuario.getText(), 2));

			TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.table.getModel());
			filtro.setRowFilter(RowFilter.andFilter(filters));
			this.table.setRowSorter(filtro);

	}
}







