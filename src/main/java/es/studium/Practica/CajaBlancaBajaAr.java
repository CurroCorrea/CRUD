package es.studium.Practica;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JTextArea;

import org.junit.jupiter.api.Test;

public class CajaBlancaBajaAr {

	private Conexion conexion;

	@Test
	public void testBajaArticulosConExito() {
		//Casos de prueba para una ejecución exitosa
		System.out.println("Devuelve 0 si es correcto, y 1 si incorrecto,"
				+ "en caso de salir -1, error base de datos");
		conexion=new Conexion();
		int resultadoEsperado = 0;
		int id = 5 ;
		int resultadoReal = conexion.bajaAr(id);;
		assertEquals(resultadoEsperado,resultadoReal);
		System.out.println(resultadoReal);
	}

	@Test
	public void testBajaArticulosFallida() {
		//Casos de prueba para una ejecución exitosa
		System.out.println("Devuelve 0 si es correcto, y 1 si incorrecto,"
				+ "en caso de salir -1, error base de datos");
		conexion=new Conexion();
		int resultadoEsperado = 1;
		int resultadoReal = conexion.bajaAr(30000);;
		assertEquals(resultadoEsperado,resultadoReal);
		System.out.println(resultadoReal);
	}
}
