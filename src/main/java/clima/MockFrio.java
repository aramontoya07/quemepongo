package clima;

import excepciones.ClimaException;

public class MockFrio extends ProvedorClimatico {

	public Clima obtenerClima(String nombre_ciudad) {
		try{
			return consultarClimaGuardado(nombre_ciudad);
		}catch(ClimaException e){
			Clima climaActual =  new Clima(0);
			agregarClima(nombre_ciudad,climaActual);
			return climaActual;
		}
	}

}
