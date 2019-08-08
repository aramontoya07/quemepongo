package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import atuendo.*;
import clima.*;
import excepciones.*;
import prenda.Prenda;
import usuario.AdaptacionPuntuada;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class UsuarioTest extends SetUp {

	@BeforeEach
	private void setUp() {
		setear();
	}


	@Test
	@DisplayName("Se deben generar todas las combinaciones posibles de ropa")
	void contarSugerencias(){
		pedro.agregarGuardarropa(guardarropa);
		pedro.actualizarSubscripcion();
		pedro.agregarPrendas(guardarropa, prendasGlobales);
		Set<Atuendo> listaSugerencias = pedro.pedirSugerencia();
		assertEquals(48, listaSugerencias.size());
	}

	@Test
	@DisplayName("Al tener una suscripcion gratuita no se puede agregar mas de 5 prendas")
	void maximoPrendasConGratuidas() {
		assertThrows(AgregarPrendaException.class, () -> {
			pedro.agregarGuardarropa(guardarropa);
			pedro.agregarPrendas(guardarropa, prendasGlobales);
		});
	}

	@Test
	@DisplayName("Al tener una suscripcion premium se puede agregar mas de 5 prendas")
	void maximoPrendasConPremium() {
		pedro.agregarGuardarropa(guardarropa);
		pedro.actualizarSubscripcion();
		pedro.agregarPrendas(guardarropa, prendasGlobales);

		assertEquals(9, guardarropa.cantidadDePrendas());
	}

	@Test
	@DisplayName("Devuelve sugerencias aptas para un clima frio")
	void prendasParaFrio() {
		pedro.agregarGuardarropa(guardarropa);
		pedro.actualizarSubscripcion();
		pedro.agregarPrendas(guardarropa, prendasGlobales);
		
		ServicioClimatico.definirProvedor(new MockFrio());
		
		Set<SugerenciasClima> listaSugerencias = pedro.pedirSugerenciaSegunClima( "London");
		assertTrue(listaSugerencias.stream()
				.allMatch(sugerencia -> sugerencia.esAptaParaClima(new MockFrio().obtenerClima("London"))));
	}

	@Test
	@DisplayName("Devuelve sugerencias aptas para un clima calido")
	void prendasParaCalor() {
		pedro.actualizarSubscripcion();
		pedro.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasGlobales);
		
		ServicioClimatico.definirProvedor(new MockCalor());
		
		Set<SugerenciasClima> listaSugerencias = pedro.pedirSugerenciaSegunClima("Palermo");
		assertTrue(listaSugerencias.stream()
				.allMatch(sugerencia -> sugerencia.esAptaParaClima(new MockCalor().obtenerClima("Palermo"))));
	}

	boolean chequearAbrigoEn(int posicion, int abrigoBuscado, SugerenciasClima sugerencia){
		return sugerencia.getAproximadas().get(posicion).nivelAbrigo() == abrigoBuscado;
	}

	@Test
	@DisplayName("Ordena segun las preferencias")
	void prendasOrdenadas(){
		pedro.actualizarSubscripcion();
		guardarropa.setMargenDePrendasAproximadas(1000);
		pedro.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasOrdenables);
		ServicioClimatico.definirProvedor(new MockAgradable());
		SugerenciasClima sugerencia = new ArrayList<>(pedro.pedirSugerenciaSegunClima("Bokita el mas grande")).get(0);
		assertTrue(chequearAbrigoEn(0,30, sugerencia) &&
				chequearAbrigoEn(1,12, sugerencia) &&
				chequearAbrigoEn(2,0, sugerencia));
	}

	@Test
	@DisplayName("Dos usuarios pueden compartir guardarropa")
	void guardarropaCompartido(){

	}
}