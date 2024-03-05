package es.studium.Practica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



public class CajaBlancaAltaAr {
	private Conexion conexion;
	@Test
	public void testAltaArticulosConExito() {
		//Casos de prueba para una ejecución exitosa
		System.out.println("Devuelve 0 si es correcto, y 1 si incorrecto,"
				+ "en caso de salir -1, error base de datos");
		conexion=new Conexion();
		String descripcion = "Lomo";
		String precioAr = "17";
		String cantidad = "5";
		int resultadoEsperado = 0;
		int resultadoReal = conexion.altaAr(descripcion, precioAr, cantidad);
		assertEquals(resultadoEsperado,resultadoReal);
		System.out.println(resultadoReal);
		
	}
	
	@Test
	public void testAltaArticulosFallida() {
		//Casos de prueba para una ejecución exitosa
		System.out.println("Devuelve 0 si es correcto, y 1 si incorrecto,"
				+ "en caso de salir -1, error base de datos");
		conexion=new Conexion();
		String descripcion = "15";
		String precioAr = "hola";
		String cantidad = "adios";
		int resultadoEsperado = 1;
		int resultadoReal = conexion.altaAr(descripcion, precioAr, cantidad);
		assertEquals(resultadoEsperado,resultadoReal);
		System.out.println(resultadoReal);
		
	}

}
