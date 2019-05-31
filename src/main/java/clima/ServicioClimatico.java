package clima;

import excepciones.ClimaGuardadoMuyAntiguoException;
import excepciones.NoExisteClimaGuardadoException;

import java.util.HashMap;
import java.util.Map;


public abstract class ServicioClimatico {
	Map<String, Clima> consultas = new HashMap<>();

	public abstract Clima obtenerClima(String nombre_ciudad);

	protected void agregarClima(String nombre_ciudad, Clima climaActual){
		consultas.put(nombre_ciudad, climaActual);
	}

	Clima consultarClimaGuardado(String nombre_ciudad)
			throws NoExisteClimaGuardadoException, ClimaGuardadoMuyAntiguoException {
		Clima clima = consultas.get(nombre_ciudad);
		 if(clima == null) throw new NoExisteClimaGuardadoException();
		 if(!clima.esValido()) throw new ClimaGuardadoMuyAntiguoException();
		 return clima;
	}
}
