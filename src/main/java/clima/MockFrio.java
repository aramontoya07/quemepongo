package clima;

import excepciones.ClimaGuardadoMuyAntiguoException;
import excepciones.NoExisteClimaGuardadoException;

public class MockFrio extends ServicioClimatico {

	public Clima obtenerClima(String nombre_ciudad) {
		try{
			return consultarClimaGuardado(nombre_ciudad);
		}catch(NoExisteClimaGuardadoException | ClimaGuardadoMuyAntiguoException e){
			Clima climaActual =  new Clima(0);
			agregarClima(nombre_ciudad,climaActual);
			return climaActual;
		}
	}

}
