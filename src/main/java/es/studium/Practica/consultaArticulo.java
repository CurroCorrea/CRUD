package es.studium.Practica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class consultaArticulo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Conexion conexion = new Conexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					consultaArticulo frame = new consultaArticulo();
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
	public consultaArticulo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CONSULTA DE ARTÍCULOS:");
		lblNewLabel.setBounds(134, 20, 193, 32);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Retroceder");
		btnNewButton.setBounds(148, 214, 131, 21);
		contentPane.add(btnNewButton);
		
		JTextArea txtA = new JTextArea();
		conexion.rellenarTextAAr(txtA);
		txtA.setBounds(134, 81, 145, 93);
		contentPane.add(txtA);
		setVisible(true);
	}
}
