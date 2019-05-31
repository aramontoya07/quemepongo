package clima;

import excepciones.ClimaGuardadoMuyAntiguoException;
import excepciones.NoExisteClimaGuardadoException;

public class MockCalor extends ServicioClimatico {

	public Clima obtenerClima(String nombre_ciudad) {
		try{
			return consultarClimaGuardado(nombre_ciudad);
		}catch(NoExisteClimaGuardadoException | ClimaGuardadoMuyAntiguoException e){
			Clima climaActual =  new Clima(70);
			return climaActual;
		}
	}
}
