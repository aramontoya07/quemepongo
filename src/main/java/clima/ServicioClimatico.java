package clima;

import alertas.Alerta;
import excepciones.ClimaException;

import java.util.Set;

public class ServicioClimatico {
	static String ubicacion = "Buenos Aires";
	private static ProvedorClimatico provedorActual = new MockAgradable();

	public static Clima obtenerClimaEnDefault() throws ClimaException {
		return ServicioClimatico.obtenerClima(ubicacion);
	}


	public static Clima obtenerClima(String nombre_ciudad) throws ClimaException {
		if(provedorActual.equals(null)) throw new ClimaException("No existe proveedor climatico definido para consultarle el clima");
		return provedorActual.obtenerClima(nombre_ciudad);
	}

	public String getUbicacionActual(){
		return ubicacion;
	}
	
	public static void definirProvedor(ProvedorClimatico nuevoProvedor) {
		provedorActual = nuevoProvedor;
	}

	public static Set<Alerta> obtenerAlertas(String ubicacion) {
		return provedorActual.obtenerAlertas(ubicacion);
	}
}
