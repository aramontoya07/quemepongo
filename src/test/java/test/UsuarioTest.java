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

import java.util.ArrayList;
import java.util.List;
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
	@DisplayName("Devuelve sugerencias aptas para un clima agradable") //TODO este explota
	void prendasParaFrio() {
		pedro.agregarGuardarropa(guardarropa);
		pedro.actualizarSubscripcion();
		pedro.agregarPrendas(guardarropa, prendasGlobales);
		
		ServicioClimatico.definirProvedor(new MockFrio());
		
		Set<SugerenciasClima> listaSugerencias = pedro.pedirSugerenciaSegunClima( "London");
		assertTrue(listaSugerencias.stream()
				.allMatch(sugerencia -> sugerencia.esAptaParaClima(new MockAgradable().obtenerClima("London"))));
	}

	@Test
	@DisplayName("Devuelve sugerencias aptas para un clima calido")
	void prendasParaCalor() {
		pedro.actualizarSubscripcion();
		pedro.agregarGuardarropa(guardarropa);
		
		ServicioClimatico.definirProvedor(new MockCalor());
		
		Set<SugerenciasClima> listaSugerencias = pedro.pedirSugerenciaSegunClima("Palermo");
		assertTrue(listaSugerencias.stream()
				.allMatch(sugerencia -> sugerencia.esAptaParaClima(new MockCalor().obtenerClima("Palermo"))));
	}

}