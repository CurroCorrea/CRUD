package es.studium.Practica;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Conexion {

	
	private static final String URL = "jdbc:mysql://localhost/tiendecitaccs";
    private static final String USER = "root";
    private static final String PASSWORD = "Studium2022;";
    
    static Connection conexion = null;
    String sentencia = "";
    
   
public  Conexion() {
	
	conexion = conectar(); // La igualo al método de conectar para puentear
}



private Connection conectar() {
	 try {
            // Registrar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión con la base de datos
             conexion = DriverManager.getConnection(URL, USER, PASSWORD);

            
        } catch (ClassNotFoundException | SQLException e) {
     
            System.out.println("Error 1-" + e.getMessage());
            
        }
	return conexion; 
	 
}



public void rellenarListArticulos(JComboBox articulos) {
	
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

				        // Construir el texto que se mostrará en el JComboBox
				        String textoCombo = " - " + idArticulo + " - " + descripcionArticulo;

				        // Agregar el texto al modelo de la lista
				        modeloCombo.addElement(textoCombo);
				    }

				    // Asignar el modelo de lista al JComboBox
				    articulos.setModel(modeloCombo);
			}
			
			res.close();
			st.close();
		
		
    } catch (SQLException e) {
        System.out.println("Error 2-" + e.getMessage());
        
    }
	
   

	
}



public void altaAr(String descripcionAr, String precioAr, String cantidadAr) {
	// TODO Auto-generated method stub
	 sentencia = "INSERT INTO articulos VALUES(null, '"+ descripcionAr+"','"+precioAr+"','"+cantidadAr+"');";
	
	try {
        Statement st = conexion.createStatement();
        st.executeUpdate(sentencia);
        JOptionPane.showMessageDialog(null, "Alta de artículo realizada");
       
    } catch (SQLException sqle) {
        System.out.println("Error 3-" + sqle.getMessage());
    }
	 

	
}



public void rellenarTextAAr(JTextArea txtA) {

	 sentencia = "SELECT idArticulos, descripcionAr, cantidadAr, precioAr FROM articulos;";
	
	
	 try {
      
		 	Statement st = conexion.createStatement();
		 	ResultSet res = st.executeQuery(sentencia);

		    
			if (res.next())
			{
				txtA.setText(res.getInt("idArticulos")+ " - " + res.getString("descripcionAr")+" - "+res.getInt("cantidadAr")+" - "+ res.getDouble("precioAr"));
				
			}
			
			res.close();
			st.close();
		
		
   } catch (SQLException e) {
       e.printStackTrace();
       System.out.println("Error 4-" + e.getMessage());
       
   }
	
	
}



public void bajaAr(int i) {
	// TODO Auto-generated method stub
	 sentencia = "DELETE FROM articulos WHERE idArticulos = "+ i +";";
	
	try {
        Statement st = conexion.createStatement();
        st.executeUpdate(sentencia, Statement.RETURN_GENERATED_KEYS);

       
    } catch (SQLException sqle) {
        System.out.println("Error 5-" + sqle.getMessage());
    }
	
	
}
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

public void rellenarTxtLineaDeFactura(JTextArea txtArea, int cantidad, int idAr)
{
	sentencia = "SELECT descripcionAr AS 'Descripción', "
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
			txtArea.setText(txtArea.getText()+ idAr +"-"+rs.getString("Descripción")+"-"+ cantidad +"-"+rs.getString("Precio")+"-"+rs.getString("Subtotal")+"\n") ;
			
		}
		

	}
	catch (SQLException sqle)
	{
		System.out.println("Error 8-"+sqle.getMessage());
	}
	
}



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



public void actualizarAr(int i, String text, double p, int c) {
	// TODO Auto-generated method stub
	 sentencia = "UPDATE FROM articulos VALUES(null, '"+ text +"', '"+ p +"', '"+ c +"') WHERE idArticulos = "+ i +";";
		
		try {
	        Statement st = conexion.createStatement();
	        st.executeUpdate(sentencia);

	       
	    } catch (SQLException sqle) {
	        System.out.println("Error 12-" + sqle.getMessage());
	    }
	
}












	
}
