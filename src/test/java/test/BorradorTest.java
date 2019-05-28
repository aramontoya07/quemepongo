package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import excepciones.ColorSecundarioIgualAPrimarioException;
import excepciones.ColorSecundarioSinPrimarioException;
import prenda.Borrador;
import prenda.ColorRGB;

class BorradorTest {
	
	@Test
	@DisplayName("La prenda debe tener un color primario y opcionalmente un color secundario DISTINTO")
	void coloresDistintos() {
		assertThrows(ColorSecundarioIgualAPrimarioException.class, () -> {
			Borrador borradorPrueba = new Borrador();
			borradorPrueba.definirColorPrimario(new ColorRGB(100,100,100));
			borradorPrueba.definirColorSecundario(new ColorRGB(100,100,100));
	    });
	}

	@Test
	@DisplayName("Para definir un color secundario primero se debe definir uno primario")
	void ordenColores() {
		assertThrows(ColorSecundarioSinPrimarioException.class, () -> {
			Borrador prueba = new Borrador();
			prueba.definirColorSecundario(new ColorRGB(255,9,0));
        });
	}

}
