package es.studium.Practica;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JTextArea;

import org.junit.jupiter.api.Test;

public class CajaBlancaConsultaAr {
	
	private Conexion conexion;
	
	@Test
	public void testConsultaArticulosExito() {
		//Casos de prueba para una ejecución exitosa
		System.out.println("Devuelve 0 si es correcto, y 1 si incorrecto");
		conexion=new Conexion();
		JTextArea jt = new JTextArea();
		int resultadoEsperado = 0;
		int resultadoReal = conexion.rellenarTextAAr(jt);
		assertEquals(resultadoEsperado,resultadoReal);
		System.out.println(resultadoReal);
		
	}
	@Test
	public void testConsultaArticulosFallida() {
		//Casos de prueba para una ejecución exitosa
		System.out.println("Devuelve 0 si es correcto, y 1 si incorrecto");
		conexion=new Conexion();
		JTextArea jt = new JTextArea();
		int resultadoEsperado = 1;
		int resultadoReal = conexion.rellenarTextAAr(jt);
		assertEquals(resultadoEsperado,resultadoReal);
		System.out.println(resultadoReal);
		
	}

}
