package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import excepciones.ColorPrimarioObligatorioException;
import excepciones.ColorSecundarioIgualAPrimarioException;
import excepciones.ColorSecundarioSinPrimarioException;
import excepciones.MaterialAntesQueTipoPrendaException;
import excepciones.MaterialObligatorioException;
import excepciones.TipoPrendaObligatorioException;
import prenda.Borrador;
import prenda.Categoria;
import prenda.ColorRGB;
import prenda.Material;
import prenda.TipoPrenda;
import prenda.TipoUso;

class BorradorTest {

	TipoPrenda remera = new TipoPrenda(Categoria.PARTE_SUPERIOR,
			new ArrayList<Material>(Arrays.asList(Material.ALGODON, Material.SEDA)), 10, TipoUso.PRIMARIA);

	@Test
	@DisplayName("La prenda debe tener un color primario y opcionalmente un color secundario DISTINTO")
	void coloresDistintos() {
		assertThrows(ColorSecundarioIgualAPrimarioException.class, () -> {
			Borrador borradorPrueba = new Borrador();
			borradorPrueba.definirColorPrimario(new ColorRGB(100, 100, 100));
			borradorPrueba.definirColorSecundario(new ColorRGB(100, 100, 100));
		});
	}

	@Test
	@DisplayName("Para definir un color secundario primero se debe definir uno primario")
	void ordenColores() {
		assertThrows(ColorSecundarioSinPrimarioException.class, () -> {
			Borrador prueba = new Borrador();
			prueba.definirColorSecundario(new ColorRGB(255, 9, 0));
		});
	}

	@Test
	@DisplayName("Antes de definir un material se debe definir los materiales que acepta el tipo de la prenda")
	void materialesAntes() {
		assertThrows(MaterialAntesQueTipoPrendaException.class, () -> {
			Borrador prueba = new Borrador();
			prueba.definirMaterial(Material.ALGODON);
		});
	}

	@Test
	@DisplayName("El tipo es obligatorio")
	void tipoPrendaObligatorio() {
		assertThrows(TipoPrendaObligatorioException.class, () -> {
			Borrador prueba = new Borrador();
			prueba.definirColorPrimario(new ColorRGB(255, 9, 0));
			prueba.crearPrenda();
		});
	}

	@Test
	@DisplayName("El color primario es obligatorio")
	void colorPrimarioObligatorio() {
		assertThrows(ColorPrimarioObligatorioException.class, () -> {
			Borrador prueba = new Borrador();
			prueba.definirTipo(remera);
			prueba.definirMaterial(Material.ALGODON);
			prueba.crearPrenda();
		});
	}

	@Test
	@DisplayName("El materla es obligatorio")
	void materialObligatorio() {
		assertThrows(MaterialObligatorioException.class, () -> {
			Borrador prueba = new Borrador();
			prueba.definirTipo(remera);
			prueba.definirColorPrimario(new ColorRGB(255, 9, 0));
			prueba.crearPrenda();
		});
	}
}
