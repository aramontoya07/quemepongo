package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import atuendo.Atuendo;
import dominio.Guardarropas;
import dominio.Usuario;
import prenda.Borrador;
import prenda.Categoria;
import prenda.ColorRGB;
import prenda.Material;
import prenda.Prenda;
import prenda.TipoPrenda;

class UsuarioTest {
	TipoPrenda remera = new TipoPrenda(Categoria.PARTE_SUPERIOR,new ArrayList<Material>(Arrays.asList(Material.ALGODON,Material.SEDA)),10,true);
	TipoPrenda pantalon = new TipoPrenda(Categoria.PARTE_INFERIOR,new ArrayList<Material>(Arrays.asList(Material.JEAN,Material.CUERO,Material.ALGODON)),10,true);
	TipoPrenda zapatilla = new TipoPrenda(Categoria.CALZADO,new ArrayList<Material>(Arrays.asList(Material.CUERO)),10,true);
	TipoPrenda anteojo = new TipoPrenda(Categoria.ACCESORIO,new ArrayList<Material>(Arrays.asList(Material.VIDRIO,Material.PLASTICO)),0,false);
	
	Borrador borrador_remeraAzul = new Borrador();
	Borrador borrador_jeanRojo = new Borrador();
	Borrador borrador_zapatilla = new Borrador();
	Borrador borrador_remeraDeportiva = new Borrador();
	Borrador borrador_jeanNegro = new Borrador();
	Borrador borrador_ojotas= new Borrador();
	Borrador borrador_anteojos = new Borrador();
	Borrador borrador_anteojosDeSol = new Borrador();

	Prenda remeraAzul;
	Prenda jeanRojo;
	Prenda zapatillasVerde;
	Prenda remeraDeportiva;
	Prenda jeanNegro;
	Prenda ojotas;
	Prenda anteojos;
	Prenda anteojosDeSol;
	
    Usuario pedro;
	
	@BeforeEach
	public void setUp(){
		
		borrador_remeraAzul.crearBorrador(new ColorRGB(255,255,0),remera,Material.ALGODON);
		borrador_jeanRojo.crearBorrador(new ColorRGB(255,0,0),pantalon,Material.JEAN);
		borrador_zapatilla.crearBorrador(new ColorRGB(55,123,60),zapatilla,Material.CUERO);
		borrador_remeraDeportiva.crearBorrador(new ColorRGB(77,4,10),remera,Material.ALGODON);
		borrador_jeanNegro.crearBorrador(new ColorRGB(255,0,255),pantalon,Material.JEAN);
		borrador_ojotas.crearBorrador(new ColorRGB(0,0,0),zapatilla,Material.CUERO);
		borrador_anteojos.crearBorrador(new ColorRGB(0,0,0),anteojo,Material.VIDRIO);
		borrador_anteojosDeSol.crearBorrador(new ColorRGB(0,0,0),anteojo,Material.PLASTICO);
		
		remeraAzul = borrador_remeraAzul.crearPrenda();
		jeanRojo = borrador_jeanRojo.crearPrenda();
		zapatillasVerde = borrador_zapatilla.crearPrenda();
		remeraDeportiva = borrador_remeraDeportiva.crearPrenda();
		jeanNegro = borrador_jeanNegro.crearPrenda();
		ojotas = borrador_ojotas.crearPrenda();
		anteojos = borrador_anteojos.crearPrenda();
		anteojosDeSol = borrador_anteojosDeSol.crearPrenda();
		
		Set<Prenda> superioreSet = new HashSet<Prenda>();
		Set<Prenda> inferioreSet = new HashSet<Prenda>();
		Set<Prenda> calzadoSet = new HashSet<Prenda>();
		Set<Prenda> accesorioSet = new HashSet<Prenda>();
		superioreSet.add(remeraDeportiva);
		superioreSet.add(remeraAzul);
		inferioreSet.add(jeanRojo);
		calzadoSet.add(zapatillasVerde);
		accesorioSet.add(anteojos);
		
		Set<Prenda> superioreSet2 = new HashSet<>();
		Set<Prenda> inferioreSet2 = new HashSet<Prenda>();
		Set<Prenda> calzadoSet2 = new HashSet<Prenda>();
		Set<Prenda> accesorioSet2 = new HashSet<Prenda>();
		superioreSet2.add(remeraAzul);
		superioreSet2.add(remeraDeportiva);
		inferioreSet2.add(jeanNegro);
		calzadoSet2.add(ojotas);
		accesorioSet2.add(anteojosDeSol);
		
		Guardarropas guardarropa = new Guardarropas(superioreSet,inferioreSet,calzadoSet,accesorioSet);
		Guardarropas otroGuardarropa = new Guardarropas(superioreSet2,inferioreSet2,calzadoSet2,accesorioSet2);
		pedro = new Usuario(Arrays.asList(guardarropa, otroGuardarropa));
	}
	
	@Disabled
	@Test
	@DisplayName("Las sugerencias de prenda deben ser validas")
	void generarSugerencias() {
		List<Atuendo> listaSugerencias = pedro.pedirSugerencia();
        assertTrue(listaSugerencias.stream().allMatch(sugerencia -> sugerencia.esAtuendoValido(sugerencia)));
	}
	
	@Test
	@DisplayName("Se deben generar todas las combinaciones posibles de ropa")
	void contarSugerencias(){
		List<Atuendo> listaSugerencias = pedro.pedirSugerencia();
		assertEquals(4,listaSugerencias.size());
	}
}
