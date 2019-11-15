package clima;

import alertas.Alerta;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import excepciones.ClimaException;
import excepciones.HttpCodeException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class OpenWeather extends ProvedorClimatico {
	private static OpenWeather single_instance = null;
	String keyPropia = "dd868503319e88af289ea1772d90c952";
	private static String idioma = "&units=metric&lang=es";
	private static String linkObtencion = "http://api.openweathermap.org/data/2.5/weather?q=";

	public static OpenWeather getInstance(){
		if (single_instance == null) single_instance = new OpenWeather();
		return single_instance;
	}

	public Clima obtenerClima(String nombre_ciudad) {
		try{
			return consultarClimaGuardado(nombre_ciudad);
		}catch(ClimaException e){
			String JsonRespuesta = obtenerJson(nombre_ciudad);
			Clima climaActual = parsearClima(JsonRespuesta);
			
			agregarClima(nombre_ciudad,climaActual);
			return climaActual;
		}
	}

	private String obtenerJson(String nombre_ciudad) {
		ClientResponse respuesta = Api_get(linkObtencion + nombre_ciudad + "&APPID=" + keyPropia + idioma);
		return respuesta.getEntity(String.class);
	}

	@Override
	public Set<Alerta> obtenerAlertas(String ubicacion){
		String JsonRespuesta = obtenerJson(ubicacion);
		return parsearAlertas(JsonRespuesta);
	}

	private Set<Alerta> parsearAlertas(String JSON) {
		Set<Alerta> alertas = new HashSet <>();
		//TODO implementar alertas en openweather
		return alertas;
	}

	public ClientResponse Api_get(String request) {
		Client client = Client.create();
		WebResource webResource = client.resource(request);
		ClientResponse response = webResource.accept(tipoJson).get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new HttpCodeException("Fallo la comunicacion con la API de AccuWeather. Codigo de error: " + response.getStatus());
		}
		return response;
	}
	
	public Clima parsearClima(String JSON){
		JSONObject json = new JSONObject(JSON);
		return new Clima(json.getJSONObject("main").getDouble("temp"));
	}
}