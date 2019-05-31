package clima;

public class MockFrio extends ServicioClimatico {

	public Clima obtenerClima(String nombre_ciudad) {
		return new Clima(0);
	}

}
