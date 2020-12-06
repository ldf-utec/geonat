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

public class NewFrameModificar  {
	
	public JFrame frmGestionFenomenos;
	private JPanel contentPane;
	private JButton btnGuardar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JTextField txtFNombre;
	private JTextField textFDescripcion;
	private JTextField txtFTelefono;
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
		frmGestionFenomenos.setTitle("GEONat - Registro de Fen\u00F3menos");
		frmGestionFenomenos.setResizable(false);
		frmGestionFenomenos.setBounds(100, 100, 392, 496);
		frmGestionFenomenos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmGestionFenomenos.getContentPane().setLayout(null);
		
		btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fenomeno f = new Fenomeno();
				f.setId_Fenomeno((Integer)comboBox.getSelectedItem());
				f.setNombre(txtFNombre.getText());
				f.setDescripcion(textFDescripcion.getText());
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
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnGuardar.setBounds(10, 324, 179, 50);
		frmGestionFenomenos.getContentPane().add(btnGuardar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				try {
					fenomenoSrv.delete((Integer)comboBox.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Fenomeno Eliminado");
				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setForeground(Color.RED);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setBounds(194, 324, 124, 50);
		frmGestionFenomenos.getContentPane().add(btnEliminar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGestionFenomenos.hide();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.setBounds(98, 385, 138, 50);
		frmGestionFenomenos.getContentPane().add(btnCancelar);
		
		txtFNombre = new JTextField();
		txtFNombre.setBounds(98, 120, 206, 31);
		frmGestionFenomenos.getContentPane().add(txtFNombre);
		txtFNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(10, 120, 78, 31);
		frmGestionFenomenos.getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescripcion.setBounds(10, 175, 78, 31);
		frmGestionFenomenos.getContentPane().add(lblDescripcion);
		
		textFDescripcion = new JTextField();
		textFDescripcion.setBounds(98, 175, 206, 69);
		frmGestionFenomenos.getContentPane().add(textFDescripcion);
		textFDescripcion.setColumns(10);
		
		txtFTelefono = new JTextField();
		txtFTelefono.setBounds(98, 263, 206, 31);
		frmGestionFenomenos.getContentPane().add(txtFTelefono);
		txtFTelefono.setColumns(10);
		
		JLabel lblTelefonoDe = new JLabel("Telefono");
		lblTelefonoDe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefonoDe.setBounds(10, 263, 78, 28);
		frmGestionFenomenos.getContentPane().add(lblTelefonoDe);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatos((Integer)comboBox.getSelectedItem());
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboBox.setBounds(183, 39, 108, 42);
		frmGestionFenomenos.getContentPane().add(comboBox);
		
		JLabel lblSeleccioneElId = new JLabel("Seleccione el ID del fenomeno");
		lblSeleccioneElId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeleccioneElId.setBounds(10, 39, 163, 42);
		frmGestionFenomenos.getContentPane().add(lblSeleccioneElId);
		
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
				comboBox.addItem((Integer)f.getId_Fenomeno());
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
				textFDescripcion.setText(f.getDescripcion());;
				txtFTelefono.setText(f.getTelefono());;
			}
			
		}catch (Exception e) {

			System.out.println("Error al cargar datos en el comboBox Fenomenos. ");
			e.printStackTrace();

		}
	}
}
