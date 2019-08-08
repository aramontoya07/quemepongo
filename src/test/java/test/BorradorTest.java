package test;

import static org.junit.jupiter.api.Assertions.*;

import excepciones.BorradorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import prenda.Borrador;
import prenda.ColorRGB;
import prenda.Material;

class BorradorTest extends SetUp{

	@BeforeEach
	private void setUp() {
		setear();
	}

	@Test
	@DisplayName("La prenda debe tener un color primario y opcionalmente un color secundario DISTINTO")
	void coloresDistintos() {
		assertThrows(BorradorException.class, () -> {
			Borrador borradorPrueba = new Borrador();
			borradorPrueba.definirColorPrimario(new ColorRGB(100, 100, 100));
			borradorPrueba.definirColorSecundario(new ColorRGB(100, 100, 100));
		});
	}

	@Test
	@DisplayName("Para definir un color secundario primero se debe definir uno primario")
	void ordenColores() {
		assertThrows(BorradorException.class, () -> {
			Borrador prueba = new Borrador();
			prueba.definirColorSecundario(new ColorRGB(255, 9, 0));
		});
	}

	@Test
	@DisplayName("Antes de definir un material se debe definir los materiales que acepta el tipo de la prenda") //se debe definir el tipo prenda
	void materialesAntes() {
		assertThrows(BorradorException.class, () -> {
			Borrador prueba = new Borrador();
			prueba.definirMaterial(Material.ALGODON);
		});
	}

	@Test
	@DisplayName("El material de la prenda debe ser coherente con su tipo")
	void materialCoherenteConTipoPrenda() {
		assertThrows(BorradorException.class, () -> {
			Borrador prueba = new Borrador();
			prueba.definirTipo(remera);
			prueba.definirMaterial(Material.CUERO);
		});
	}

	@Test
	@DisplayName("El tipo es obligatorio")
	void tipoPrendaObligatorio() {
		assertThrows(BorradorException.class, () -> {
			Borrador prueba = new Borrador();
			prueba.definirColorPrimario(new ColorRGB(255, 9, 0));
			prueba.crearPrenda();
		});
	}

	@Test
	@DisplayName("El color primario es obligatorio")
	void colorPrimarioObligatorio() {
		assertThrows(BorradorException.class, () -> {
			Borrador prueba = new Borrador();
			prueba.definirTipo(remera);
			prueba.definirMaterial(Material.ALGODON);
			prueba.crearPrenda();
		});
	}

	@Test
	@DisplayName("El material es obligatorio")
	void materialObligatorio() {
		assertThrows(BorradorException.class, () -> {
			Borrador prueba = new Borrador();
			prueba.definirTipo(remera);
			prueba.definirColorPrimario(new ColorRGB(255, 9, 0));
			prueba.crearPrenda();
		});
	}	
}
