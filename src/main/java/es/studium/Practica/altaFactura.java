package es.studium.Practica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;


/**
 * @author curro
 * @version 4.29.0
 * @since 07/02/2024
 * @param Txt1 para la fecha
 * @param txt2 para el total
 * @param simpledformat para el manejo de fechas
 *  @param en el constructor se le da forma al frame
 */

public class AltaFactura extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Conexion conexion = new Conexion();
	static int idTicket;
	private JTextField textField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaFactura frame = new AltaFactura();
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
	public AltaFactura() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		idTicket = conexion.sacarIdTicket();
		JLabel lblNewLabel = new JLabel("Alta de Factura:Ticket nº"+ idTicket );
		lblNewLabel.setBounds(143, 10, 172, 32);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Artículo:");
		lblNewLabel_1.setBounds(129, 41, 68, 39);
		contentPane.add(lblNewLabel_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(192, 50, 105, 21);
		conexion.rellenarListArticulos(comboBox);
		contentPane.add(comboBox);

		JTextArea textAreaC = new JTextArea();
		textAreaC.setBounds(93, 116, 172, 51);
		contentPane.add(textAreaC);

		JLabel lblNewLabel_2 = new JLabel("Total");
		lblNewLabel_2.setBounds(292, 118, 45, 21);
		contentPane.add(lblNewLabel_2);

		JTextArea textAreaT = new JTextArea();
		textAreaT.setBounds(275, 145, 62, 22);
		contentPane.add(textAreaT);

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(93, 209, 85, 21);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Finalizar");
		btnNewButton_1.setBounds(252, 209, 85, 21);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_3 = new JLabel("Cantidad:");
		lblNewLabel_3.setBounds(129, 89, 68, 17);
		contentPane.add(lblNewLabel_3);

		textField = new JTextField();
		textField.setBounds(184, 87, 54, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		

		JButton anadir = new JButton("Añadir");
		anadir.setBounds(248, 86, 85, 21);
		anadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource().equals(anadir)) {

					if(comboBox.getSelectedIndex()==0 || textField.getText().length()==0)
					{
						JOptionPane.showMessageDialog(null, "Compruebe los campos");
					}

					else {


						int idArticulo = 0;
						String articuloSeleccionado = (String) comboBox.getSelectedItem();
						String[] partes = articuloSeleccionado.split("-");


						idArticulo = Integer.parseInt(partes[1].trim());
						int cantidad =0;
						cantidad = Integer.parseInt(textField.getText());
						double c = 0.0;
						conexion.rellenarCantidadProducto(cantidad, idArticulo, idTicket);
						conexion.rellenarTxtLineaDeFactura(textAreaC, cantidad, idArticulo);
						conexion.sacarTotal(idArticulo,cantidad,textAreaT, c);
						
					}
				}

			}

		});
		contentPane.add(anadir);

		setVisible(true);

	}
}
