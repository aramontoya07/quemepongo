package test;

import atuendo.SugerenciasClima;
import clima.MockAgradable;
import clima.MockCalor;
import clima.MockFrio;
import clima.ServicioClimatico;
import eventos.Evento;
import eventos.EventoUnico;
import excepciones.EventoLejanoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Set;

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
        pedro.actualizarSubscripcion();
        pedro.agregarGuardarropa(guardarropa);
        pedro.agregarPrendas(guardarropa, prendasGlobales);

        LocalDateTime fechaTorneo = LocalDateTime.now().plusHours(13);

        Evento torneoFornite = new EventoUnico("Torneo de Fornite", fechaTorneo, "London");
        pedro.asistirAEvento(torneoFornite);
        assertThrows(EventoLejanoException.class, () ->
                pedro.pedirSugerenciaParaEvento(torneoFornite)
        );
    }

    @Test
    @DisplayName("Se genera sugerencia si faltan menos de 12 horas.")
    void eventoGeneraSugerencias() throws InterruptedException {
        ServicioClimatico.definirProvedor(new MockAgradable());
        pedro.actualizarSubscripcion();
        pedro.agregarGuardarropa(guardarropa);
        pedro.agregarPrendas(guardarropa, prendasGlobales);

        LocalDateTime fechaTorneo = LocalDateTime.now().plusHours(6);
        Evento torneobaile = new EventoUnico("torneo de bailar como ricardo millos", fechaTorneo, "London");
        pedro.asistirAEvento(torneobaile);
        Thread.sleep(1000); //Perdon franco :C, no tengo otra forma de probar esto, te lo justifico 1 pa 1 sin camiseta.
        Set<SugerenciasClima> sugerenciasParaEvento =  pedro.pedirSugerenciaParaEvento(torneobaile);
        assertFalse(sugerenciasParaEvento.isEmpty());
    }
}

