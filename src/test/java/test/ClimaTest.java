package test;

import clima.*;
import excepciones.ClimaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sun.jersey.api.client.ClientResponse;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class ClimaTest {

    @Test
    @DisplayName("El clima queda guardado despues de consultar una vez con la api")
    void ClimaGuardado(){
        ProvedorClimatico provedor = new MockCalor();
        Clima Actual = provedor.obtenerClima("London");
        try{
            Clima sinApi = provedor.consultarClimaGuardado("London");
            assertEquals(Actual,sinApi);
        }catch(ClimaException e){
            fail("ocurrio una excepcion de clima: " + e.toString());
        }
    }

    @Test
    @DisplayName("Un clima no es valido despues de 12 horas de su obtencion")
    void ClimaNoValido(){
        Clima climaViejo = new Clima(5);
        climaViejo.setFechaObtencion(LocalDateTime.now().minusHours(13));
        assertTrue(!climaViejo.esValido());
    }

    @Test
    @DisplayName("Un clima es valido antes de 12 horas de su obtencion")
    void ClimaValido(){
        Clima climaNoTanViejo = new Clima(5);
        climaNoTanViejo.setFechaObtencion(LocalDateTime.now().minusHours(11));
        assertTrue(climaNoTanViejo.esValido());
    }

    //@Disabled //Este test anda, pero esta desactivado para no consultar la API de OpenWeather cada vez que corremos los tests
    @Test
    @DisplayName("Openweather responde consultas sin errores")
    void openWeatherResponde() {
        Clima londres = null;
        londres = OpenWeather.getInstance().obtenerClima("London");
        assertNotNull(londres);
    }

    @Test
    //@Disabled //idem para este test
    @DisplayName("Accuweather responde consultas sin errores")
    void accuWeatherResponde() {
        ClientResponse response = AccuWeather.getInstance().Api_get(AccuWeather.getInstance()
                .obtenerLink("http://dataservice.accuweather.com/forecasts/v1/daily/1day/","London"));
        assertEquals(response.getStatus(), 200);
    }
}