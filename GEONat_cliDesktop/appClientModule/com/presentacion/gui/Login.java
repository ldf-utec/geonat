package com.presentacion.gui;
//otro comentario


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JPasswordField;
import javax.swing.InputMap;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField jpassClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login en el sistema GEONat");
		setForeground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login GEONat");
		lblLogin.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblLogin.setBounds(158, 25, 172, 37);
		contentPane.add(lblLogin);
		
		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(38, 100, 86, 20);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a :");
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasea.setBounds(38, 142, 86, 20);
		contentPane.add(lblContrasea);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(134, 97, 221, 26);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		jpassClave = new JPasswordField();
		jpassClave.setBounds(134, 139, 221, 26);
		contentPane.add(jpassClave);
		
		JButton btIngresar = new JButton("Ingresar");
		btIngresar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				char[] clave = jpassClave.getPassword();
				String claveFinal = new String(clave);
				
			if(textUsuario.getText().toUpperCase().equals("ADMINISTRADOR") && claveFinal.toUpperCase().equals("ADMINISTRADOR")) {
				dispose();
//				JOptionPane.showConfirmDialog(null,"Bienvenido al Sistema","Login Correcto",
//					JOptionPane.in);
				Login p = new Login();
				p.setVisible(false);
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FramePrincipal window = new FramePrincipal();
							window.frmGeonat.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				
			}
			else {
				JOptionPane.showMessageDialog(null,"Usuario o Cantraseña Incorrectos","ERROR",
					JOptionPane.ERROR_MESSAGE);
				
				textUsuario.setText("");
				jpassClave.setText("");
				textUsuario.requestFocus();
				}
			}
		});
		
		InputMap im = btIngresar.getInputMap();
        im.put(KeyStroke.getKeyStroke("ENTER"), "pressed");
        im.put(KeyStroke.getKeyStroke("released ENTER"), "released");

        
        getRootPane().setDefaultButton(btIngresar);
		
		
		
		btIngresar.setBounds(298, 231, 115, 29);
		contentPane.add(btIngresar);
		
		JButton btSalir = new JButton("Salir");
		btSalir.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(WIDTH);
			}
		});
		btSalir.setBounds(38, 231, 115, 29);
		contentPane.add(btSalir);
	}
}
