package test;

import atuendo.SugerenciasClima;
import clima.MockAgradable;
import clima.MockCalor;
import clima.MockFrio;
import eventos.Evento;
import excepciones.EventoLejanoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        Calendar fechaTorneo = Calendar.getInstance();
        fechaTorneo.set(2021, Calendar.DECEMBER,31, 7, 0);

        Evento torneoFornite = new Evento(pedro,fechaTorneo, "London",new MockAgradable());
        pedro.agregarEvento(torneoFornite);
        assertThrows(EventoLejanoException.class, () ->
                pedro.pedirSugerenciaParaEvento(torneoFornite)
        );
    }

    @Test
    @DisplayName("Se genera sugerencia si faltan menos de 12 horas.")
    void eventoGeneraSugerencias() throws InterruptedException {
        pedro.actualizarSubscripcion();
        pedro.agregarGuardarropa(guardarropa);
        pedro.agregarPrendas(guardarropa, prendasGlobales);

        Calendar fechaTorneo = Calendar.getInstance();
        fechaTorneo.add(Calendar.HOUR_OF_DAY, 11);
        Evento torneoFornite = new Evento(pedro, fechaTorneo, "London",new MockAgradable());
        pedro.agregarEvento(torneoFornite);
        Thread.sleep(1000); //Perdon franco :C, no tengo otra forma de probar esto, te lo justifico 1 pa 1 sin camiseta.
        Set<SugerenciasClima> sugerenciasParaEvento =  pedro.pedirSugerenciaParaEvento(torneoFornite);
        assertTrue(!sugerenciasParaEvento.isEmpty());
    }

}

