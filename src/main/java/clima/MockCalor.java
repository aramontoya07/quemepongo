package clima;

public class MockCalor implements ServicioClimatico {

	public Clima obtenerClima() {
		return new Clima(38);
	}

}
