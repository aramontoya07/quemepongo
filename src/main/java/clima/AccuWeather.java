package clima;



import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import com.sun.xml.internal.bind.v2.TODO;
import excepciones.ClimaException;
import excepciones.HttpCodeException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AccuWeather extends ProvedorClimatico {     
	private static AccuWeather single_instance = null;
	private Map<String,Ubicacion> keys = new HashMap<>();
	private static String keyPropia = "zhfH9jKdVhV1EfPHRAHoZTZqBH7GbsDM";
	private static String paginaAccuweather = "http://dataservice.accuweather.com";
	private static String accuweatherConditions = paginaAccuweather + "/currentconditions/v1/";
	private static String accuweatherLoqueishons = paginaAccuweather + "/locations/v1/cities/search";
	private static String idioma = "&language=es";
	private static String apikey = "?apikey=";

	public static AccuWeather getInstance() {
		if (single_instance == null) single_instance = new AccuWeather();
		return single_instance;
	}

	public Clima obtenerClima(String nombre_ciudad) {
		try{
			return consultarClimaGuardado(nombre_ciudad);
		}catch(ClimaException e){
			Ubicacion ubicacionActual = obtenerUbicacion(nombre_ciudad);
			keys.put(nombre_ciudad,ubicacionActual);
			ClientResponse respuesta = Api_get(accuweatherConditions + ubicacionActual.getKey() + apikey + keyPropia + idioma);
			String JsonRespuesta = respuesta.getEntity(String.class);
			Clima climaActual = parsearClima(JsonRespuesta);
			this.agregarClima(nombre_ciudad,climaActual);
			return climaActual;
		}
	}
	
	public PronosticoMetereologico obtenerPronostico(String linkParcial, String ciudad) {
		String link = obtenerLink(linkParcial,ciudad);
		String JsonRespuesta =obtenerRespuesta(link);
		return parsearPronostico(JsonRespuesta);
	}
	
	public String obtenerLink(String linkParcial,String nombre_ciudad) {
		Ubicacion ubicacionActual = obtenerUbicacion(nombre_ciudad);
		keys.put(nombre_ciudad,ubicacionActual);
		return linkParcial+ ubicacionActual.getKey() + apikey + keyPropia + idioma;
	}

	public Ubicacion obtenerUbicacion(String nombre_ciudad) {
		String link = accuweatherLoqueishons + apikey + keyPropia + "&q=" + nombre_ciudad + idioma;
		String JsonRespuesta =obtenerRespuesta(link);
		return parsearUbicacion(JsonRespuesta);
	}

	public ClientResponse Api_get(String request) {
		Client client = Client.create();
		WebResource webResource = client.resource(request);
		ClientResponse response = webResource.accept(tipoJson).get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new HttpCodeException("Fallo la comunicacion con la API de AccuWeather. Codigo de error " + response.getStatus());
		}
		return response;
	}
	
	public String obtenerRespuesta(String link) {
		ClientResponse respuesta = Api_get(link);
		return respuesta.getEntity(String.class);
	}

	public Clima parsearClima(String JSON){
		JSONArray array = new JSONArray(JSON);
		return new Clima(array.getJSONObject(0).getJSONObject("Temperature").getJSONObject("Metric").getDouble("Value"));
	}

	public PronosticoMetereologico parsearPronostico(String JSON){
	JSONObject object = new JSONObject(JSON);
	return new PronosticoMetereologico(object.getJSONObject("Headline").getString("Category"));
	}

	public Ubicacion parsearUbicacion(String JSON){
		JSONArray array = new JSONArray(JSON);
		return new Ubicacion(array.getJSONObject(0).getString("Key"));
	}
}