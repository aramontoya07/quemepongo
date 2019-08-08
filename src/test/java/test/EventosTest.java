package test;

import atuendo.SugerenciasClima;
import eventos.Evento;
import eventos.EventoUnico;
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

        Evento torneoFornite = new EventoUnico("Torneo de Fornite", fechaTorneo, "Bokita el one");
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
        Evento torneobaile = new EventoUnico("torneo de bailar como ricardo millos", fechaTorneo, "Bokita de mi vida");
        pedro.asistirAEvento(torneobaile);
        Thread.sleep(1000);
        Set<SugerenciasClima> sugerenciasParaEvento =  pedro.pedirSugerenciaParaEvento(torneobaile);
        assertFalse(sugerenciasParaEvento.isEmpty());
    }
}

