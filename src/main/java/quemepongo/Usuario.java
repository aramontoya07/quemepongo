package quemepongo;

import java.util.List;
import java.util.stream.Collectors;


public class Usuario {
	public List<Guardarropas> guardarropas;

	public Usuario(List<Guardarropas> guardarropas) {
		this.guardarropas = guardarropas;
	}
	public List<Atuendo>pedirSugerencia(){
		return guardarropas.stream().map(unGuardarropa -> unGuardarropa.generarSugerencia()).flatMap(atuendos -> atuendos.stream()).collect(Collectors.toList());
	}
}
