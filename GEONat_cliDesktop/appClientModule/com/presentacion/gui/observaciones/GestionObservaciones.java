package com.presentacion.gui.observaciones;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.Enums.Criticidad;
import com.entities.Fenomeno;
import com.entities.Observacion;
import com.exception.ServiciosException;
import com.presentacion.gui.FramePrincipal;
import com.presentacion.servicios.ServiciosFenomeno;
import com.presentacion.servicios.ServiciosObservacion;
import com.presentacion.servicios.ServiciosUsuario;

import javax.persistence.PersistenceException;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class GestionObservaciones  {

	// Obtengo la instancia del servicio de capa lógica de negocios
	ServiciosObservacion serviciosObservaciones = ServiciosObservacion.getInstance();

	public JFrame frmGestionObservaciones;
	private JPanel contentPane;
	private JTable table;
	private JButton btnCancelar;
	private JComboBox cmbCriticidad; 
	private JDateChooser dateChooser_hasta;
	private JDateChooser dateChooser_desde;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
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
		//frmGestionObservaciones.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Leandro\\Downloads\\icongeonat_eij_icon.ico"));
		frmGestionObservaciones.setTitle("GEONat - Observaciones");
		frmGestionObservaciones.setResizable(false);
		frmGestionObservaciones.setBounds(100, 100, 1200, 800);
		frmGestionObservaciones.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmGestionObservaciones.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("SansSerif", Font.PLAIN, 18));
		scrollPane.setBounds(50, 195, 1100, 500);
		frmGestionObservaciones.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setFont(new Font("SansSerif", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		
		btnCancelar = new JButton("Cerrar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGestionObservaciones.hide();
			}
		});
		btnCancelar.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnCancelar.setBounds(1012, 715, 138, 50);
		frmGestionObservaciones.getContentPane().add(btnCancelar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Filtrar por nivel de criticidad", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(50, 87, 280, 96);
		frmGestionObservaciones.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		cmbCriticidad = new JComboBox();
		cmbCriticidad.setBounds(105, 23, 144, 42);
		panel_1.add(cmbCriticidad);
//		cmbCriticidad.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//					filtrar();
//				} catch (Exception e2) {
//					System.out.println(e2);
//				}
//				
//				
//				
//				
//			}
//		});
		cmbCriticidad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblCriticidad = new JLabel("Criticidad:");
		lblCriticidad.setBounds(17, 27, 91, 34);
		panel_1.add(lblCriticidad);
		lblCriticidad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel panel = new JPanel();
		panel.setFont(new Font("SansSerif", Font.PLAIN, 18));
		panel.setBorder(new TitledBorder(null, "Filtrar de fecha", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panel.setBounds(339, 87, 647, 96);
		frmGestionObservaciones.getContentPane().add(panel);
		
		JLabel lblFechaDesde = new JLabel("Fecha desde:");
		lblFechaDesde.setBounds(39, 31, 109, 24);
		lblFechaDesde.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblFechaDesde.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		dateChooser_desde = new JDateChooser();
		dateChooser_desde.setBounds(158, 23, 180, 40);
		dateChooser_desde.setFont(new Font("SansSerif", Font.PLAIN, 18));
		dateChooser_desde.getCalendarButton().setFont(new Font("Dialog", Font.PLAIN, 18));
		JTextFieldDateEditor dateChooser_desdeEditor = (JTextFieldDateEditor) dateChooser_desde.getDateEditor();
		dateChooser_desdeEditor.setEnabled(false);
		
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta:");
		lblFechaHasta.setBounds(350, 31, 104, 24);
		lblFechaHasta.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblFechaHasta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		dateChooser_hasta = new JDateChooser();
		dateChooser_hasta.setBounds(455, 23, 180, 40);
		dateChooser_hasta.setFont(new Font("SansSerif", Font.PLAIN, 18));
		JTextFieldDateEditor dateChooser_hastaEditor = (JTextFieldDateEditor) dateChooser_hasta.getDateEditor();
		dateChooser_hastaEditor.setEnabled(false);
		panel.setLayout(null);
		panel.add(lblFechaDesde);
		panel.add(dateChooser_desde);
		panel.add(lblFechaHasta);
		panel.add(dateChooser_hasta);
		
		JPanel banner = new JPanel();
		banner.setLayout(null);
		banner.setBackground(Color.WHITE);
		banner.setBounds(0, 0, 1194, 59);
		frmGestionObservaciones.getContentPane().add(banner);
		
		JLabel lblReporteDeObservaciones = new JLabel("Reporte de observaciones");
		lblReporteDeObservaciones.setBounds(new Rectangle(10, 10, 10, 10));
		lblReporteDeObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblReporteDeObservaciones.setHorizontalAlignment(SwingConstants.LEFT);
		lblReporteDeObservaciones.setForeground(Color.GRAY);
		lblReporteDeObservaciones.setBounds(14, 28, 1174, 25);
		banner.add(lblReporteDeObservaciones);
		
		JLabel label = new JLabel("");
		label.setIconTextGap(0);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(1099, 0, 69, 53);
		//label.setIcon(new ImageIcon(this.getClass().getResource("../img/logo_small.png")));
		banner.add(label);
		
		
		
		JButton btnAplicarFecha = new JButton("Aplicar filtros");
		btnAplicarFecha.setBounds(1001, 109, 149, 40);
		frmGestionObservaciones.getContentPane().add(btnAplicarFecha);
		btnAplicarFecha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAplicarFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				    	 
				filtrar();		
				
			}
		});
		

		
		
				
		try {
			cargarTabla(serviciosObservaciones.obtenerTodos());
			cargarComboBox();

			
			
		} catch (ServiciosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	// Método para cargar el contenido de la tabla
	
	private void cargarTabla(List<Observacion> listaObservaciones) throws ServiciosException {
		try {
			
			//List<Observacion> listaObservaciones =  serviciosObservaciones.obtenerTodos(); 

			String[] nombreColumnas = { "ID", "Fecha", "Descripcion", "Tipo de Fenómeno", "Criticidad" };

			/*
			 * El tamaño de la tabla es, 5 columnas (cantidad de datos a mostrar) y
			 * la cantidad de filas depende de la cantidad de observaciones
			 */
			Object[][] datos = new Object[listaObservaciones.size()][5];
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			/* Cargamos la matriz con todos los datos */
			int fila = 0;

			for (Observacion o : listaObservaciones) {

				datos[fila][0] = o.getId_Observacion().toString();
				datos[fila][1] = dateFormat.format(o.getFecha());
				datos[fila][2] = o.getDescripcion().toString();
				datos[fila][3] = o.getFenomeno().getNombre().toString();
				datos[fila][4] = o.getCriticidad().toString();
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
	
	private void cargarComboBox() throws ServiciosException {
		try {
			cmbCriticidad.addItem("Todos");
			for (Criticidad c : Criticidad.values()) {
				cmbCriticidad.addItem(c.toString());
				}
			
		}catch (Exception e) {

			System.out.println("Error al cargar datos en el comboBox Criticidad. ");
			e.printStackTrace();

		}
	}
	
	
	
	
	public void filtrar() {
		Date startDate;
		Date endDate;
		List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(3);
		
		try {
			
						
			String strCriticidad = cmbCriticidad.getSelectedItem().toString();
			if (strCriticidad == "Todos") { strCriticidad=""; }
			
			// el filtro de fechas sólo lo aplico si está bien seleccionadas
			if (ValidateFechas()) {
				startDate = this.dateChooser_desde.getDate();
				endDate = this.dateChooser_hasta.getDate();
				
				
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				cal.add(Calendar.DATE, -1);
				startDate = cal.getTime();
				
				
				filters.add( RowFilter.dateFilter(ComparisonType.AFTER , startDate) );
				filters.add( RowFilter.dateFilter(ComparisonType.BEFORE, endDate) );
			}
			
			// el filtro de criticididad siempre lo aplica
			filters.add(RowFilter.regexFilter(strCriticidad, 4));
			
			TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.table.getModel());
			filtro.setRowFilter(RowFilter.andFilter(filters));
			this.table.setRowSorter(filtro);

			
			
			
			//JOptionPane.showMessageDialog(frmGestionObservaciones, cal.getTime(), null, JOptionPane.ERROR_MESSAGE);
			
			
			
		} catch (Exception e) {
			throw e;
		}
				
	}
	
	
	
	
	
	
	
	// Helpers methods
	private boolean ValidateFechas() {
		Date startDate = dateChooser_desde.getDate();
		Date endtDate = dateChooser_hasta.getDate();
		
		// los dos campos están vacío?
		if( (startDate == null) & ( endtDate == null ) ) { return false;}
		
		
		// hay sólo uno ?
		if( !((startDate == null) || ( endtDate == null )) ) {
			if(startDate.compareTo(endtDate) > 0) {
				JOptionPane.showMessageDialog(frmGestionObservaciones, "La fecha final debe ser posterior a la inicial", null, JOptionPane.ERROR_MESSAGE);
		    }else{
		    	 return true;
		    }
			
		} else {
		    JOptionPane.showMessageDialog(frmGestionObservaciones, "Falta seleccionar alguna fecha", null, JOptionPane.ERROR_MESSAGE);
		}
		return false;
		
	}
	
	
	
}
