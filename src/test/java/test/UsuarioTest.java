package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import atuendo.Atuendo;
import clima.MockCalor;
import clima.MockFrio;
import excepciones.AgregarPrendaException;
import prenda.Borrador;
import prenda.Categoria;
import prenda.ColorRGB;
import prenda.Material;
import prenda.Prenda;
import prenda.TipoPrenda;
import prenda.TipoUso;
import usuario.Guardarropas;
import usuario.Usuario;

class UsuarioTest {
	TipoPrenda remera = new TipoPrenda(Categoria.PARTE_SUPERIOR,new ArrayList<Material>(Arrays.asList(Material.ALGODON,Material.SEDA)),10,TipoUso.PRIMARIA);
	TipoPrenda pantalon = new TipoPrenda(Categoria.PARTE_INFERIOR,new ArrayList<Material>(Arrays.asList(Material.JEAN,Material.CUERO,Material.ALGODON)),10,TipoUso.PRIMARIA);
	TipoPrenda zapatilla = new TipoPrenda(Categoria.CALZADO,new ArrayList<Material>(Arrays.asList(Material.CUERO)),10,TipoUso.PRIMARIA);
	TipoPrenda anteojo = new TipoPrenda(Categoria.ACCESORIO,new ArrayList<Material>(Arrays.asList(Material.VIDRIO,Material.PLASTICO)),0,TipoUso.SECUNDARIA);
	TipoPrenda campera = new TipoPrenda(Categoria.PARTE_SUPERIOR,new ArrayList<Material>(Arrays.asList(Material.ALGODON,Material.SEDA)), 20, TipoUso.SECUNDARIA);
	
	Borrador borrador_remeraAzul = new Borrador();
	Borrador borrador_jeanRojo = new Borrador();
	Borrador borrador_zapatilla = new Borrador();
	Borrador borrador_remeraDeportiva = new Borrador();
	Borrador borrador_jeanNegro = new Borrador();
	Borrador borrador_ojotas= new Borrador();
	Borrador borrador_anteojos = new Borrador();
	Borrador borrador_anteojosDeSol = new Borrador();

    
    Set<Prenda> prendasGlobales = new HashSet<Prenda>();
    
	
    Guardarropas guardarropa = new Guardarropas();
    Guardarropas otroGuardarropa = new Guardarropas();
    
    Usuario pedro = new Usuario();
    
	
	@BeforeEach
	public void setUp(){
		remera.setTiposAceptados(new ArrayList<TipoPrenda>(Arrays.asList(campera)));
		
		borrador_remeraAzul.crearBorrador(new ColorRGB(255,255,0),remera,Material.ALGODON);
		borrador_jeanRojo.crearBorrador(new ColorRGB(255,0,0),pantalon,Material.JEAN);
		borrador_zapatilla.crearBorrador(new ColorRGB(55,123,60),zapatilla,Material.CUERO);
		borrador_remeraDeportiva.crearBorrador(new ColorRGB(77,4,10),remera,Material.ALGODON);
		borrador_jeanNegro.crearBorrador(new ColorRGB(255,0,255),pantalon,Material.JEAN);
		borrador_ojotas.crearBorrador(new ColorRGB(0,0,0),zapatilla,Material.CUERO);
		borrador_anteojos.crearBorrador(new ColorRGB(0,0,0),anteojo,Material.VIDRIO);
		borrador_anteojosDeSol.crearBorrador(new ColorRGB(0,0,0),anteojo,Material.PLASTICO);
		
		Prenda remeraAzul = borrador_remeraAzul.crearPrenda();
		Prenda remeraDeportiva = borrador_remeraDeportiva.crearPrenda();
		Prenda jeanRojo = borrador_jeanRojo.crearPrenda();
		Prenda jeanNegro = borrador_jeanNegro.crearPrenda();
		Prenda ojotas = borrador_ojotas.crearPrenda();
		Prenda zapatillasVerde = borrador_zapatilla.crearPrenda();
		Prenda anteojos = borrador_anteojos.crearPrenda();
		Prenda anteojosDeSol = borrador_anteojosDeSol.crearPrenda();
		
		prendasGlobales.add(remeraAzul);
		prendasGlobales.add(remeraDeportiva);
		prendasGlobales.add(jeanNegro);
		prendasGlobales.add(jeanRojo);
		prendasGlobales.add(ojotas);
		prendasGlobales.add(zapatillasVerde);
		prendasGlobales.add(anteojos);
		prendasGlobales.add(anteojosDeSol);
	}
	

	@Test
	@DisplayName("Las sugerencias de prenda deben ser validas")
	void generarSugerencias() {
		pedro.agregarGuardarropa(guardarropa);
		pedro.actualizarSubscripcion();
		pedro.agregarPrendas(guardarropa, prendasGlobales);
		List<Atuendo> listaSugerencias = pedro.pedirSugerencia();
        assertTrue(listaSugerencias.stream().allMatch(sugerencia -> sugerencia.esAtuendoValido(sugerencia)));
	}
	
	@Test
	@DisplayName("Se deben generar todas las combinaciones posibles de ropa")
	void contarSugerencias(){
		//#FIXME Testear esto es medio raro
		pedro.agregarGuardarropa(guardarropa);
		pedro.actualizarSubscripcion();
		pedro.agregarPrendas(guardarropa, prendasGlobales);
		List<Atuendo> listaSugerencias = pedro.pedirSugerencia();
		assertEquals(32,listaSugerencias.size());
	}
	
	@Test
	@DisplayName("Al tener una solucion gratuita no se puede agregar mas de 5 prendas")
	void maximoPrendasConGratuidas() {
		assertThrows(AgregarPrendaException.class, ()-> {
			pedro.agregarGuardarropa(guardarropa);
			pedro.agregarPrendas(guardarropa, prendasGlobales);
		});
	}
	
	@Test
	@DisplayName("Al tener una solucion premium se puede agregar mas de 5 prendas")
	void maximoPrendasConPremium() {
		pedro.actualizarSubscripcion();
		pedro.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasGlobales);
		
		assertEquals(8, guardarropa.cantidadDePrendas());
	}
	
	@Test
	@DisplayName("Devuelve sugerencias aptas para un clima frio")
	void prendasParaFrio() {
		pedro.actualizarSubscripcion();
		pedro.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasGlobales);
		List<Atuendo> listaSugerencias = pedro.pedirSugerenciaSegunClima(new MockFrio());
		assertTrue(listaSugerencias.stream().allMatch(sugerencia -> sugerencia.soportaClima(new MockFrio().obtenerClima())));
	}
	
	@Test
	@DisplayName("Devuelve sugerencias aptas para un clima calido")
	void prendasParaCalor() {
		pedro.actualizarSubscripcion();
		pedro.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasGlobales);
		List<Atuendo> listaSugerencias = pedro.pedirSugerenciaSegunClima(new MockCalor());
		assertTrue(listaSugerencias.stream().allMatch(sugerencia -> sugerencia.soportaClima(new MockCalor().obtenerClima())));
	}
}
