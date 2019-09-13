package test;

import static org.junit.jupiter.api.Assertions.*;

import atuendo.Atuendo;
import atuendo.SugerenciasClima;
import clima.MockAgradable;
import clima.MockCalor;
import clima.MockFrio;
import clima.ServicioClimatico;
import excepciones.GuardarropaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prenda.Categoria;

import java.util.ArrayList;
import java.util.Set;

class GuardarropaTest extends SetUp {

    @BeforeEach
    private void setUp() {
        setear();
    }

    @Test
    @DisplayName("Se agrega correctamente una prenda y se guarda como DISPONIBLE")
    void agregarPrendaTest(){
        guardarropa.agregarPrenda(remeraAzul);
        assertTrue(guardarropa.getPrendasDisponibles().contains(remeraAzul));
    }

    @Test
    @DisplayName("Se agrega correctamente una prenda a la lista de USADAS cuando se usa")
    void usarPrendaTest(){
        guardarropa.agregarPrenda(remeraAzul);
        guardarropa.usarPrenda(remeraAzul);
        assertFalse(guardarropa.getPrendasDisponibles().contains(remeraAzul));
        assertTrue(guardarropa.getPrendasUsadas().contains(remeraAzul));
    }

    @Test
    @DisplayName("Falla al querer usar una prenda que no esta disponible")
    void usarPrendaInexistente(){ 
        assertThrows(GuardarropaException.class, () ->
            guardarropa.usarPrenda(remeraAzul)
        );
    }
    
    @Test
    @DisplayName("Falla al intentar liberar una prenda que no existe")
    void usoNoExistente(){
        assertThrows(GuardarropaException.class, () ->
                guardarropa.usarPrenda(remeraAzul)
        );
    }

    @Test
    @DisplayName("Se libera una prenda que esta siendo usada correctamente")
    void liberacion() {
        guardarropa.agregarPrenda(remeraAzul);
        guardarropa.usarPrenda(remeraAzul);
        guardarropa.liberarPrenda(remeraAzul);
        assertTrue(guardarropa.prendaDisponible(remeraAzul));
    }

    @Test
    @DisplayName("Falla al querer agregar una prenda existente")
    void prendaExistente(){
        guardarropa.agregarPrenda(remeraAzul);
        assertThrows(GuardarropaException.class, () ->
                guardarropa.agregarPrenda(remeraAzul)
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

        assertEquals(6, guardarropa.cantidadDePrendas());
    }

    boolean chequearAbrigoEn(int posicion, int abrigoBuscado, SugerenciasClima sugerencia){
        return sugerencia.getAproximadas().get(posicion).nivelAbrigo() == abrigoBuscado;
    }

    @Test
    @DisplayName("Ordena segun las preferencias")
    void prendasOrdenadas(){
        pedro.actualizarSubscripcionAPremium();
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
        assertThrows(GuardarropaException.class, ()->
                guardarropa.agregarImagenA(remeraDeportiva, "")
        );
    }

  

    @Test
    @DisplayName("Se deben generar todas las combinaciones posibles de ropa")
    void contarSugerencias(){
        pedro.agregarGuardarropa(guardarropa);
        pedro.actualizarSubscripcionAPremium();
        pedro.agregarPrendas(guardarropa, prendasGlobales);

        Set <Atuendo> listaSugerencias = pedro.pedirSugerencia();
        assertEquals(48, listaSugerencias.size());
    }

    @Test
    @DisplayName("Devuelve sugerencias aptas para un clima frio")
    void prendasParaFrio() {
        pedro.agregarGuardarropa(guardarropa);
        pedro.actualizarSubscripcionAPremium();
        pedro.agregarPrendas(guardarropa, prendasGlobales);
        ServicioClimatico.definirProvedor(new MockFrio());
        Set<SugerenciasClima> listaSugerencias = pedro.pedirSugerenciaSegunClima( "London");
        assertTrue(listaSugerencias.stream()
                .allMatch(sugerencia -> sugerencia.esAptaParaClima(new MockFrio().obtenerClima("London"))));
    }

    @Test
    @DisplayName("Devuelve sugerencias aptas para un clima calido")
    void prendasParaCalor() {
        pedro.actualizarSubscripcionAPremium();
        pedro.agregarGuardarropa(guardarropa);
        pedro.agregarPrendas(guardarropa, prendasGlobales);

        ServicioClimatico.definirProvedor(new MockCalor());

        Set<SugerenciasClima> listaSugerencias = pedro.pedirSugerenciaSegunClima("Palermo");
        assertTrue(listaSugerencias.stream()
                .allMatch(sugerencia -> sugerencia.esAptaParaClima(new MockCalor().obtenerClima("Palermo"))));
    }
}