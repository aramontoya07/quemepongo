package test;

import atuendo.SugerenciasClima;
import eventos.AsistenciaEvento;
import eventos.Diaria;
import eventos.Evento;
import eventos.Unico;
import excepciones.EventoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventosTest extends SetUp{

    @BeforeEach
    private void setUp() {
        setear();
    }

    @Test
    @DisplayName("No se genera sugerenci para evento unico si faltan mas de 12 horas")
    void eventoNoGeneraSugerencias() throws InterruptedException {
        pedro.actualizarSubscripcionAPremium();
        pedro.agregarGuardarropa(guardarropa);
        pedro.agregarPrendas(guardarropa, prendasGlobales);

        LocalDateTime fechaTorneo = LocalDateTime.now().plusHours(13);

        Evento torneoFornite = new Evento("Torneo de Fornite", fechaTorneo, "Bokita el one", new Unico()); //"Bokita el one" viene a ser la ubicacion donde se toma el clima, no es relevante ya que usamos un mock de clima.
        pedro.asistirAEvento(torneoFornite);
        Thread.sleep(1000);
        assertThrows(EventoException.class, () ->
                pedro.pedirSugerenciaParaEvento(torneoFornite)
        );
    }

    @Test
    @DisplayName("Se genera sugerencia para evento unico si faltan menos de 12 horas.")
    void eventoGeneraSugerencias() throws InterruptedException {
        pedro.actualizarSubscripcionAPremium();
        pedro.agregarGuardarropa(guardarropa);
        pedro.agregarPrendas(guardarropa, prendasGlobales);

        LocalDateTime fechaTorneo = LocalDateTime.now().plusHours(6);
        Evento torneobaile = new Evento("torneo de bailar como ricardo millos", fechaTorneo, "Bokita de mi vida", new Unico());
        pedro.asistirAEvento(torneobaile);
        Thread.sleep(1000);
        Set<SugerenciasClima> sugerenciasParaEvento =  pedro.pedirSugerenciaParaEvento(torneobaile);
        assertFalse(sugerenciasParaEvento.isEmpty());
    }

    @Test
    @DisplayName("Un evento repetitivo genera sugerencias nada mas empezar")
    void eventoRepetitivoGeneraSugerencias() throws InterruptedException {
        pedro.actualizarSubscripcionAPremium();
        pedro.agregarGuardarropa(guardarropa);
        pedro.agregarPrendas(guardarropa, prendasGlobales);

        LocalDateTime fechaTorneo = LocalDateTime.now().plusHours(6);
        Evento entrenamiento = new Evento("entrenar haciendo bailes de fortnite", fechaTorneo, "El templo", new Diaria());
        pedro.asistirAEvento(entrenamiento);
        Thread.sleep(1000);
        Set<SugerenciasClima> sugerenciasParaEvento =  pedro.pedirSugerenciaParaEvento(entrenamiento);
        assertFalse(sugerenciasParaEvento.isEmpty());
    }

    //Aqui pondria mi test de evento repetitivo.. SI TUVIERA UNO!!

    @Test
    @DisplayName("Se obtienen bien los eventos unicos entre dos fechas") //TODO hacer que se puedan obtener eventos repetitivos entre dos fechas (ahora se puede pero solo mirando su fecha de inicio)
    void obtenerEventosEntreFechas(){
        LocalDateTime fechaPractica = LocalDateTime.now().plusDays(2);
        LocalDateTime fechaCalentamiento = LocalDateTime.now().plusDays(4);
        LocalDateTime fechaTorneo = LocalDateTime.now().plusDays(7);
        LocalDateTime fechaMinima = LocalDateTime.now();
        LocalDateTime fechaMaxima = LocalDateTime.now().plusDays(6);

        Evento practicaDeBaile = new Evento("practica", fechaPractica, "Casa de azul", new Unico());
        Evento calentamientoDeBaile = new Evento("calentamiento", fechaCalentamiento, "El riachuelo", new Unico());
        Evento torneobaile = new Evento("torneo de bailar como Rick Astley", fechaTorneo, "La bombonera", new Unico());

        pedro.asistirAEvento(practicaDeBaile);
        pedro.asistirAEvento(calentamientoDeBaile);
        pedro.asistirAEvento(torneobaile);

        Set<AsistenciaEvento> asistencias = pedro.obtenerEventosEntre(fechaMinima,fechaMaxima);

        assertEquals(2, asistencias.size());
        assertFalse(asistencias.stream().anyMatch(asistencia -> asistencia.esDeEvento(torneobaile)));
    }
}