package clima;

import alertas.Alerta;
import excepciones.ClimaException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public abstract class ProvedorClimatico {
	public static String tipoJson = "application/json";
	Map<String, Clima> consultas = new HashMap<>();

	public abstract Clima obtenerClima(String nombre_ciudad);

	protected void agregarClima(String nombre_ciudad, Clima climaActual){
		consultas.put(nombre_ciudad, climaActual);
	}

	public Clima consultarClimaGuardado(String nombre_ciudad) throws ClimaException {
		Clima clima = consultas.get(nombre_ciudad);
		 if(clima == null) throw new ClimaException("No existe un clima guardado para consultar");
		 if(!clima.esValido()) throw new ClimaException("El clima consultado fue obtenido hace mucho tiempo y ya no es confiable");
		 return clima;
	}

    public abstract Set<Alerta> obtenerAlertas(String ubicacion);
}
