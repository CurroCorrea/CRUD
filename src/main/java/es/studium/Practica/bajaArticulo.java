package es.studium.Practica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class bajaArticulo extends JFrame {

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
					bajaArticulo frame = new bajaArticulo();
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
	public bajaArticulo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox articulos = new JComboBox();
		articulos.setBounds(192, 90, 129, 21);
		conexion.rellenarListArticulos(articulos);
		contentPane.add(articulos);

		JLabel lblNewLabel = new JLabel("BAJA DE ARTÍCULOS");
		lblNewLabel.setBounds(155, 22, 129, 34);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Elija Artículo:");
		lblNewLabel_1.setBounds(92, 83, 78, 34);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.setBounds(236, 172, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource().equals(btnNewButton)) {

					if(articulos.getSelectedItem()!= null) {

						String articuloSeleccionado = (String) articulos.getSelectedItem();
						String[] partes = articuloSeleccionado.split("-");

						// Verificar si se obtuvieron ambas partes
						if (partes.length == 3) {
							try {
								// Convertir la parte correspondiente a idArticulo a un entero
								int idArticulo = Integer.parseInt(partes[1].trim());

								// Llamar al método de conexión con el idArticulo
								conexion.bajaAr(idArticulo);

								JOptionPane.showMessageDialog(null, "Articulo eliminado");
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(null, "Error: Formato de idArticulo no válido");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Error: Formato de datos no válido");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Selecciona articulo");
					}

				}
			}
		});
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(85, 172, 85, 21);
		contentPane.add(btnNewButton_1);
		setVisible(true);
	}
}
