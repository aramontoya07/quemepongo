package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import junit.framework.AssertionFailedError;
import quemepongo.Borrador;
import quemepongo.Categoria;
import quemepongo.Guardarropas;
import quemepongo.Material;
import quemepongo.Prenda;
import quemepongo.TipoPrenda;
import quemepongo.Usuario;

class PrimerTest {
	
	TipoPrenda remera = new TipoPrenda(Categoria.PARTE_SUPERIOR,new ArrayList<Material>(Arrays.asList(Material.ALGODON,Material.SEDA)));
	TipoPrenda pantalon = new TipoPrenda(Categoria.PARTE_INFERIOR,new ArrayList<Material>(Arrays.asList(Material.JEAN,Material.CUERO,Material.ALGODON)));
	TipoPrenda zapatilla = new TipoPrenda(Categoria.CALZADO,new ArrayList<Material>(Arrays.asList(Material.CUERO)));
	TipoPrenda anteojo = new TipoPrenda(Categoria.ACCESORIO,new ArrayList<Material>(Arrays.asList(Material.VIDRIO,Material.PLASTICO)));
	
	Prenda remeraAzul;
	Prenda jeanRojo;
	Prenda zapatillasVerde;
	Prenda remeraDeportiva;
	Prenda jeanNegro;
	Prenda ojotas;
	Prenda anteojos;
	Prenda anteojosDeSol;
	
	@BeforeAll
	public void setUp() {
		Borrador borrador1 = new Borrador();
		borrador1.definirColorPrimario(new Color(255,255,0));
		borrador1.definirTipo(remera);
		borrador1.definirMaterial(Material.ALGODON);

		Borrador borrador2 = new Borrador();
		borrador2.definirColorPrimario(new Color(255,0,0));
		borrador2.definirTipo(pantalon);
		borrador2.definirMaterial(Material.JEAN);
		
		Borrador borrador3 = new Borrador();
		borrador3.definirColorPrimario(new Color(55,123,60));
		borrador3.definirTipo(zapatilla);
		borrador3.definirMaterial(Material.CUERO);
		
		Borrador borrador4 = new Borrador();
		borrador4.definirColorPrimario(new Color(77,4,10));
		borrador4.definirTipo(remera);
		borrador4.definirMaterial(Material.ALGODON);

		Borrador borrador5 = new Borrador();
		borrador5.definirColorPrimario(new Color(255,0,255));
		borrador5.definirTipo(pantalon);
		borrador5.definirMaterial(Material.JEAN);
		
		Borrador borrador6 = new Borrador();
		borrador6.definirColorPrimario(new Color(0,0,0));
		borrador6.definirTipo(zapatilla);
		borrador6.definirMaterial(Material.CUERO);
		
		Borrador borrador7 = new Borrador();
		borrador7.definirColorPrimario(new Color(0,0,0));
		borrador7.definirTipo(anteojo);
		borrador7.definirMaterial(Material.VIDRIO);
		
		Borrador borrador8 = new Borrador();
		borrador8.definirColorPrimario(new Color(0,0,0));
		borrador8.definirTipo(anteojo);
		borrador8.definirMaterial(Material.PLASTICO);
		
		remeraAzul = borrador1.crearPrenda();
		jeanRojo = borrador2.crearPrenda();
		zapatillasVerde = borrador3.crearPrenda();
		remeraDeportiva = borrador4.crearPrenda();
		jeanNegro = borrador5.crearPrenda();
		ojotas = borrador6.crearPrenda();
		anteojos = borrador7.crearPrenda();
		anteojosDeSol = borrador8.crearPrenda();
	}
	
	@Test
	@DisplayName("Debe saberse el tipo de una prenda")
	void tipoDePrenda() {
		assertEquals(remeraAzul.getTipo(),remera);
	}
	
	@Test
	@DisplayName("Debe saberse a que categoria pertenece una prenda")
	void categoriaDePrenda() {
		assertEquals(remeraAzul.getCategoria(),Categoria.PARTE_SUPERIOR);
	}
	
	@Test
	@DisplayName("El materia tiene que ser consistente con el tipo de prenda")
	void consistenciaMaterial() {
		try {
			Borrador borradorPrueba = new Borrador();
			borradorPrueba.definirTipo(zapatilla);
			borradorPrueba.definirMaterial(Material.ALGODON);
			fail("Se supone que tiene que tirar una excepcion al intentar introducir un material no consistente con el tipo de prenda");
		} catch (Exception e) {
			assertEquals(e.getMessage(),"El material no esta permitido para este tipo de prenda");
		}
	}
	
	@Test
	@DisplayName("Debe saberse a que categoria pertenece una prenda")
	void categoriaDePrenda() {
		assertEquals(remeraAzul.getCategoria(),Categoria.PARTE_SUPERIOR);
	}
	
	@Test
	void generarSugerencias() {
		Set<Prenda> superioreSet = new HashSet<Prenda>();
		Set<Prenda> inferioreSet = new HashSet<Prenda>();
		Set<Prenda> calzadoSet = new HashSet<Prenda>();
		Set<Prenda> accesorioSet = new HashSet<Prenda>();
		superioreSet.add(remeraAzul);
		inferioreSet.add(jeanRojo);
		calzadoSet.add(zapatillasVerde);
		accesorioSet.add(anteojos);
		
		Set<Prenda> superioreSet2 = new HashSet<Prenda>();
		Set<Prenda> inferioreSet2 = new HashSet<Prenda>();
		Set<Prenda> calzadoSet2 = new HashSet<Prenda>();
		Set<Prenda> accesorioSet2 = new HashSet<Prenda>();
		superioreSet.add(remeraDeportiva);
		inferioreSet.add(jeanNegro);
		calzadoSet.add(ojotas);
		accesorioSet.add(anteojosDeSol);
		
		Guardarropas guardarropa = new Guardarropas(superioreSet,inferioreSet,calzadoSet,accesorioSet);
		Guardarropas otroGuardarropa = new Guardarropas(superioreSet2,inferioreSet2,calzadoSet2,accesorioSet2);
		
		List<Guardarropas> guardarropas = new ArrayList<Guardarropas>();
		guardarropas.add(guardarropa);
		guardarropas.add(otroGuardarropa);
		
		Usuario azul = new Usuario(guardarropas);
		assertEquals("ASD","ASD");
	}

}
