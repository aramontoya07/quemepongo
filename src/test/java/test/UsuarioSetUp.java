package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import atuendo.*;
import clima.*;
import excepciones.*;

import java.util.Set;

class UsuarioSetUp extends SetUp {

	@BeforeEach
	private void setUp() {
		setear();
	}

	@Test
	@DisplayName("Las sugerencias de prenda deben ser validas")
	void generarSugerencias() {
		pedro.agregarGuardarropa(guardarropa);
		pedro.actualizarSubscripcion();
		pedro.agregarPrendas(guardarropa, prendasGlobales);
		Set<Atuendo> listaSugerencias = pedro.pedirSugerencia();
		assertTrue(listaSugerencias.stream().allMatch(atuendo -> atuendo.esAtuendoValido(atuendo)));
	}

	@Test
	@DisplayName("Se deben generar todas las combinaciones posibles de ropa")
	void contarSugerencias() {
		// #FIXME Testear esto es medio raro
		pedro.agregarGuardarropa(guardarropa);
		pedro.actualizarSubscripcion();
		pedro.agregarPrendas(guardarropa, prendasGlobales);

		Set<Atuendo> listaSugerencias = pedro.pedirSugerencia();
		assertEquals(32, listaSugerencias.size());
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

		assertEquals(8, guardarropa.cantidadDePrendas());
	}

	@Test
	@DisplayName("Devuelve sugerencias aptas para un clima agradable")
	void prendasParaFrio() {
		pedro.agregarGuardarropa(guardarropa);
		pedro.actualizarSubscripcion();
		pedro.agregarPrendas(guardarropa, prendasGlobales);
		Set<SugerenciasClima> listaSugerencias = pedro.pedirSugerenciaSegunClima(new MockAgradable(), "London");
		assertTrue(listaSugerencias.stream()
				.allMatch(sugerencia -> sugerencia.esAptaParaClima(new MockAgradable().obtenerClima("London"))));
	}

	@Test
	@DisplayName("Devuelve sugerencias aptas para un clima calido")
	void prendasParaCalor() {
		pedro.actualizarSubscripcion();
		pedro.agregarGuardarropa(guardarropa);
		Set<SugerenciasClima> listaSugerencias = pedro.pedirSugerenciaSegunClima(new MockCalor(), "Palermo");
		assertTrue(listaSugerencias.stream()
				.allMatch(sugerencia -> sugerencia.esAptaParaClima(new MockCalor().obtenerClima("Palermo"))));
	}

}