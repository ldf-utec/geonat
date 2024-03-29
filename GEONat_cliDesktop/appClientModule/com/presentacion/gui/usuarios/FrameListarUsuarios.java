package com.presentacion.gui.usuarios;
import java.awt.EventQueue;

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

import com.entities.Usuario;
import com.exception.ServiciosException;
import com.presentacion.SessionData;
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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameListarUsuarios extends JFrame implements DocumentListener, ActionListener {

	// Obtengo la instancia del servicio de capa l�gica de negocios
	ServiciosUsuario serviciosUsuarios = ServiciosUsuario.getInstance();
	
	public JFrame frmListarUsuarios;
	private JTextField txtFiltroNombreUsuario;
	private JScrollPane scrollPane;
	private JTable table;
	private int idSeleccionado = -1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton radioTodos;
	JRadioButton radioActivos;
	JRadioButton radioInactivos;
	JButton btnBajaUsuario;
	private JButton btnAltaUsuario;

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
		frmListarUsuarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblNombreDeUsuario = new JLabel("Filtrar por nombre de usuario:");
		lblNombreDeUsuario.setBounds(18, 82, 259, 20);
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtFiltroNombreUsuario = new JTextField();
		txtFiltroNombreUsuario.setLocation(275, 72);
		txtFiltroNombreUsuario.setSize(279, 40);
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
		
		// Bot�n BAJA DE USUARIO
		btnBajaUsuario = new JButton("Dar de baja usuario");
		btnBajaUsuario.setForeground(new Color(255, 0, 0));
		btnBajaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBajaUsuario.setBounds(28, 714, 211, 34);
		btnBajaUsuario.setEnabled(true);		
		btnBajaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (idSeleccionado == -1) {
						JOptionPane.showMessageDialog(null, "No hay ning�n usuario seleccionado para eliminar",
							      "Baja de usuario", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					try {
						if (serviciosUsuarios.obtenerUno(idSeleccionado).getEstadoActivo()==false) {
							JOptionPane.showMessageDialog(null, "El usuario seleccionado ya est� dado de baja.\nNo se realizar� ninguna operaci�n.",
								      "Baja de usuario", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
					
				
					if(SessionData.idUsuarioActual != idSeleccionado) {
						
						// 0=ok, 2=cancel
						int input = JOptionPane.showConfirmDialog(null, "�Seguro que desea dar de BAJA el usuario seleccionado?",
								"Baja de usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						
						if (input == 0) {
							try {
								serviciosUsuarios.bajaLogica(idSeleccionado);
								List<Usuario> usuarios = serviciosUsuarios.obtenerPorEstado(true); 
								cargarTabla(usuarios);
								radioActivos.setSelected(true);
								idSeleccionado=-1;
								JOptionPane.showMessageDialog(null,  "Usuario dado de baja exitosamente.", "Baja de usuario", JOptionPane.INFORMATION_MESSAGE);
								
							} catch (ServiciosException err) {
								err.printStackTrace();
								JOptionPane.showMessageDialog(null,  "Error al dar de baja el usuario.\nError:\n"  + err.toString() );
							}
						}else {
							return;
						}	
					
					}else {
						JOptionPane.showMessageDialog(null, "No se puede dar de baja al usuario logueado actualmente",
							      "Baja de usuario", JOptionPane.ERROR_MESSAGE);
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
		frmListarUsuarios.getContentPane().add(btnBajaUsuario);
		frmListarUsuarios.getContentPane().add(btnCerrar);
		frmListarUsuarios.getContentPane().add(banner_1);
		frmListarUsuarios.getContentPane().add(lblNombreDeUsuario);
		frmListarUsuarios.getContentPane().add(txtFiltroNombreUsuario);
		frmListarUsuarios.getContentPane().add(scrollPane);
		
		radioTodos = new JRadioButton("Todos");
		radioTodos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radioTodos.setActionCommand("Todos");
		buttonGroup.add(radioTodos);
		radioTodos.setBounds(1063, 82, 109, 23);
		frmListarUsuarios.getContentPane().add(radioTodos);
		radioTodos.addActionListener(this);
		
		
		radioActivos = new JRadioButton("Activos");
		radioActivos.setSelected(true);
		radioActivos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radioActivos.setActionCommand("Activos");
		buttonGroup.add(radioActivos);
		radioActivos.setBounds(767, 82, 109, 23);
		frmListarUsuarios.getContentPane().add(radioActivos);
		radioActivos.addActionListener(this);
		
		radioInactivos = new JRadioButton("Inactivos");
		buttonGroup.add(radioInactivos);
		radioInactivos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radioInactivos.setActionCommand("Inactivos");
		radioInactivos.setBounds(911, 82, 109, 23);
		frmListarUsuarios.getContentPane().add(radioInactivos);
		radioInactivos.addActionListener(this);
		
		JLabel label = new JLabel("Mostrar:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(666, 82, 105, 20);
		frmListarUsuarios.getContentPane().add(label);
		
		btnAltaUsuario = new JButton("Dar de alta usuario");
		btnAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (idSeleccionado == -1) {
					JOptionPane.showMessageDialog(null, "No hay ning�n usuario seleccionado para dar de alta",
						      "Alta de usuario", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				try {
					if (serviciosUsuarios.obtenerUno(idSeleccionado).getEstadoActivo()==true) {
						JOptionPane.showMessageDialog(null, "El usuario seleccionado ya est� dado de alta.\nNo se realizar� ninguna operaci�n.",
							      "Alta de usuario", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
				// 0=ok, 2=cancel
				int input = JOptionPane.showConfirmDialog(null, "�Seguro que desea dar de ALTA el usuario seleccionado?",
						"Alta de usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (input == 0) {
					try {
						serviciosUsuarios.altaLogica(idSeleccionado);
						List<Usuario> usuarios = serviciosUsuarios.obtenerPorEstado(true); 
						cargarTabla(usuarios);
						radioActivos.setSelected(true);
						idSeleccionado=-1;
						
						JOptionPane.showMessageDialog(null,  "Usuario dado de alta exitosamente.", "Alta de usuario", JOptionPane.INFORMATION_MESSAGE);
						
					} catch (ServiciosException err) {
						err.printStackTrace();
						JOptionPane.showMessageDialog(null,  "Error al dar de alta el usuario.\nError:\n"  + err.toString() );
					}
				}else {
					return;
				}	
				
				
			}
		});
		btnAltaUsuario.setForeground(new Color(50, 205, 50));
		btnAltaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAltaUsuario.setEnabled(true);
		btnAltaUsuario.setBounds(263, 714, 211, 34);
		frmListarUsuarios.getContentPane().add(btnAltaUsuario);
		
		try {
			List<Usuario> usuarios = serviciosUsuarios.obtenerPorEstado(true); 
			cargarTabla(usuarios);

		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Listener para Filtrar cuando cambia algo en el Frame
		this.txtFiltroNombreUsuario.getDocument().addDocumentListener(this);
		
		// Listener para cuando se selecciona una Fila en la tabla, y habilita la Eliminaci�n de usuario
		ListSelectionModel model = table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            if(!model.isSelectionEmpty()) {
		        	idSeleccionado = Integer.valueOf((table.getValueAt(table.getSelectedRow(), 0).toString()));

	            }
	            else {
	            	idSeleccionado = -1;

	            }
	        }
	    });
		
	
	}
	
	
	
	
	// M�todo para cargar el contenido de la tabla
	// Recordar que la tabla va dentro de un JScrollPane para que se vean los encabezados
	private void cargarTabla(List<Usuario> usuarios ) throws ServiciosException {
		try {
			

			String[] nombreColumnas = { "ID", "Documento", "Nombre de Usuario", "Nombre", "Apellido", "Tipo Usuario", "Email" };
	
			/*
			 * El tama�o de la tabla es, 7 columnas (cantidad de datos a mostrar) y
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
			
			table.setRowHeight(30);
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

	
	@Override
	public void actionPerformed(ActionEvent e) {
		txtFiltroNombreUsuario.setText(null);
		this.table.setRowSorter(null); // Quito el filtrado de la tabla
		
		if (e.getActionCommand().equals("Activos")) {
           
			System.out.println("Selected Radio Button: " + buttonGroup.getSelection().getActionCommand());
            try {
				cargarTabla(serviciosUsuarios.obtenerPorEstado(true));
				
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
            
        }
		
		if (e.getActionCommand().equals("Inactivos")) {
        	System.out.println("Selected Radio Button: " + buttonGroup.getSelection().getActionCommand());
        	try {
        		List<Usuario> list = serviciosUsuarios.obtenerPorEstado(false);
				cargarTabla(list);
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
        	
		}
	
		if (e.getActionCommand().equals("Todos")) {
			System.out.println("Selected Radio Button: " + buttonGroup.getSelection().getActionCommand());
			try {
				cargarTabla(serviciosUsuarios.obtenerTodos());
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
        	
        
	}
	
	
	private void configurarBotones() {
		
		switch (buttonGroup.getSelection().getActionCommand()) {
		case "Activos":
			btnAltaUsuario.setVisible(false);
			btnBajaUsuario.setVisible(true);
			break;
		
		case "Inactivos":
			btnAltaUsuario.setVisible(true);
			btnBajaUsuario.setVisible(false);
			break;
			
		case "Todos":
			btnAltaUsuario.setVisible(false);
			btnBajaUsuario.setVisible(true);
			break;

		default:
			break;
		}
		
	}
}







