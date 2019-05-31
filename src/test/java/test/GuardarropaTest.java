package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import atuendo.*;
import clima.*;
import excepciones.*;
import usuario.Usuario;

import java.util.Set;

class GuardarropaTest extends SetUp {

    @BeforeEach
    private void setUp() {
        setear();
    }


    @Test
    @DisplayName("Falla al querer agregar una prenda existente")
    void prendaExistente(){
        guardarropa.agregarPrenda(remeraAzul);
        assertThrows(PrendaYaExisteException.class, () ->
                guardarropa.agregarPrenda(remeraAzul)
        );
    }

    @Test
    @DisplayName("Cantidad de ropa de un guardarropa")
    void cantidadRopa(){
        guardarropa.agregarPrenda(remeraAzul);
        guardarropa.agregarPrenda(remeraDeportiva);
        guardarropa.agregarPrenda(camperaGris);
        guardarropa.agregarPrenda(jeanRojo);
        guardarropa.agregarPrenda(jeanNegro);
        guardarropa.agregarPrenda(anteojosDeSol);

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
        guardarropa.agregarPrenda(remeraDeportiva);
        assertTrue(guardarropa.getSuperiores().contains(remeraDeportiva));
    }

}