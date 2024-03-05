package es.studium.Practica;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CajaNegraAltaAr {

	
private Conexion conexion;
	
	@Test
	public void testAltaArticulosConExito() {
		//Casos de prueba para una ejecución exitosa
		System.out.println("Devuelve 0 si es correcto, y 1 si incorrecto");
		conexion=new Conexion();
		int resultadoEsperado = 0;
		int id = 1 ;
		int resultadoReal = conexion.bajaAr(id);;
		assertEquals(resultadoEsperado,resultadoReal);
		System.out.println(resultadoReal);
		
	}
	
	@Test
	public void testAltaArticulosFallida() {
		//Casos de prueba para una ejecución exitosa
		System.out.println("Devuelve 0 si es correcto, y 1 si incorrecto");
		conexion=new Conexion();
		int resultadoEsperado = 0;
		int id = 1 ;
		int resultadoReal = conexion.bajaAr(id);;
		assertEquals(resultadoEsperado,resultadoReal);
		System.out.println(resultadoReal);
		
	}
	
}
