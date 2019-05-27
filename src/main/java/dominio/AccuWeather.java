package dominio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class AccuWeather implements ServicioClimatico{

	public Ubicacion ubicacionActual;
	private String api_key = "hdE6Cz46t4fXquFAM65p4k1NTNqOSLhG";
	
	public Clima obtenerClima() {
		ClientResponse respuesta = Api_get("http://dataservice.accuweather.com/forecasts/v1/hourly/12hour/" + ubicacionActual.getKey() + "?apikey="  + api_key + "&language=es");
		String JsonRespuesta = respuesta.getEntity(String.class); 
		return ParsearRespuesta(JsonRespuesta,Clima.class);
	}
	
	public void definirUbicacion(String nombre_ciudad){
		ClientResponse respuesta = Api_get("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + api_key + "&q=" + nombre_ciudad + "&language=es");
		String JsonRespuesta = respuesta.getEntity(String.class);
		ubicacionActual = ParsearRespuesta(JsonRespuesta,Ubicacion.class);
	}
	
	public ClientResponse Api_get(String request){
		Client client = Client.create();
		WebResource webResource = client.resource(request);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		return response;
	}
	
	public <T> T ParsearRespuesta(String Json,Class<T> clase) {
		GsonBuilder builder = new GsonBuilder();  
	    Gson gson = builder.create(); 
		return gson.fromJson(Json,clase);
	}
}
