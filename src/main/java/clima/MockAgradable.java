package clima;

import excepciones.ClimaGuardadoMuyAntiguoException;
import excepciones.NoExisteClimaGuardadoException;

public class MockAgradable extends ServicioClimatico {

    public Clima obtenerClima(String nombre_ciudad) {
        try{
            return consultarClimaGuardado(nombre_ciudad);
        }catch(NoExisteClimaGuardadoException | ClimaGuardadoMuyAntiguoException e){
            Clima climaActual =  new Clima(20);
            agregarClima(nombre_ciudad,climaActual);
            return climaActual;
        }
    }
}