package test;

import static org.junit.jupiter.api.Assertions.*;

import clima.MockAlertas;
import clima.ServicioClimatico;
import notificaciones.Informante;
import eventos.Evento;
import eventos.Frecuencia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import atuendo.*;
import excepciones.*;
import prenda.ParteAbrigada;
import subscripciones.SubscripcionPremium;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

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
		assertEquals(9, guardarropa.cantidadDePrendas());
	}

	@Test
	@DisplayName("Se actualiza correctamente la subscripcion a premium")
	void actualizarPremium() {
		pedro.actualizarSubscripcionAPremium();
		assertEquals(SubscripcionPremium.class,pedro.getSubscripcion().getClass());
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
		Atuendo atuendo = sugerenciasPedro.stream().collect(Collectors.toList()).get(0);
		pedro.aceptarAtuendo(atuendo);
		assertTrue(pedro.getAceptados().stream().map(uso -> uso.getAtuendo()).collect(Collectors.toSet()).contains(atuendo));
		assertTrue(guardarropa.getPrendasUsadas().containsAll(atuendo.obtenerPrendasTotales()));
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
		assertTrue(guardarropa.getPrendasDisponibles().containsAll(atuendo.obtenerPrendasTotales()));
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
		pedro.puntuarParteDeAtuendoEn(pedro.obtenerUso(atuendo), 7, ParteAbrigada.CABEZA);

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

		assertThrows(AtuendoException.class, () -> pedro.puntuarParteDeAtuendoEn(pedro.obtenerUso(atuendo), 1, ParteAbrigada.CABEZA));
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
		pedro.puntuarParteDeAtuendoEn(pedro.obtenerUso(atuendo), 7, ParteAbrigada.CABEZA);
		pedro.puntuarParteDeAtuendoEn(pedro.obtenerUso(atuendo), 8, ParteAbrigada.CABEZA);

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
		pedro.puntuarParteDeAtuendoEn(pedro.obtenerUso(atuendo), 7, ParteAbrigada.CABEZA);
		pedro.puntuarParteDeAtuendoEn(pedro.obtenerUso(atuendo), 6, ParteAbrigada.CABEZA);

		assertEquals(7, pedro.getPuntajeEn(ParteAbrigada.CABEZA));
	}

	@Test
	@DisplayName("Se notifica al usuario cuando sus sugerencias para un evento estan listas")
	void notificarSugerenciasListas() throws InterruptedException {
		pedro.actualizarSubscripcionAPremium();
		pedro.agregarGuardarropa(guardarropa);
		pedro.agregarPrendas(guardarropa, prendasGlobales);

		LocalDateTime fechaTorneo = LocalDateTime.now().plusHours(6);
		Evento entrenamiento = new Evento("entrenar haciendo bailes de fortnite", fechaTorneo, "El templo", Frecuencia.UNICO);

		pedro.agregarInformante(Informante.MockSMS);

		pedro.asistirAEvento(entrenamiento);

		Thread.sleep(1000);
		assertTrue(pedro.getNotificado());
	}

	@Test
	@DisplayName("Se notifica al usuario cuando hay una alerta en su zona de interes")
	void notificarAlertas() throws InterruptedException{
		ServicioClimatico.definirProvedor(new MockAlertas());
		LocalDateTime fechaTorneo = LocalDateTime.now().plusHours(6);
		Evento entrenamiento = new Evento("entrenar haciendo bailes de fortnite", fechaTorneo, "El templo", Frecuencia.UNICO);

		pedro.agregarInformante(Informante.MockSMS);

		pedro.asistirAEvento(entrenamiento);

		Thread.sleep(1000);
		assertTrue(pedro.getNotificado());
	}
}
