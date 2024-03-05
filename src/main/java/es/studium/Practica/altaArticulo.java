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



/**
 * @author curro
 * @version 4.29.0
 * @since 07/02/2024
 * @param jtxt1 para el título
 * @param jtxt2 para los campos de artículo
 * @param jtxt3 para los campos de artículo
 * @param conexion el objeto conexion para los métodos
 * @param dlgmensaje mensaje para la respuesta del alta
 */

public class AltaArticulo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jtxt1;
	private JTextField jtxt2;
	private JTextField jtxt3;
	Conexion conexion = new Conexion();
	 JDialog dlgMensaje = new JDialog(this,"Mensaje", true);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaArticulo frame = new AltaArticulo();
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
	public AltaArticulo() {
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
		
		jtxt1 = new JTextField();
		jtxt1.setBounds(196, 78, 96, 19);
		contentPane.add(jtxt1);
		jtxt1.setColumns(10);
		
		jtxt2 = new JTextField();
		jtxt2.setBounds(196, 116, 96, 19);
		contentPane.add(jtxt2);
		jtxt2.setColumns(10);
		
		jtxt3 = new JTextField();
		jtxt3.setBounds(196, 159, 96, 19);
		contentPane.add(jtxt3);
		jtxt3.setColumns(10);
		
		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.setBounds(250, 219, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(jtxt1.getText() != null && verificarNumero()== true && jtxt3.getText()!=null) {
					
					String descripcionAr = jtxt1.getText();
					String precioAr = jtxt2.getText();
					String cantidadAr = jtxt3.getText();
					if(descripcionAr.matches(".*\\d.*")) {//Para comprobar q no utiliza un numero como descripcion
						JOptionPane.showMessageDialog(null, "Compruebe la descripción");
					}
					else {
						conexion.altaAr(descripcionAr, precioAr, cantidadAr);
					}
					
					
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
	        String input = jtxt2.getText();
	        String cantidad = jtxt3.getText();

	        try {
	            // Intentar convertir la entrada a un nÃºmero
	            double numero = Double.parseDouble(input);
	            int c = Integer.parseInt(cantidad);

	            if (numero < 0) {
	                // Mostrar un mensaje de error si el número es negativo
	                JOptionPane.showMessageDialog(this, "Error: Introduce un precio no negativo", "Error", JOptionPane.ERROR_MESSAGE);
	                return false;
	            }
	            if (c < 0) {
	                // Mostrar un mensaje de error si el número es negativo
	                JOptionPane.showMessageDialog(this, "Error: Introduce una cantidad que no sea negativa", "Error", JOptionPane.ERROR_MESSAGE);
	                return false;
	            }

	          return true;
	        } catch (NumberFormatException e) {
	            // La conversión falla, no es un nÃºmero vÃ¡lido
	            JOptionPane.showMessageDialog(this, "Error: Introduce un número válido", "Error", JOptionPane.ERROR_MESSAGE);
	        }
			return false;
	    }
}
