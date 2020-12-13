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
import javax.swing.JTextArea;
import java.awt.Rectangle;
import javax.swing.SwingConstants;

public class NewFrameModificar  {
	
	public JFrame frmGestionFenomenos;
	private JPanel contentPane;
	private JButton btnGuardar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JTextField txtFNombre;
	private JTextField txtFTelefono;
	private JTextArea txtADescripcion;
	private JComboBox comboBox; 


	private ServiciosFenomeno fenomenoSrv = ServiciosFenomeno.getInstance();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewFrameModificar frame = new NewFrameModificar();
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
	public NewFrameModificar() {
		
		frmGestionFenomenos = new JFrame();
		frmGestionFenomenos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmGestionFenomenos.setResizable(false);
		frmGestionFenomenos.setTitle("GEONat - Modificaci\u00F3n de Fen\u00F3menos");
		frmGestionFenomenos.setResizable(false);
		frmGestionFenomenos.setBounds(10, 10, 1200, 800);
		frmGestionFenomenos.setSize(1200, 800);
		frmGestionFenomenos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGestionFenomenos.getContentPane().setLayout(null);
		

		
		btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fenomeno f = new Fenomeno();
				String item = comboBox.getSelectedItem().toString();
				String[] parts = item.split(" - ");
				Integer ID = Integer.parseInt(parts[0]); 
				f.setId_Fenomeno(ID);
				f.setNombre(txtFNombre.getText());
				f.setDescripcion(txtADescripcion.getText());
				f.setTelefono(txtFTelefono.getText());
				
				Boolean errores = false;
				String strerror ="";
				ServiciosFenomeno fenomenoServ = ServiciosFenomeno.getInstance();
				try {
					boolean existeFenomeno = fenomenoServ.existeNombreFenomeno(f);
					if (existeFenomeno) {
						errores = true;
						strerror = "El fenómeno ya existe. ";
					} 
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
				if (!errores) {
					try {
						fenomenoSrv.update(f);
						JOptionPane.showMessageDialog(null, "Fenomeno Actualizado");
					} catch (ServiciosException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null,  strerror);
					}			

				}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGuardar.setBounds(550, 700, 270, 40);
		frmGestionFenomenos.getContentPane().add(btnGuardar);
		
		/*
		 * btnEliminar = new JButton("Eliminar"); btnEliminar.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) {
		 * 
		 * try { fenomenoSrv.delete((Integer)comboBox.getSelectedItem());
		 * JOptionPane.showMessageDialog(null, "Fenomeno Eliminado"); } catch
		 * (ServiciosException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } } }); btnEliminar.setForeground(Color.RED);
		 * btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		 * btnEliminar.setBounds(194, 324, 124, 50);
		 * frmGestionFenomenos.getContentPane().add(btnEliminar);
		 */
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGestionFenomenos.hide();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(900, 700, 270, 40);
		frmGestionFenomenos.getContentPane().add(btnCancelar);
		
		txtFNombre = new JTextField();
		txtFNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFNombre.setBounds(348, 220, 350, 40);
		frmGestionFenomenos.getContentPane().add(txtFNombre);
		txtFNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(10, 220, 270, 40);
		frmGestionFenomenos.getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescripcion.setBounds(10, 320, 270, 40);
		frmGestionFenomenos.getContentPane().add(lblDescripcion);
		
		txtFTelefono = new JTextField();
		txtFTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFTelefono.setBounds(348, 500, 350, 40);
		frmGestionFenomenos.getContentPane().add(txtFTelefono);
		txtFTelefono.setColumns(10);
		
		JLabel lblTelefonoDe = new JLabel("Tel\u00E9fono:");
		lblTelefonoDe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTelefonoDe.setBounds(10, 500, 270, 40);
		frmGestionFenomenos.getContentPane().add(lblTelefonoDe);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = comboBox.getSelectedItem().toString();
				String[] parts = item.split(" - ");
				Integer ID = Integer.parseInt(parts[0]); 
				cargarDatos(ID);
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboBox.setBounds(348, 120, 350, 40);
		frmGestionFenomenos.getContentPane().add(comboBox);
		
		JLabel lblSeleccioneElId = new JLabel("Seleccione el ID del fen\u00F3meno");
		lblSeleccioneElId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSeleccioneElId.setBounds(10, 120, 270, 40);
		frmGestionFenomenos.getContentPane().add(lblSeleccioneElId);
		
		txtADescripcion = new JTextArea();
		txtADescripcion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtADescripcion.setBounds(348, 320, 350, 120);
		frmGestionFenomenos.getContentPane().add(txtADescripcion);
		
		JPanel banner = new JPanel();
		banner.setLayout(null);
		banner.setBackground(Color.WHITE);
		banner.setBounds(0, 6, 1195, 60);
		frmGestionFenomenos.getContentPane().add(banner);
		
		JLabel lblModificacinDeFenmenos = new JLabel("Modificaci\u00F3n de Fen\u00F3menos");
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
		
		try {
			cargarComboBox();
		} catch (ServiciosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void cargarComboBox() throws ServiciosException {
		try {
			List<Fenomeno> fen =  fenomenoSrv.obtenerTodos();
			for (Fenomeno f : fen) {
				comboBox.addItem((Integer)f.getId_Fenomeno()+" - "+f.getNombre());
			}
			
		}catch (Exception e) {

			System.out.println("Error al cargar datos en el comboBox Fenomenos. ");
			e.printStackTrace();

		}
	}
	
	private void cargarDatos (Integer filtro) {
		try {
			List<Fenomeno> fen =  fenomenoSrv.obtenerUnoID(filtro);
			for (Fenomeno f : fen) {
				txtFNombre.setText(f.getNombre());;
				txtADescripcion.setText(f.getDescripcion());;
				txtFTelefono.setText(f.getTelefono());;
			}
			
		}catch (Exception e) {

			System.out.println("Error al cargar datos en el comboBox Fenomenos. ");
			e.printStackTrace();

		}
	}
}
