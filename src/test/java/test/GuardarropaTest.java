package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import excepciones.*;

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