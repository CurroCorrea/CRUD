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
import javax.swing.JButton;


/**
 * @author curro
* @version 4.29.0
* @since 07/02/2024
* @param Se serializa la clase
* @param el valor est�tico idArt�culo es para la consulta
*/

public class ModificacionArticulo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Conexion conexion = new Conexion();
	static int idArticulo = 0;
	 
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificacionArticulo frame = new ModificacionArticulo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * En el constructor se le da forma y estructura al frame
	 */
	public ModificacionArticulo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MODIFICACIӓN DE ART�CULOS");
		lblNewLabel.setBounds(129, 10, 188, 81);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(215, 118, 113, 21);
		conexion.rellenarListArticulos(comboBox);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Elija Art�culo:");
		lblNewLabel_1.setBounds(92, 111, 124, 34);
		contentPane.add(lblNewLabel_1);
		
		JButton btnModificarAr = new JButton("Modificar");
		btnModificarAr.setBounds(243, 209, 104, 21);
		btnModificarAr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnModificarAr)) {
					
					
					 String articuloSeleccionado = (String) comboBox.getSelectedItem();
					 String[] partes = articuloSeleccionado.split("-");
					 System.out.println(partes.length);
					
				        // Verificar si se obtuvieron ambas partes
				        if (partes.length != 0) {
				            try {
				                // Convertir la parte correspondiente a idArticulo a un entero
				                 idArticulo = Integer.parseInt(partes[1].trim());
				                 System.out.println(idArticulo);
				         
				            } catch (NumberFormatException ex) {
				                JOptionPane.showMessageDialog(null, "Error: Formato de idArticulo no v�lido");
				            }
				        } else {
				            JOptionPane.showMessageDialog(null, "Error: Formato de datos no v�lido");
				        }
				        if(comboBox.getSelectedItem()!=null) {
    	
				        	new ModificarAr();
				        	
				        }
					
					
				}
				
			}
			
		});;
		contentPane.add(btnModificarAr);
		
		JButton cancelarAr = new JButton("Cancelar");
		cancelarAr.setBounds(73, 209, 85, 21);
		contentPane.add(cancelarAr);
		setVisible(true);
	}

}
