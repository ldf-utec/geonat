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
		frmGestionFenomenos.setTitle("GEONat - Modificar Fen\u00F3menos");
		frmGestionFenomenos.setResizable(false);
		frmGestionFenomenos.setBounds(100, 100, 1200, 800);
		frmGestionFenomenos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
				
				
				try {
					fenomenoSrv.update(f);
					JOptionPane.showMessageDialog(null, "Fenomeno Actualizado");
				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGuardar.setBounds(10, 324, 270, 40);
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
		btnCancelar.setBounds(348, 324, 270, 40);
		frmGestionFenomenos.getContentPane().add(btnCancelar);
		
		txtFNombre = new JTextField();
		txtFNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFNombre.setBounds(348, 106, 270, 40);
		frmGestionFenomenos.getContentPane().add(txtFNombre);
		txtFNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(10, 120, 270, 40);
		frmGestionFenomenos.getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescripcion.setBounds(10, 175, 270, 40);
		frmGestionFenomenos.getContentPane().add(lblDescripcion);
		
		txtFTelefono = new JTextField();
		txtFTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFTelefono.setBounds(348, 263, 270, 40);
		frmGestionFenomenos.getContentPane().add(txtFTelefono);
		txtFTelefono.setColumns(10);
		
		JLabel lblTelefonoDe = new JLabel("Tel\u00E9fono");
		lblTelefonoDe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTelefonoDe.setBounds(10, 263, 78, 28);
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
		comboBox.setBounds(348, 40, 270, 40);
		frmGestionFenomenos.getContentPane().add(comboBox);
		
		JLabel lblSeleccioneElId = new JLabel("Seleccione el ID del fen\u00F3meno");
		lblSeleccioneElId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSeleccioneElId.setBounds(10, 39, 270, 40);
		frmGestionFenomenos.getContentPane().add(lblSeleccioneElId);
		
		txtADescripcion = new JTextArea();
		txtADescripcion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtADescripcion.setBounds(348, 159, 270, 100);
		frmGestionFenomenos.getContentPane().add(txtADescripcion);
		
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
