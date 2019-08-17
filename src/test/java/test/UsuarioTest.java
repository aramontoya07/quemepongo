package test;

import static org.junit.jupiter.api.Assertions.*;

import clima.MockAlertas;
import clima.ServicioClimatico;
import notificaciones.Informante;
import eventos.Evento;
import eventos.Unico;
import notificaciones.InformanteMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import atuendo.*;
import excepciones.*;
import prenda.ParteAbrigada;

import java.time.LocalDateTime;
import java.util.Set;

class UsuarioTest extends SetUp {

	@BeforeEach
	private void setUp() {
		setear();
	}

	@Test
	@DisplayName("Al tener una suscripcion gratuita no se puede agregar mas de 5 prendas")
	void maximoPrendasConGratuidas() {
		assertThrows(GuardarropaException.class, () -> {
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
		// todo también chequearía que la subscripción cambió (es un detalle igual)
		assertEquals(9, guardarropa.cantidadDePrendas());
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
		//fixme usar findFirst devuelve un Optional, o sea que podría no tener ningún valor.
		//Qué pasa si el optional no tiene nada? (Pista: el get() les va a explotar, sería bueno manejarlo por las dudas)
		Atuendo atuendo = sugerenciasPedro.stream().findFirst().get();
		pedro.aceptarAtuendo(atuendo);
		assertTrue(pedro.getAceptados().contains(atuendo));
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

	@Test
	@DisplayName("Un usuario puede rechazar una sugerencia")
	void rechazaSugerencia(){
		pedro.actualizarSubscripcionAPremium();
		pedro.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasJustito);

		Set<Atuendo> sugerenciasPedro = pedro.pedirSugerencia();
		Atuendo atuendo = sugerenciasPedro.stream().findFirst().get();
		pedro.rechazarAtuendo(atuendo);
		assertTrue(pedro.getRechazados().contains(atuendo));
	}

	@Test
	@DisplayName("Un usuario puede deshacer la decision de rechazar una sugerencia")
	void deshacerRechazaSugerencia(){
		pedro.actualizarSubscripcionAPremium();
		pedro.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasJustito);

		Set<Atuendo> sugerenciasPedro = pedro.pedirSugerencia();
		Atuendo atuendo = sugerenciasPedro.stream().findFirst().get();
		pedro.rechazarAtuendo(atuendo);
		pedro.deshacerDecision();
		assertFalse(pedro.getRechazados().contains(atuendo));
	}

	@Test
	@DisplayName("Un usuario puede calificar una sugerencia aceptada")
	void calificarSugerencia(){
		pedro.actualizarSubscripcionAPremium();
		pedro.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasJustito);

		Set<SugerenciasClima> sugerenciasPedro = pedro.pedirSugerenciaSegunClima("Bokita es lo mas");
		SugerenciasClima sugerencias = sugerenciasPedro.stream().findFirst().get();
		Atuendo atuendo = sugerencias.getExactas().get(0);
		pedro.aceptarAtuendo(atuendo);
		pedro.puntuarParteDeAtuendoEn(atuendo, 7, ParteAbrigada.CABEZA);

		assertEquals(7, pedro.getPuntajeEn(ParteAbrigada.CABEZA));
		assertEquals(20.0, pedro.getAbrigoPreferidoEn(ParteAbrigada.CABEZA));

	}

	@Test
	@DisplayName("Un usuario no puede calificar una sugerencia que no fue aceptada")
	void calificarNoAceptada(){
		pedro.actualizarSubscripcionAPremium();
		pedro.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasJustito);

		Set<SugerenciasClima> sugerenciasPedro = pedro.pedirSugerenciaSegunClima("Bokita es lo mas");
		SugerenciasClima sugerencias = sugerenciasPedro.stream().findFirst().get();
		Atuendo atuendo = sugerencias.getExactas().get(0);
		pedro.rechazarAtuendo(atuendo);

		assertThrows(AtuendoException.class, () -> pedro.puntuarParteDeAtuendoEn(atuendo, 1, ParteAbrigada.CABEZA));
	}

	@Test
	@DisplayName("Si se califica algo dos veces con una nota mayor, se reemplaza la preferencia")
	void recalificar(){
		pedro.actualizarSubscripcionAPremium();
		pedro.agregarGuardarropa(guardarropa);
		prendasJustito.add(jeanNegro);
		pedro.agregarPrendas(guardarropa, prendasJustito);

		Set<SugerenciasClima> sugerenciasPedro = pedro.pedirSugerenciaSegunClima("Bokita es lo mas");
		SugerenciasClima sugerencias = sugerenciasPedro.stream().findFirst().get();
		Atuendo atuendo = sugerencias.getExactas().get(0);
		pedro.aceptarAtuendo(atuendo);
		pedro.puntuarParteDeAtuendoEn(atuendo, 7, ParteAbrigada.CABEZA);
		pedro.puntuarParteDeAtuendoEn(atuendo, 8, ParteAbrigada.CABEZA);

		assertEquals(8, pedro.getPuntajeEn(ParteAbrigada.CABEZA));
	}

	@Test
	@DisplayName("Si se califica algo dos veces con una nota menor, no se reemplaza la preferencia")
	void recalificarNoReemplaza(){
		pedro.actualizarSubscripcionAPremium();
		pedro.agregarGuardarropa(guardarropa);
		prendasJustito.add(jeanNegro);
		pedro.agregarPrendas(guardarropa, prendasJustito);

		Set<SugerenciasClima> sugerenciasPedro = pedro.pedirSugerenciaSegunClima("Bokita es lo mas");
		SugerenciasClima sugerencias = sugerenciasPedro.stream().findFirst().get();
		Atuendo atuendo = sugerencias.getExactas().get(0);
		pedro.aceptarAtuendo(atuendo);
		pedro.puntuarParteDeAtuendoEn(atuendo, 7, ParteAbrigada.CABEZA);
		pedro.puntuarParteDeAtuendoEn(atuendo, 6, ParteAbrigada.CABEZA);

		assertEquals(7, pedro.getPuntajeEn(ParteAbrigada.CABEZA));
	}

	@Test
	@DisplayName("Se notifica al usuario cuando sus sugerencias para un evento estan listas")
	void notificarSugerenciasListas() throws InterruptedException {
		pedro.actualizarSubscripcionAPremium();
		pedro.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasGlobales);

		LocalDateTime fechaTorneo = LocalDateTime.now().plusHours(6);
		Evento entrenamiento = new Evento("entrenar haciendo bailes de fortnite", fechaTorneo, "El templo", new Unico());

		Informante informanteMockeado = new InformanteMock();
		pedro.agregarInformante(informanteMockeado);

		pedro.asistirAEvento(entrenamiento);

		Thread.sleep(1000);
		assertTrue(pedro.getNotificado());
	}

	@Test
	@DisplayName("Se notifica al usuario cuando hay una alerta en su zona de interes")
	void notificarAlertas() throws InterruptedException{
		ServicioClimatico.definirProvedor(new MockAlertas());
		LocalDateTime fechaTorneo = LocalDateTime.now().plusHours(6);
		Evento entrenamiento = new Evento("entrenar haciendo bailes de fortnite", fechaTorneo, "El templo", new Unico());

		Informante informanteMockeado = new InformanteMock();
		pedro.agregarInformante(informanteMockeado);

		pedro.asistirAEvento(entrenamiento);

		Thread.sleep(1000);
		assertTrue(pedro.getNotificado());
	}
}
