package clima;

import alertas.Alerta;
import excepciones.ClimaException;

import java.util.HashSet;
import java.util.Set;

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

	@Override
	public Set <Alerta> obtenerAlertas(String ubicacion) {
		return new HashSet <>();
	}

}
