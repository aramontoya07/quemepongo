package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import quemepongo.Atuendo;
import quemepongo.Borrador;
import quemepongo.Categoria;
import quemepongo.Guardarropas;
import quemepongo.Material;
import quemepongo.Prenda;
import quemepongo.TipoPrenda;
import quemepongo.Usuario;

class PrimerTest {
	
	static TipoPrenda remera = new TipoPrenda(Categoria.PARTE_SUPERIOR,new ArrayList<Material>(Arrays.asList(Material.ALGODON,Material.SEDA)));
	static TipoPrenda pantalon = new TipoPrenda(Categoria.PARTE_INFERIOR,new ArrayList<Material>(Arrays.asList(Material.JEAN,Material.CUERO,Material.ALGODON)));
	static TipoPrenda zapatilla = new TipoPrenda(Categoria.CALZADO,new ArrayList<Material>(Arrays.asList(Material.CUERO)));
	static TipoPrenda anteojo = new TipoPrenda(Categoria.ACCESORIO,new ArrayList<Material>(Arrays.asList(Material.VIDRIO,Material.PLASTICO)));
	
	static Prenda remeraAzul;
	static Prenda jeanRojo;
	static Prenda zapatillasVerde;
	static Prenda remeraDeportiva;
	static Prenda jeanNegro;
	static Prenda ojotas;
	static Prenda anteojos;
	static Prenda anteojosDeSol;
	
	static Usuario pedro;
	
	public static Borrador crearBorrador(Color colorPrimario, TipoPrenda tipo, Material material) {
		Borrador borrador = new Borrador();
		borrador.definirColorPrimario(colorPrimario);
		borrador.definirTipo(tipo);
		borrador.definirMaterial(material);
		return borrador;
	}
	
	public static boolean esAtuendoValido(Atuendo atuendo){
		return 
				atuendo.getSuperior().getCategoria() == Categoria.PARTE_SUPERIOR &&
				atuendo.getInferior().getCategoria() == Categoria.PARTE_INFERIOR &&
				atuendo.getCalzado().getCategoria() == Categoria.CALZADO &&
				atuendo.getAccesorio().getCategoria() == Categoria.ACCESORIO;
	}
	
	@BeforeAll
	public static void setUp(){
		Borrador borrador1 = crearBorrador(new Color(255,255,0),remera,Material.ALGODON);
		Borrador borrador2 = crearBorrador(new Color(255,0,0),pantalon,Material.JEAN);
		Borrador borrador3 = crearBorrador(new Color(55,123,60),zapatilla,Material.CUERO);
		Borrador borrador4 = crearBorrador(new Color(77,4,10),remera,Material.ALGODON);
		Borrador borrador5 = crearBorrador(new Color(255,0,255),pantalon,Material.JEAN);
		Borrador borrador6 = crearBorrador(new Color(0,0,0),zapatilla,Material.CUERO);
		Borrador borrador7 = crearBorrador(new Color(0,0,0),anteojo,Material.VIDRIO);
		Borrador borrador8 = crearBorrador(new Color(0,0,0),anteojo,Material.PLASTICO);
		
		remeraAzul = borrador1.crearPrenda();
		jeanRojo = borrador2.crearPrenda();
		zapatillasVerde = borrador3.crearPrenda();
		remeraDeportiva = borrador4.crearPrenda();
		jeanNegro = borrador5.crearPrenda();
		ojotas = borrador6.crearPrenda();
		anteojos = borrador7.crearPrenda();
		anteojosDeSol = borrador8.crearPrenda();
		
		remeraAzul.setNombre("remera azul");
		jeanRojo.setNombre("jean rojo");
		zapatillasVerde.setNombre("zapatillasVerde");
		remeraDeportiva.setNombre("remeraDeportiva");
		jeanNegro.setNombre("jeanNegro");
		ojotas.setNombre("ojotas");
		anteojos.setNombre("anteojos");
		anteojosDeSol.setNombre("anteojos sol");
		
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
		superioreSet2.add(remeraDeportiva);
		inferioreSet2.add(jeanNegro);
		calzadoSet2.add(ojotas);
		accesorioSet2.add(anteojosDeSol);
		
		Guardarropas guardarropa = new Guardarropas(superioreSet,inferioreSet,calzadoSet,accesorioSet);
		Guardarropas otroGuardarropa = new Guardarropas(superioreSet2,inferioreSet2,calzadoSet2,accesorioSet2);
		
		List<Guardarropas> guardarropas = new ArrayList<Guardarropas>();
		guardarropas.add(guardarropa);
		guardarropas.add(otroGuardarropa);
		
		pedro = new Usuario(guardarropas);
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
			crearBorrador(new Color(255,255,0),zapatilla,Material.ALGODON);
			fail("Deberia tirar una excepcion al intentar introducir un material no consistente con el tipo de prenda");
		} catch (Exception e) {
			assertEquals(e.getMessage(),"El material no esta permitido para este tipo de prenda");
		}
	}
	
	@Test
	@DisplayName("La prenda debe tener un color primario y opcionalmente un color secundario DISTINTO")
	void coloresDistintos() {
		try {
			Borrador borradorPrueba = new Borrador();
			borradorPrueba.definirColorPrimario(new Color(100,100,100));
			borradorPrueba.definirColorSecundario(new Color(100,100,100));
			fail("Deberia tirar una excepcion al introducir dos colores iguales");
		} catch (Exception e) {
			assertEquals(e.getMessage(),"El color secundario debe diferir del primario");
		}
	}
	
	@Test
	@DisplayName("Las sugerencias de prenda deben ser validas")
	void generarSugerencias() {
		List<Atuendo> listaSugerencias = pedro.pedirSugerencia();
        assert(listaSugerencias.stream().allMatch(sugerencia -> esAtuendoValido(sugerencia)));
	}
	
	@Test
	@DisplayName("Se deben generar todas las combinaciones posibles de ropa")
	void contarSugerencias(){
		List<Atuendo> listaSugerencias = pedro.pedirSugerencia();
		assertEquals(2,listaSugerencias.size());
	}
}
