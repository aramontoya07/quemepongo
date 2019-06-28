package clima;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import excepciones.ClimaGuardadoMuyAntiguoException;
import excepciones.HttpCodeException;
import excepciones.NoExisteClimaGuardadoException;
import org.json.JSONObject;

public class OpenWeather extends ProvedorClimatico {
	private static OpenWeather single_instance = null;
	String api_key = "dd868503319e88af289ea1772d90c952";

	public static OpenWeather getInstance(){
		if (single_instance == null) single_instance = new OpenWeather();
		return single_instance;
	}

	public Clima obtenerClima(String nombre_ciudad) {
		try{
			return consultarClimaGuardado(nombre_ciudad);
		}catch(NoExisteClimaGuardadoException | ClimaGuardadoMuyAntiguoException e){
			ClientResponse respuesta = Api_get("http://api.openweathermap.org/data/2.5/weather?q="
					+ nombre_ciudad + "&APPID=" + api_key + "&units=metric&lang=es");
			String JsonRespuesta = respuesta.getEntity(String.class);
			
			Clima climaActual = parsearClima(JsonRespuesta);
			
			agregarClima(nombre_ciudad,climaActual);
			return climaActual;
		}
	}

	public ClientResponse Api_get(String request) {
		Client client = Client.create();
		WebResource webResource = client.resource(request);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new HttpCodeException(response.getStatus());
		}
		return response;
	}
	
	public Clima parsearClima(String JSON){
		JSONObject json = new JSONObject(JSON);
		return new Clima(json.getJSONObject("main").getDouble("temp"));
	}
}