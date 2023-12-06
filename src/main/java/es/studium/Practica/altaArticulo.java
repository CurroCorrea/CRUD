package es.studium.Practica;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class altaArticulo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField2;
	private JTextField textField3;
	Conexion conexion = new Conexion();
	 JDialog dlgMensaje = new JDialog(this,"Mensaje", true);
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					altaArticulo frame = new altaArticulo();
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
	public altaArticulo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ALTA DE ARTÍCULO");
		lblNewLabel.setBounds(163, 22, 129, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción:");
		lblNewLabel_1.setBounds(92, 74, 86, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio:");
		lblNewLabel_2.setBounds(119, 111, 59, 21);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cantidad:");
		lblNewLabel_3.setBounds(109, 154, 69, 29);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(196, 78, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setBounds(196, 116, 96, 19);
		contentPane.add(textField2);
		textField2.setColumns(10);
		
		textField3 = new JTextField();
		textField3.setBounds(196, 159, 96, 19);
		contentPane.add(textField3);
		textField3.setColumns(10);
		
		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.setBounds(250, 219, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField.getText() != null && verificarNumero()== true && textField3.getText()!=null) {
					
					String descripcionAr = textField.getText();
					String precioAr = textField2.getText();
					String cantidadAr = textField3.getText();
					conexion.altaAr(descripcionAr, precioAr, cantidadAr);
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Compruebe los campos");
				}
				
				
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
						
				
			}
		});
		btnNewButton_1.setBounds(92, 219, 85, 21);
		contentPane.add(btnNewButton_1);
		setVisible(true);
	}
	 private boolean verificarNumero() {
	        String input = textField2.getText();
	        String cantidad = textField3.getText();

	        try {
	            // Intentar convertir la entrada a un número
	            double numero = Double.parseDouble(input);
	            int c = Integer.parseInt(cantidad);

	          return true;
	        } catch (NumberFormatException e) {
	            // La conversión falló, no es un número válido
	            JOptionPane.showMessageDialog(this, "Error: Introduce un número válido", "Error", JOptionPane.ERROR_MESSAGE);
	        }
			return false;
	    }
}
