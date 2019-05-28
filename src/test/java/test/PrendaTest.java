package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import prenda.Borrador;
import prenda.Categoria;
import prenda.ColorRGB;
import prenda.Material;
import prenda.Prenda;
import prenda.TipoPrenda;

public class PrendaTest {
	TipoPrenda remera = new TipoPrenda(Categoria.PARTE_SUPERIOR,new ArrayList<Material>(Arrays.asList(Material.ALGODON,Material.SEDA)), 10, true);
	TipoPrenda campera = new TipoPrenda(Categoria.PARTE_SUPERIOR,new ArrayList<Material>(Arrays.asList(Material.ALGODON,Material.SEDA)), 20, false);
	TipoPrenda pantalon = new TipoPrenda(Categoria.PARTE_INFERIOR,new ArrayList<Material>(Arrays.asList(Material.JEAN,Material.CUERO,Material.ALGODON)), 10, true);
	TipoPrenda zapatilla = new TipoPrenda(Categoria.CALZADO,new ArrayList<Material>(Arrays.asList(Material.CUERO)), 10, true);
	TipoPrenda anteojo = new TipoPrenda(Categoria.ACCESORIO,new ArrayList<Material>(Arrays.asList(Material.VIDRIO,Material.PLASTICO)), 0, false);
	
	Borrador borrador_remeraAzul = new Borrador();
	Borrador borrador_jeanRojo = new Borrador();
	Borrador borrador_zapatillas = new Borrador();
	Borrador borrador_anteojosDeSol = new Borrador();

	Prenda remeraAzul;
	Prenda jeanRojo;
	Prenda zapatillas;
	Prenda anteojosDeSol;

	@BeforeEach
	void crearPrendas() {
		remera.setTiposAceptados(new ArrayList<TipoPrenda>(Arrays.asList(campera)));
		
		borrador_remeraAzul.crearBorrador(new ColorRGB(255,255,0),remera,Material.ALGODON);
		borrador_jeanRojo.crearBorrador(new ColorRGB(255,0,0),pantalon,Material.JEAN);
		borrador_anteojosDeSol.crearBorrador(new ColorRGB(0,0,0),anteojo,Material.PLASTICO);
		borrador_zapatillas.crearBorrador(new ColorRGB(55,123,60),zapatilla,Material.CUERO);
		
		remeraAzul = borrador_remeraAzul.crearPrenda();
		jeanRojo = borrador_jeanRojo.crearPrenda();
		anteojosDeSol = borrador_anteojosDeSol.crearPrenda();
		zapatillas = borrador_zapatillas.crearPrenda();
	}
	
	@Test
	@DisplayName("Debe saberse el tipo de una prenda")
	void tipoDePrenda() {
		assertEquals(remeraAzul.getTipo(),remera);
	}
	
	@Test
	@DisplayName("Debe saberse a que categoria pertenece una prenda")
	void categoriaDePrenda() {
		assertTrue(remeraAzul.esDeCategoria(Categoria.PARTE_SUPERIOR));
	}
}
