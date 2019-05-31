package clima;

import excepciones.ClimaGuardadoMuyAntiguoException;
import excepciones.NoExisteClimaGuardadoException;

public class MockFrio extends ServicioClimatico {

	public Clima obtenerClima(String nombre_ciudad) {
		try{
			return consultarClimaGuardado(nombre_ciudad);
		}catch(NoExisteClimaGuardadoException | ClimaGuardadoMuyAntiguoException e){
			Clima climaActual =  new Clima(0);
<<<<<<< HEAD
			agregarClima(nombre_ciudad,climaActual);
=======
>>>>>>> 0fa6e354bd54cd2b3805134cc0f2721d258a4978
			return climaActual;
		}
	}

}
