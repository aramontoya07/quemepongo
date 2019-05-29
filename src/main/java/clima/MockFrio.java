package clima;

public class MockFrio implements ServicioClimatico{

	public Clima obtenerClima() {
		return new Clima(0);
	}
	
}
