package clima;

public class ServicioClimatico {
	private static ProvedorClimatico provedorActual = new MockAgradable();
	
	public static Clima obtenerClima(String nombre_ciudad) {
		return provedorActual.obtenerClima(nombre_ciudad);
	}
	
	public static void definirProvedor(ProvedorClimatico nuevoProvedor) {
		provedorActual = nuevoProvedor;
	}
}
