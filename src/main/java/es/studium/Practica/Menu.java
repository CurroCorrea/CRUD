package es.studium.Practica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JMenuBar;

import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.BorderLayout;

import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;


/**
 * Este es el proyecto en el que estamos trabajando en AD curso 2024.
 * @author curro
 * @version 4.29.0
 * @since 07/02/2024
 * @return Esta clase crea el menú con las opciones a escoger de Artículos y Tickets
 * 
 */

public class Menu extends JFrame  {

	private static final long serialVersionUID = 1L;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Esta es la creacion y diseño del frame
	 */
	public Menu() {
		setTitle("Menú");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		String[] opcionesA = {"... ","Alta de Artículo", "Baja de Artículo", "Consulta de Artículo", "Modificación de Artículo"};
		JComboBox<String> comboBox = new JComboBox(opcionesA);

		comboBox.setBounds(0, 38, 149, 21);
		getContentPane().add(comboBox);

		JButton btnAr = new JButton("Pulsa para Artículo");
		btnAr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {System.out.println(comboBox.getSelectedItem());
			if(e.getSource().equals(btnAr)) {
				if(comboBox.getSelectedItem().equals(opcionesA[1])) {

					new AltaArticulo();

				}
				if(comboBox.getSelectedItem().equals(opcionesA[2])) {

					new BajaArticulo();

				}
				if(comboBox.getSelectedItem().equals(opcionesA[3])) {

					new ConsultaArticulo();

				}
				if(comboBox.getSelectedItem().equals(opcionesA[4])) {

					new ModificacionArticulo();

				}
			}



			}
		});
		btnAr.setBounds(0, 1, 149, 38);
		getContentPane().add(btnAr);

		String[] opcionesT = {"... ","Alta de Ticket", "Consulta de Tickets"};
		JComboBox<String> comboBox2 = new JComboBox(opcionesT);
		comboBox2.setBounds(159, 38, 146, 21);
		getContentPane().add(comboBox2);

		JButton btnT = new JButton("Pulsa para Tickets");
		btnT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(btnT)) {
					if(comboBox2.getSelectedItem().equals(opcionesT[1])) {
						System.out.println("Error");
						new AltaTicket();

					}if(comboBox2.getSelectedItem().equals(opcionesT[2])) {
						System.out.println("Error");
						new ConsultaTicket();

					}
				}


			}
		});
		btnT.setBounds(156, 1, 149, 38);
		getContentPane().add(btnT);

		JLabel lblNewLabel = new JLabel("TIENDECITACCS");
		lblNewLabel.setBounds(166, 84, 129, 127);
		getContentPane().add(lblNewLabel);
		
		

	}
}
