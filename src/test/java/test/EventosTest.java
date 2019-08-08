package test;

import eventos.Evento;
import eventos.EventoUnico;
import excepciones.EventoLejanoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventosTest extends SetUp{

    @BeforeEach
    private void setUp() {
        setear();
    }

    @Test
    @DisplayName("No se genera sugerencia si faltan mas de 12 horas")
    void eventoNoGeneraSugerencias() {
        pedro.actualizarSubscripcionAPremium();
        pedro.agregarGuardarropa(guardarropa);
        pedro.agregarPrendas(guardarropa, prendasGlobales);

        LocalDateTime fechaTorneo = LocalDateTime.now().plusHours(13);

        Evento torneoFornite = new EventoUnico("Torneo de Fornite", fechaTorneo, "London");
        pedro.asistirAEvento(torneoFornite);
        assertThrows(EventoLejanoException.class, () ->
                pedro.pedirSugerenciaParaEvento(torneoFornite)
        );
    }

 /*   @Test
    @DisplayName("Se genera sugerencia si faltan menos de 12 horas.")
    void eventoGeneraSugerencias() throws InterruptedException {
        pedro.actualizarSubscripcionAPremium();
        pedro.agregarGuardarropa(guardarropa);
        pedro.agregarPrendas(guardarropa, prendasGlobales);

        LocalDateTime fechaTorneo = LocalDateTime.now().plusHours(6);
        Evento torneobaile = new EventoUnico("torneo de bailar como ricardo millos", fechaTorneo, "London");
        pedro.asistirAEvento(torneobaile);
        Thread.sleep(1000);
        Set<SugerenciasClima> sugerenciasParaEvento =  pedro.pedirSugerenciaParaEvento(torneobaile);
        assertFalse(sugerenciasParaEvento.isEmpty());
    }*/
}

