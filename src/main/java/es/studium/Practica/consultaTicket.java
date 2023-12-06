package es.studium.Practica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;

public class consultaTicket extends JFrame {

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
					consultaTicket frame = new consultaTicket();
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
	public consultaTicket() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txt = new JTextArea();
		txt.setText("-idTickets-fechaT-totalT");
		conexion.txtAT(txt);
		txt.setBounds(145, 69, 145, 93);
		
		contentPane.add(txt);
		
		JLabel lblNewLabel = new JLabel("CONSULTA TICKETS");
		lblNewLabel.setBounds(158, 22, 134, 20);
		contentPane.add(lblNewLabel);
		
		JButton cancelar = new JButton("CANCELAR");
		cancelar.setBounds(158, 208, 118, 21);
		contentPane.add(cancelar);
		setVisible(true);
	}
}
