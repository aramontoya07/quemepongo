package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import atuendo.*;
import clima.*;
import excepciones.*;

import java.util.Set;

class UsuarioTest extends SetUp {

	@BeforeEach
	private void setUp() {
		setear();
	}


	@Test
	@DisplayName("Se deben generar todas las combinaciones posibles de ropa")
	void contarSugerencias(){
		pedro.agregarGuardarropa(guardarropa);
		pedro.actualizarSubscripcionAPremium();
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
		pedro.actualizarSubscripcionAPremium();
		pedro.agregarPrendas(guardarropa, prendasGlobales);

		assertEquals(9, guardarropa.cantidadDePrendas());
	}

	@Test
	@DisplayName("Devuelve sugerencias aptas para un clima frio")
	void prendasParaFrio() {
		pedro.agregarGuardarropa(guardarropa);
		pedro.actualizarSubscripcionAPremium();
		pedro.agregarPrendas(guardarropa, prendasGlobales);
		
		ServicioClimatico.definirProvedor(new MockFrio());
		
		Set<SugerenciasClima> listaSugerencias = pedro.pedirSugerenciaSegunClima( "London");
		assertTrue(listaSugerencias.stream()
				.allMatch(sugerencia -> sugerencia.esAptaParaClima(new MockFrio().obtenerClima("London"))));
	}

	@Test
	@DisplayName("Devuelve sugerencias aptas para un clima calido")
	void prendasParaCalor() {
		pedro.actualizarSubscripcionAPremium();
		pedro.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasGlobales);
		
		ServicioClimatico.definirProvedor(new MockCalor());
		
		Set<SugerenciasClima> listaSugerencias = pedro.pedirSugerenciaSegunClima("Palermo");
		assertTrue(listaSugerencias.stream()
				.allMatch(sugerencia -> sugerencia.esAptaParaClima(new MockCalor().obtenerClima("Palermo"))));
	}

	@Test
	@DisplayName("Dos usuarios pueden compartir guardarropa")
	void guardarropaCompartido(){
		pedro.actualizarSubscripcionAPremium();
		pedro2.actualizarSubscripcionAPremium();
		pedro.agregarGuardarropa(guardarropa);
		pedro2.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasGlobales);

		assertEquals(pedro.getGuardarropas(),pedro2.getGuardarropas());
	}

	@Test
	@DisplayName("Cuando un usuario acepta una sugerencia, las prendas se marcan como usadas en el guardarropa")
	void prendasUsadas(){
		pedro.actualizarSubscripcionAPremium();
		pedro.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasJustito);

		Set<Atuendo> sugerenciasPedro = pedro.pedirSugerencia();
		Atuendo atuendo = sugerenciasPedro.stream().findFirst().get();
		pedro.aceptarAtuendo(atuendo);
		assertTrue(guardarropa.getPrendasUsadas().containsAll(atuendo.obtenerPrendasTotales()));
		assertFalse(guardarropa.getSuperiores().contains(atuendo.getSuperior()));
	}

	@Test
	@DisplayName("Cuando un usuario deshace la decision de aceptar una sugerencia, las prendas se liberan en el guardarropa")
	void prendasLiberadas(){
		pedro.actualizarSubscripcionAPremium();
		pedro.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasJustito);

		Set<Atuendo> sugerenciasPedro = pedro.pedirSugerencia();
		Atuendo atuendo = sugerenciasPedro.stream().findFirst().get();
		pedro.aceptarAtuendo(atuendo);
		pedro.deshacerDecision();
		assertTrue(guardarropa.prendasDisponibles().containsAll(atuendo.obtenerPrendasTotales()));
		assertFalse(guardarropa.getPrendasUsadas().contains(atuendo.getSuperior()));
	}

}