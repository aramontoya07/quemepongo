package clima;

public class ServicioClimatico {
	private static ProvedorClimatico provedorActual = new MockAgradable();
	
	public static Clima obtenerClima(String nombre_ciudad) {
		//TODO excepcion si no hay provedor
		return provedorActual.obtenerClima(nombre_ciudad);
	}
	
	public static void definirProvedor(ProvedorClimatico nuevoProvedor) {
		provedorActual = nuevoProvedor;
	}
}
