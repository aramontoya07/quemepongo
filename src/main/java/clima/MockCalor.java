package clima;

public class MockCalor extends ServicioClimatico {

	public Clima obtenerClima(String nombre_ciudad) {
		return new Clima(38);
	}

}
