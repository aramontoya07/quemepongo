package clima;



import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import excepciones.ClimaGuardadoMuyAntiguoException;
import excepciones.HttpCodeException;
import excepciones.NoExisteClimaGuardadoException;
import org.json.JSONArray;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

public class AccuWeather extends ServicioClimatico {     
	private static AccuWeather single_instance = null;
	private Map<String,Ubicacion> keys = new HashMap<>();
	private String api_key = "hdE6Cz46t4fXquFAM65p4k1NTNqOSLhG";

	public static AccuWeather getInstance() {
		if (single_instance == null) single_instance = new AccuWeather();
		return single_instance;
	}

	public Clima obtenerClima(String nombre_ciudad) {
		try{
			return consultarClimaGuardado(nombre_ciudad);
		}catch(NoExisteClimaGuardadoException | ClimaGuardadoMuyAntiguoException e){

			Ubicacion ubicacionActual = obtenerUbicacion(nombre_ciudad);
			keys.put(nombre_ciudad,ubicacionActual);
			ClientResponse respuesta = Api_get("http://dataservice.accuweather.com/currentconditions/v1/"
					+ ubicacionActual.getKey() + "?apikey=" + api_key + "&language=es");
			String JsonRespuesta = respuesta.getEntity(String.class);

			Clima climaActual = parsearClima(JsonRespuesta);
			this.agregarClima(nombre_ciudad,climaActual);
			return climaActual;

		}
	}

	public Ubicacion obtenerUbicacion(String nombre_ciudad) {
		ClientResponse respuesta = Api_get("http://dataservice.accuweather.com/locations/v1/cities/search?apikey="
				+ api_key + "&q=" + nombre_ciudad + "&language=es");
		String JsonRespuesta = respuesta.getEntity(String.class);
		return parsearUbicacion(JsonRespuesta);
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
		JSONArray array = new JSONArray(JSON);
		return new Clima(array.getJSONObject(0).getJSONObject("Temperature").getJSONObject("Metric").getDouble("Value"));
	}

	public Ubicacion parsearUbicacion(String JSON){
		JSONArray array = new JSONArray(JSON);
		return new Ubicacion(array.getJSONObject(0).getString("Key"));
	}
}