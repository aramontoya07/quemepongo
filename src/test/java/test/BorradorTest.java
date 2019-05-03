package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import quemepongo.Borrador;
import quemepongo.ColorRGB;

class BorradorTest {
	
	@Test
	@DisplayName("La prenda debe tener un color primario y opcionalmente un color secundario DISTINTO")
	void coloresDistintos() {
		try {
			Borrador borradorPrueba = new Borrador();
			borradorPrueba.definirColorPrimario(new ColorRGB(100,100,100));
			borradorPrueba.definirColorSecundario(new ColorRGB(100,100,100));
			fail("Deberia tirar una excepcion al introducir dos colores iguales");
		} catch (Exception e) {
			assertEquals(e.getMessage(),"El color secundario debe diferir del primario");
		}
	}

	@Test
	@DisplayName("Para definir un color secundario primero se debe definir uno primario")
	void ordenColores() {
		// TODO usar assertThrows
		// @Rule ExpectedException
		try {
			Borrador prueba = new Borrador();
			prueba.definirColorSecundario(new ColorRGB(255,9,0));
			fail("zaraza TODO zaraza");
		} catch (Exception e) {
			assertEquals(e.getMessage(),"Se debe definir un color primario antes de elegir uno secundario");
		}
	}

}
