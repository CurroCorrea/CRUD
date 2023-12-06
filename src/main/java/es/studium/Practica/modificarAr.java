package es.studium.Practica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class modificarAr extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	static int idTicket;
	ModificacionArticulo modificacionArticulo = new ModificacionArticulo();
	Conexion conexion = new Conexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modificarAr frame = new modificarAr();
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
	public modificarAr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.setBounds(258, 197, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(btnNewButton)) {
					double p = Double.parseDouble(txtPrecio.getText());
					int c = Integer.parseInt(txtCantidad.getText());
					if(c != 0 && p != 0.0) {
						conexion.actualizarAr(modificacionArticulo.idArticulo,txtDescripcion.getText(),p,c);
					}
					else {
						JOptionPane.showMessageDialog(null, "Compruebe los campos");
					}
					
				}
			}
		});
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción:");
		lblNewLabel_1.setBounds(127, 59, 86, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio:");
		lblNewLabel_2.setBounds(137, 96, 59, 21);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cantidad:");
		lblNewLabel_3.setBounds(127, 127, 69, 29);
		contentPane.add(lblNewLabel_3);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(223, 63, 96, 19);
		
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(223, 97, 96, 19);
		
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(223, 132, 96, 19);
		
		conexion.rellenarModifAr(modificacionArticulo.idArticulo,txtDescripcion,txtPrecio,txtCantidad);
		
		contentPane.add(txtDescripcion);
		contentPane.add(txtPrecio);
		contentPane.add(txtCantidad);
		
		
		JLabel lblModificacionDeArtculo = new JLabel("MODIFICACION DE ARTÍCULO");
		lblModificacionDeArtculo.setBounds(142, 14, 200, 35);
		contentPane.add(lblModificacionDeArtculo);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnNewButton_1.setBounds(88, 197, 85, 21);
		contentPane.add(btnNewButton_1);
		setVisible(true);
	}
}
