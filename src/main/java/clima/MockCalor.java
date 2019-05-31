package clima;

import excepciones.ClimaGuardadoMuyAntiguoException;
import excepciones.NoExisteClimaGuardadoException;

public class MockCalor extends ServicioClimatico {

	public Clima obtenerClima(String nombre_ciudad) {
		try{
			return consultarClimaGuardado(nombre_ciudad);
		}catch(NoExisteClimaGuardadoException | ClimaGuardadoMuyAntiguoException e){
<<<<<<< HEAD
			Clima climaActual =  new Clima(30);
			agregarClima(nombre_ciudad,climaActual);
			return climaActual;
		}
	}
}
=======
			Clima climaActual =  new Clima(70);
			return climaActual;
		}
	}
}
>>>>>>> 0fa6e354bd54cd2b3805134cc0f2721d258a4978
