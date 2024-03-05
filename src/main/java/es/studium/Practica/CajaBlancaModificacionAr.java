package es.studium.Practica;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JTextArea;

import org.junit.jupiter.api.Test;

public class CajaBlancaModificacionAr {

	private Conexion conexion;

	@Test
	public void testModificacionArticulosConExito() {
		//Casos de prueba para una ejecución exitosa
		System.out.println("Devuelve 0 si es correcto, y 1 si incorrecto,"
				+ "en caso de salir -1, error base de datos");
		conexion=new Conexion();
		int id = 6;
		String descripcion = "Carne Vaca Kuzbakulute";
		double precio = 17.50;
		int cantidad = 2;
		int resultadoEsperado = 0;
		int resultadoReal = conexion.actualizarAr(id, descripcion, precio, cantidad);;
		assertEquals(resultadoEsperado,resultadoReal);
		System.out.println(resultadoReal);

	}
	@Test
	public void testModificacionArticulosFallida() {
		//Casos de prueba para una ejecución fallida
		System.out.println("Devuelve 0 si es correcto, y 1 si incorrecto,"
				+ "en caso de salir -1, error base de datos");
		conexion=new Conexion();
		int id = 300;
		String descripcion = "";
		double precio = -1;
		int cantidad = -7;
		int resultadoEsperado = 1;
		int resultadoReal = conexion.actualizarAr(id, descripcion, precio, cantidad);;
		assertEquals(resultadoEsperado,resultadoReal);
		System.out.println(resultadoReal);

	}
}
