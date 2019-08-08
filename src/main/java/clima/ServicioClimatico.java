package clima;

import excepciones.ClimaException;

public class ServicioClimatico {
	private static ProvedorClimatico provedorActual = new MockAgradable();
	
	public static Clima obtenerClima(String nombre_ciudad) throws ClimaException {
		if(provedorActual.equals(null)) throw new ClimaException("No existe proveedor climatico definido para consultarle el clima");
		return provedorActual.obtenerClima(nombre_ciudad);
	}
	
	public static void definirProvedor(ProvedorClimatico nuevoProvedor) {
		provedorActual = nuevoProvedor;
	}
}
