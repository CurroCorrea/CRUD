package es.studium.Practica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class consultaTicket extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Conexion conexion = new Conexion();
	private JTextField fechaentrada;
	private JTextField fechasalida;

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
		
		
		JLabel lblNewLabel = new JLabel("CONSULTA TICKETS");
		lblNewLabel.setBounds(164, 25, 134, 20);
		contentPane.add(lblNewLabel);
		
		JButton cancelar = new JButton("CANCELAR");
		cancelar.setBounds(80, 207, 104, 20);
		contentPane.add(cancelar);
		
		JLabel lblNewLabel_1 = new JLabel("FECHA DESDE:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(80, 76, 104, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("FECHA HASTA:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(80, 125, 104, 20);
		contentPane.add(lblNewLabel_2);
		
		fechaentrada = new JTextField();
		fechaentrada.setHorizontalAlignment(SwingConstants.CENTER);
		fechaentrada.setBounds(202, 76, 96, 19);
		contentPane.add(fechaentrada);
		fechaentrada.setColumns(10);
		
		fechasalida = new JTextField();
		fechasalida.setHorizontalAlignment(SwingConstants.CENTER);
		fechasalida.setBounds(202, 126, 96, 19);
		contentPane.add(fechasalida);
		fechasalida.setColumns(10);
		
		JButton consultar = new JButton("CONSULTAR");
		consultar.setBounds(231, 208, 118, 20);
		consultar.addActionListener(this);
		contentPane.add(consultar);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String textoFechaEntrada = fechaentrada.getText();
		String textoFechaSalida = fechasalida.getText();

        String formato = "yyyy-MM-dd";

        Date fechaEntrada = convertirTextoAFecha(textoFechaEntrada, formato);
        Date fechaSalida = convertirTextoAFecha(textoFechaSalida, formato);


        if (fechaEntrada != null && fechaSalida != null) {
            System.out.println(fechaEntrada);
            System.out.println(fechaSalida);
            Conexion.pdfTickets(fechaEntrada, fechaSalida);
        }
		
		
		
		
	}
	
	 public static Date convertirTextoAFecha(String textoFecha, String formato) {
	        SimpleDateFormat sdf = new SimpleDateFormat(formato);
	        try {
	            Date fecha = sdf.parse(textoFecha);
	            return fecha;
	        } catch (ParseException e) {
	            System.out.println("Error: El formato de fecha proporcionado no es válido.");
	            e.printStackTrace();
	            return null;
	        }
	    }

	   
}
