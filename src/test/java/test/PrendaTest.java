package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import prenda.Categoria;
import prenda.Prenda;
import prenda.Trama;

public class PrendaTest extends SetUp{

	@BeforeEach
	private void setUp() {
		setear();
	}

	@Test
	@DisplayName("Debe saberse el tipo de una prenda")
	void tipoDePrenda() {
		assertEquals(remeraAzul.getTipo(), remera);
	}

	@Test
	@DisplayName("Debe saberse a que categoria pertenece una prenda")
	void categoriaDePrenda() {
		assertTrue(remeraAzul.esDeCategoria(Categoria.PARTE_SUPERIOR));
	}
	
	@Test
	@DisplayName("La trama por defecto debe ser lisa")
	void tramaLisaPorDefecto() {
		assertEquals(Trama.LISA, jeanRojo.getTrama());
	}
	
	@Test
	@DisplayName("La trama es rayada")
	void tramaRayada() {
		assertEquals(Trama.RAYADA, remeraAzul.getTrama());
	}
}

