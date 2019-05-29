package clima;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import excepciones.HttpCodeException;

public class OpenWeather implements ServicioClimatico {
	String Ciudad_actual;
	String api_key = "dd868503319e88af289ea1772d90c952";

	public Clima obtenerClima() {
		ClientResponse respuesta = Api_get(
				"api.openweathermap.org/data/2.5/weather?q=" + Ciudad_actual + "&lang=es&APPID=" + api_key);
		String JsonRespuesta = respuesta.getEntity(String.class);
		return ParsearRespuesta(JsonRespuesta, Clima.class);
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

	public <T> T ParsearRespuesta(String Json, Class<T> clase) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.fromJson(Json, clase);
	}
}