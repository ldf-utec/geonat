package Interfaz;
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

import com.entities.Usuario;
import com.exception.ServiciosException;
import com.servicios.UsuariosBeanRemote;

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

public class FrameListarUsuarios extends JFrame implements DocumentListener {

	JFrame frmListarUsuarios;
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
		frmListarUsuarios.setTitle("GEONat - Lista de Usuarios");
		frmListarUsuarios.setBounds(100, 100, 800, 600);
		frmListarUsuarios.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JLabel lblNombreDeUsuario = new JLabel("Filtrar por nombre de usuario:");
		
		txtFiltroNombreUsuario = new JTextField();
		txtFiltroNombreUsuario.setColumns(10);
		
 
		//Boton Cerrar
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				frmListarUsuarios.hide();
			}
			
		});
		
		scrollPane = new JScrollPane();
		
		JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
		btnEliminarUsuario.setEnabled(false);
		btnEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuariosBeanRemote usuarioBean;
				try {
					usuarioBean = (UsuariosBeanRemote) InitialContext.doLookup("/GEONat/UsuariosBean!com.servicios.UsuariosBeanRemote");
					usuarioBean.delete(idSeleccionado);
					cargarTabla();
					idSeleccionado=0;
					btnEliminarUsuario.setEnabled(false);
					
				} catch (NamingException | ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frmListarUsuarios.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(40, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 705, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNombreDeUsuario, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtFiltroNombreUsuario, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnEliminarUsuario, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(454)
							.addComponent(btnCerrar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
					.addGap(49))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreDeUsuario, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFiltroNombreUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCerrar)
						.addComponent(btnEliminarUsuario))
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
		frmListarUsuarios.getContentPane().setLayout(groupLayout);
		
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
			UsuariosBeanRemote usuarioBean;
			usuarioBean = (UsuariosBeanRemote) InitialContext.doLookup("/GEONat/UsuariosBean!com.servicios.UsuariosBeanRemote");

			ArrayList<Usuario> usuarios =  (ArrayList<Usuario>) usuarioBean.obtenerTodos(); //ControladorMascotas.obtenerTodasMascotas();

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
			filters.add(RowFilter.regexFilter(this.txtFiltroNombreUsuario.getText(), 2));

			TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.table.getModel());
			filtro.setRowFilter(RowFilter.andFilter(filters));
			this.table.setRowSorter(filtro);

	}
}







