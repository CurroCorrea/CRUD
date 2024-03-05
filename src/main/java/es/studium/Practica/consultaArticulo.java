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


/**
 * @author curro
 * @version 4.29.0
 * @since 07/02/2024
 * @param Se seriabiliza
 * @param el obj conexion para llamar a los métodos
 * @param en el constructor se le da forma al frame
 */

public class ConsultaArticulo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Conexion conexion = new Conexion();

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaArticulo frame = new ConsultaArticulo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Este es el constructor, dónde se estructura y diseña el frame
	 */
	public ConsultaArticulo() {
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
