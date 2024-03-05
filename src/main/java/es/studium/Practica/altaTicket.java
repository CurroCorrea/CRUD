package es.studium.Practica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class AltaTicket extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fecha;
	private JTextField total;


	Conexion conexion = new Conexion();

	/**
	 * @author curro
	 * @version 4.29.0
	 * @since 07/02/2024
	 * @param Txt1 para la fecha
	 * @param txt2 para el total
	 * @param simpledformat para el manejo de fechas
	 *  @param en el constructor se le da forma al frame
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaTicket frame = new AltaTicket();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Este es el constructor, d贸nde se estructura y dise帽a el frame
	 */
	public AltaTicket() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ALTA TICKET");
		lblNewLabel.setBounds(177, 10, 132, 29);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("FECHA:");
		lblNewLabel_1.setBounds(106, 70, 86, 27);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("TOTAL:");
		lblNewLabel_1_1.setBounds(106, 125, 54, 27);
		contentPane.add(lblNewLabel_1_1);

		fecha = new JTextField();
		fecha.setColumns(10);
		fecha.setBounds(190, 74, 96, 19);
		contentPane.add(fecha);

		total = new JTextField();
		total.setColumns(10);
		total.setBounds(190, 129, 96, 19);
		contentPane.add(total);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(82, 197, 85, 21);
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub


			}

		});


		contentPane.add(btnCancelar);

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnFinalizar)) {
					String totalT = total.getText();  // Reemplaza textFieldTotal con el nombre real de tu campo de total
					String fechaT = fecha.getText();
					int total = Integer.parseInt(totalT);
					// Validar la fecha
					if (isValidFecha(fechaT) != null) {

						System.out.println("Fecha correcta");

					}
					else {
						JOptionPane.showMessageDialog(null, "La fecha no es v醠ida");
						return; // Detener la ejecuci贸n si la fecha no es v谩lida
					}
					if(total < 0) {
						JOptionPane.showMessageDialog(null, "El total debe ser positivo");
					}
					

					// Validar el total
					double total1 ;
					try {
						total1 = Double.parseDouble(totalT);
						if (total1 <= 0) {
							JOptionPane.showMessageDialog(null, "El total debe ser mayor que cero");
							return; // Detener la ejecuci贸n si el total no es v谩lido
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Ingrese un valor num閞ico v醠ido para el total");
						System.out.println("Error 1 :" + ex.getMessage());

						return; // Detener la ejecuci贸n si no se puede convertir el total a un n鷐ero
					}


					String sentencia = "INSERT INTO tickets VALUES(null,'" + totalT+"','"+ fecha.getText()+"');";
					conexion.altaT(sentencia);
					new AltaFactura();

				}
			}

		});
		btnFinalizar.setBounds(267, 197, 85, 21);
		contentPane.add(btnFinalizar);
		setVisible(true);


	}

	private String[] isValidFecha(String fechaT) {
		// TODO Auto-generated method stub
		// Verificar el formato de la fecha  
		String[] partesFecha = fechaT.split("/");

		if(fechaT.length()!=10&&!fechaT.matches("\\d{4}/\\d{2}/\\d{2}")) {
			JOptionPane.showMessageDialog(null, "Formato de fecha no v醠ido. Utilice yyyy/MM/dd");
			return null;
		}
		
		// Si no hay tres partes, la fecha no tiene el formato esperado
		if (partesFecha.length != 3) {
			JOptionPane.showMessageDialog(null, "Formato de fecha no v醠ido. Utilice yyyy/MM/dd");
			return null;
		}
		String ano = partesFecha[0];
	    String mes = partesFecha[1];
	    String dia = partesFecha[2];
		int diaInt;
		try {
			diaInt = Integer.parseInt(dia);
			if (diaInt < 1 || diaInt > 31) {
				JOptionPane.showMessageDialog(null, "Dias fuera del rango");

				return null; // D铆a fuera de rango
			}
		} catch (NumberFormatException e) {
			System.out.println("Error 2 :" + e.getMessage());

			return null; // No se pudo convertir a entero
		}
		int mesInt;
		try {
			mesInt = Integer.parseInt(mes);
			if (mesInt < 1 || mesInt > 12) {
				JOptionPane.showMessageDialog(null, "Mes fuera del rango");
				return null; // Mes fuera de rango
			}
		} catch (NumberFormatException e) {
			System.out.println("Error 3 :" + e.getMessage());
			return null; // No se pudo convertir a entero
		}
		int anoInt;
		try {
			anoInt = Integer.parseInt(ano);
			if (anoInt < 2024 || anoInt > 2024) {
				JOptionPane.showMessageDialog(null, "A駉 fuera del rango");
				return null; // Mes fuera de rango
			}
		} catch (NumberFormatException e) {
			System.out.println("Error 4 :" + e.getMessage());
			return null; // No se pudo convertir a entero
		}
		// Devolver el arreglo con d铆a, mes y a帽o
		return partesFecha;

	}



}
