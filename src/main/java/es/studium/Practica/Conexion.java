package es.studium.Practica;

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author curro
 * @version 4.29.0
 * @since 07/02/2024
 * @param los obj static son para la conexión de la base de datos
 * @param la sentencia es el mensaje que se le pasa a la abse de datos
 * @return Aquí estarían todos los métodos registrados 
 */

public class Conexion {


	private static final String URL = "jdbc:mysql://localhost/tiendecitaccs";
	private static final String USER = "root";
	private static final String PASSWORD = "Studium2022;";

	static Connection conexion = null;
	String sentencia = "";

	/**
	 * @return El constructor está para que nos devuelva la conexion realizada en la base de datos
	 */
	public  Conexion() {

		conexion = conectar(); // La igualo al método de conectar para puentear
	}

	/**
	 * @param Este método Conecction conecta con la base de datos
	 */

	private Connection conectar() {
		try {
			// Registrar el driver de MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establecer la conexiÃ³n con la base de datos
			conexion = DriverManager.getConnection(URL, USER, PASSWORD);


		} catch (ClassNotFoundException | SQLException e) {

			System.out.println("Error 1-" + e.getMessage());

		}
		return conexion; 

	}

	/**
	 * @param Rellena el combo box de artículos haciendo una consulta
	 * @return 
	 */

	public int rellenarListArticulos(JComboBox articulos) {

		sentencia = "SELECT idArticulos, descripcionAr FROM articulos;";	
		try {     
			Statement st = conexion.createStatement();
			ResultSet res = st.executeQuery(sentencia);		    
			if (res.next())
			{				
				DefaultComboBoxModel<String> modeloCombo = new DefaultComboBoxModel<>();

				while (res.next()) {
					// Obtener los valores de la consulta
					int idArticulo = res.getInt("idArticulos");
					String descripcionArticulo = res.getString("descripcionAr");

					// Construir el texto que se mostrarÃ¡ en el JComboBox
					String textoCombo = " - " + idArticulo + " - " + descripcionArticulo;

					// Agregar el texto al modelo de la lista
					modeloCombo.addElement(textoCombo);
					
				}

				// Asignar el modelo de lista al JComboBox
				articulos.setModel(modeloCombo);
				return 0;
			}

			res.close();
			st.close();
			return 0;

		} catch (SQLException e) {
			System.out.println("Error 2-" + e.getMessage());
			return 1;
		}
	
		
	}

	/**
	 * Este metodo manda una sentencia a la Base de Datos para crear un Artículo con sus atributos correspondientes
	 * @param Descripción, precio y cantidad.
	 * @return 
	 */

	public int altaAr(String descripcionAr, String precioAr, String cantidadAr) {
		// TODO Auto-generated method stub
		try {
	        // Intenta convertir los valores a tipos numéricos
	        double precio = Double.parseDouble(precioAr);
	        int cantidad = Integer.parseInt(cantidadAr);

	        sentencia = "INSERT INTO articulos VALUES(null, '"+ descripcionAr+"','"+cantidad+"','"+precio+"');";
	        Statement st = conexion.createStatement();
	        int exito = st.executeUpdate(sentencia);
	        if (exito > 0) {
	            // ALTA exitosa
	            JOptionPane.showMessageDialog(null, "Alta de artículo realizada");
	            return 0;
	        } else {
	            // No se actualizó ninguna fila (ID no encontrado)
	            return 1;
	        }
	    } catch (NumberFormatException | SQLException e) {
	        // Si ocurre un error al convertir a números o un error de SQL
	        System.out.println("Error 3- " + e.getMessage());
	        return 1;
	    }
	}

	/**
	 * Este metodo rellena un textarea con una consulta de Artículos
	 * @param TextArea
	 * @return 
	 */

	public int rellenarTextAAr(JTextArea txtA) {

		sentencia = "SELECT idArticulos, descripcionAr, cantidadAr, precioAr FROM articulos;";
		try {
			Statement st = conexion.createStatement();
			ResultSet res = st.executeQuery(sentencia);
			pdf();
			if (res.next())
			{
				txtA.setText(res.getInt("idArticulos")+ " - " + res.getString("descripcionAr")+" - "+res.getInt("cantidadAr")+" - "+ res.getDouble("precioAr"));	
			}	
			res.close();
			st.close();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error 4-" + e.getMessage());
			return 1;
		}
	}
	/**
	 * Este metodo manda una sentencia a la Base de Datos para borrar Artículos.
	 * @param TextArea
	 * @return 
	 */


	public int bajaAr(int i) {
		// TODO Auto-generated method stub
		sentencia = "DELETE FROM articulos WHERE idArticulos = "+ i +";";
		try {
			Statement st = conexion.createStatement();
			int exito = st.executeUpdate(sentencia, Statement.RETURN_GENERATED_KEYS);
			if(exito>0) {
				return 0;
			}else {
			return 1;
			}
		} catch (SQLException sqle) {
			System.out.println("Error 5-" + sqle.getMessage());
			return -1;
		}
	}
	/**
	 * Este metodo saca el ID del Ticket que se está creando.
	 * @param Tickets
	 */
	public int sacarIdTicket()
	{
		sentencia = "Select idTicket FROM tickets WHERE idTicket = (Select MAX(idTicket) from tickets);";
		int id = 0;
		System.out.println(sentencia);

		try {

			Statement st = conexion.createStatement();
			ResultSet res = st.executeQuery(sentencia);


			if (res.next())
			{
				id = res.getInt("idTicket");

			}

			res.close();
			st.close();


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error 6-" + e.getMessage());

		}
		return id;


	}
	/**
	 * Este metodo rellena un textarea con la cantidad de artículos que se han comprado
	 * @param TextArea
	 */
	public int rellenarCantidadProducto(int cantidad, int idAr, int idT)
	{
		sentencia = "INSERT INTO factura VALUES (null, "+ idAr +", "+ idT +", "+ cantidad +" );" ;
		System.out.println(idAr);
		System.out.println(idT);
		System.out.println(cantidad);
		try {
			Statement st = conexion.createStatement();
			st.executeUpdate(sentencia);


		} catch (SQLException sqle) {
			System.out.println("Error 7-" + sqle.getMessage());
		}
		return idT;

	}
	/**
	 * Este metodo rellena otro textarea con la cantidad de artículos que se han comprado y con su subtotal
	 * @param TextArea, cantidad, idArtículo
	 */
	public void rellenarTxtLineaDeFactura(JTextArea txtArea, int cantidad, int idAr)
	{
		sentencia = "SELECT descripcionAr AS 'Descripcion', "
				+ "    precioAr AS 'Precio',"
				+ "    (precioAr * "+ cantidad +") AS 'Subtotal'FROM "
				+ "	articulos "
				+ "WHERE "
				+ "	idArticulos ="+idAr+" ;";

		try
		{
			// Crear una sentencia
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(sentencia);


			while(rs.next())
			{
				txtArea.setText(txtArea.getText()+ idAr +"-"+rs.getString("DescripciÃ³n")+"-"+ cantidad +"-"+rs.getString("Precio")+"-"+rs.getString("Subtotal")+"\n") ;

			}


		}
		catch (SQLException sqle)
		{
			System.out.println("Error 8-"+sqle.getMessage());
		}

	}


	/**
	 * Este metodo manda una sentencia a la base de datos para crear un Artículo
	 * @param TextArea
	 */
	public void altaT(String sentencia2) {
		// TODO Auto-generated method stub
		try {
			Statement st = conexion.createStatement();
			st.executeUpdate(sentencia2, Statement.RETURN_GENERATED_KEYS);
			JOptionPane.showMessageDialog(null, "Alta Ticket realizada");

		} catch (SQLException sqle) {
			System.out.println("Error 9-" + sqle.getMessage());
		}

	}



	public void txtAT(JTextArea txt) {
		sentencia = "SELECT idTicket, totalT, fechaT from tickets;";

		try
		{
			// Crear una sentencia
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(sentencia);


			while(rs.next())
			{
				txt.setText("-"+rs.getInt("idTicket")+"-"+rs.getInt("totalT")+"-"+rs.getString("FechaT")+"\n") ;

			}


		}
		catch (SQLException sqle)
		{
			System.out.println("Error 10-"+sqle.getMessage());
		}

	}

	/**
	 * 
	 * @param TextArea
	 */

	public void rellenarModifAr(int idArticulo, JTextField txtDescripcion, JTextField txtPrecio, JTextField txtCantidad) {
		// TODO Auto-generated method stub
		sentencia = "SELECT descripcionAr,cantidadAr,precioAr FROM articulos WHERE idArticulos = '"+idArticulo+"';";

		try
		{
			// Crear una sentencia
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(sentencia);


			while(rs.next())
			{
				txtDescripcion.setText(rs.getString("descripcionAr")+"\n") ;
				txtPrecio.setText(rs.getString("precioAr")+"\n") ;
				txtCantidad.setText(rs.getString("cantidadAr")+"\n") ;


			}


		}
		catch (SQLException sqle)
		{
			System.out.println("Error 11-"+sqle.getMessage());
		}



	}

	/**
	 * Este metodo saca el total del Ticket que se está creando
	 * @param TextArea
	 */

	public void sacarTotal(int idArticulo, int cantidad, JTextArea textAreaT,double c) {
		// TODO Auto-generated method stub
		sentencia = "SELECT precioAr FROM articulos WHERE idArticulos = '"+idArticulo+"';";


		try
		{
			// Crear una sentencia
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(sentencia);


			while(rs.next())
			{
				textAreaT.setText( cantidad  * rs.getDouble("precioAr")+"\n") ;

			}


		}
		catch (SQLException sqle)
		{
			System.out.println("Error 11-"+sqle.getMessage());
		}



	}

	/**
	 * Este metodo actualiza valores de la Base de Datos.Método Modificar
	 * @param TextArea
	 * @return 
	 */

	public int actualizarAr(int i, String text, double p, int c) {
		// TODO Auto-generated method stub
		sentencia = "UPDATE articulos SET descripcionAr = '"+ text +"',cantidadAr = '"+ c +"', precioAr = '"+ p +"' WHERE idArticulos = "+ i +";";
		try {
			Statement st = conexion.createStatement();
			int exito = st.executeUpdate(sentencia);
			if (exito > 0) {
	            // Actualización exitosa
	            return 0;
	        } else {
	            // No se actualizó ninguna fila (ID no encontrado)
	            return 1;
	        }
	    } catch (SQLException sqle) {
	        System.out.println("Error 12-" + sqle.getMessage());
	        return -1; // Devuelve 1 en caso de error
	    }
	}

	/**
	 * Este método devuelve un PDF con el archivo Jasper que hemos usado para mostrar la información de la consulta.
	 * 
	 */
	
	public static void pdf() {
		try  
		{ 
			// Compilar el informe generando fichero jasper 

			JasperCompileManager.compileReportToFile("C:/Programa_Tiendecita/Articulos.jrxml"); 
			System.out.println("Fichero Ejemplo.jasper generado CORRECTAMENTE!"); 
			// Objeto para guardar parÃ¡metros necesarios para el informe. 
			   //Como ejemplo usamos dos parÃ¡metros autor ytitulo 
			   //DÃ¡ndole valores fijos a los parÃ¡metros del informe. 
			   HashMap<String, Object> parametros = new 
			HashMap<String, Object>(); 
			   parametros.put("titulo", "Consulta Artículos "); 
			   parametros.put("autor", "Curro Correa"); 
			 
			   // Cargar el informe compilado 
			   JasperReport report = (JasperReport) 
			   JRLoader.loadObjectFromFile("C:/Programa_Tiendecita/Ejemplo.jasper"); 
			 
			   // Conectar a la base de datos para sacar la informaciÃ³n 
			   Class.forName("com.mysql.cj.jdbc.Driver"); 
			   String servidor = "jdbc:mysql://localhost:3306/tiendecitaccs"; 
			   String usuarioDB = "root"; 
			   String passwordDB = "Studium2022;"; 
			   Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB); 
			 
			   // Completar el informe con los datos de la base de datos 
			JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion); 
			 
			   // Mostrar el informe en JasperViewer 
			   JasperViewer.viewReport(print, false); 
			   System.out.println("Fichero abierto correctamente con el visor JasperViewer!"); 
			    
			   // Para exportarlo a pdf 
			  
			 JasperExportManager.exportReportToPdfFile(print,"C:/Programa_Tiendecita/Articulos.pdf"); 
			// Abrir el fichero PDF generado 
			   File path = new File("C:/Programa_Tiendecita/Articulos.pdf"); 
			    
			   Desktop.getDesktop().open(path); 
			   System.out.println("Fichero Ejemplo.pdf generado CORRECTAMENTE!"); 
			    
			  } catch (Exception e) { 
			   
			   System.out.println("Error: " + e.toString()); 
			   
			  }	
		}
		
	/**
	 * Este otro, devuelve otro PDF con la consulta para Tickets, con los parámetros necesarios.
	 * @param  FechaEntrada, FechaSalida
	 */
	
		public static void pdfTickets(java.util.Date fechaEntrada, java.util.Date fechaSalida) {
			try  
			{ 
				// Compilar el informe generando fichero jasper 

				JasperCompileManager.compileReportToFile("C:/Programa_Tiendecita/Tickets.jrxml"); 
				System.out.println("Fichero Ejemplo.jasper generado CORRECTAMENTE!"); 
				// Objeto para guardar parÃ¡metros necesarios para el informe. 
				   //Como ejemplo usamos dos parÃ¡metros autor ytitulo 
				   //DÃ¡ndole valores fijos a los parÃ¡metros del informe. 
				   HashMap<String, Object> parametros = new 
				HashMap<String, Object>(); 
				   parametros.put("FECHAENTRADA", fechaEntrada); 
				   parametros.put("FECHASALIDA", fechaSalida); 
				 
				   // Cargar el informe compilado 
				   JasperReport report = (JasperReport) 
				JRLoader.loadObjectFromFile("C:/Programa_Tiendecita/Ejemplo.jasper"); 
				 
				   // Conectar a la base de datos para sacar la informaciÃ³n 
				   Class.forName("com.mysql.cj.jdbc.Driver"); 
				   String servidor = "jdbc:mysql://localhost:3306/tiendecitaccs"; 
				   String usuarioDB = "root"; 
				   String passwordDB = "Studium2022;"; 
				   Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB); 
				 
				   // Completar el informe con los datos de la base de datos 
				JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion); 
				 
				   // Mostrar el informe en JasperViewer 
				   JasperViewer.viewReport(print, false); 
				   System.out.println("Fichero abierto correctamente con el visor JasperViewer!"); 
				    
				   // Para exportarlo a pdf 
				  
				 JasperExportManager.exportReportToPdfFile(print,"C:/Programa_Tiendecita/Tickets.pdf"); 
				// Abrir el fichero PDF generado 
				   File path = new File("C:/Programa_Tiendecita/Tickets.pdf"); 
				    
				   Desktop.getDesktop().open(path); 
				   System.out.println("Fichero Ejemplo.pdf generado CORRECTAMENTE!"); 
				    
				  } catch (Exception e) { 
				   
				   System.out.println("Error: " + e.toString()); 
				   
				  }	
		
	}













}
