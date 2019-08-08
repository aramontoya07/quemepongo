package test;

import static org.junit.jupiter.api.Assertions.*;

import atuendo.SugerenciasClima;
import clima.MockAgradable;
import clima.ServicioClimatico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import excepciones.*;

import java.util.ArrayList;

class GuardarropaTest extends SetUp {

    @BeforeEach
    private void setUp() {
        setear();
    }


    @Test
    @DisplayName("Falla al querer agregar una prenda existente")
    void prendaExistente(){
        guardarropa.agregarADisponibles(remeraAzul);
        assertThrows(PrendaYaExisteException.class, () ->
                guardarropa.agregarADisponibles(remeraAzul)
        );
    }

    @Test
    @DisplayName("Cantidad de ropa de un guardarropa")
    void cantidadRopa(){
        guardarropa.agregarADisponibles(remeraAzul);
        guardarropa.agregarADisponibles(remeraDeportiva);
        guardarropa.agregarADisponibles(camperaGris);
        guardarropa.agregarADisponibles(jeanRojo);
        guardarropa.agregarADisponibles(jeanNegro);
        guardarropa.agregarADisponibles(anteojosDeSol);

        assertEquals(7-1, guardarropa.cantidadDePrendas());
    }

    boolean chequearAbrigoEn(int posicion, int abrigoBuscado, SugerenciasClima sugerencia){
        return sugerencia.getAproximadas().get(posicion).nivelAbrigo() == abrigoBuscado;
    }

    @Test
    @DisplayName("Ordena segun las preferencias")
    void prendasOrdenadas(){
        pedro.actualizarSubscripcionAPremium();
        guardarropa.setMargenDePrendasAproximadas(1000);
        pedro.agregarGuardarropa(guardarropa);
        pedro.agregarPrendas(guardarropa, prendasOrdenables);
        ServicioClimatico.definirProvedor(new MockAgradable());
        SugerenciasClima sugerencia = new ArrayList <>(pedro.pedirSugerenciaSegunClima("Bokita el mas grande")).get(0);
        assertTrue(chequearAbrigoEn(0,30, sugerencia) &&
                chequearAbrigoEn(1,12, sugerencia) &&
                chequearAbrigoEn(2,0, sugerencia));
    }

    @Test
    @DisplayName("No se puede agregar imagen a una prenda que no existe")
    void imagen_A_PrendaNoExistente(){
        assertThrows(NoExistePrendaEnGuardarropaException.class, ()->
                guardarropa.agregarImagenA(remeraDeportiva, "")
        );
    }

    @Test
    @DisplayName("Agrega prenda en la lista correcta del guardarropa")
    void agregarPrendaEnListaCorrecta(){
        guardarropa.agregarADisponibles(remeraDeportiva);
        assertTrue(guardarropa.getSuperiores().contains(remeraDeportiva));
    }

}