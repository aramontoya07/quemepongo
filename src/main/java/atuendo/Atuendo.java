package atuendo;

import java.util.Set;

import prenda.Prenda;

public class Atuendo {
	AtuendoBasico capaBasica;
	Set<CapaDeAbrigo> capasAbrigos;
	
	public Atuendo(AtuendoBasico atuendoBasico, Set<Prenda> PrendasComplementarias) {
		capaBasica = atuendoBasico;
		agregarPrendas(PrendasComplementarias);
	}

	public void agregarCapa(CapaDeAbrigo capa) {
		capasAbrigos.add(capa);
	}
	
	public boolean esAtuendoValido(Atuendo atuendo){
		return true;
	}
	
	public void agregarPrendas(Set<Prenda> prendas) {
		prendas.stream().forEach(prenda -> agregarPrenda(prenda));
	}
	
	public void agregarPrenda(Prenda prenda) {
		CapaDeAbrigo capaDisponible = capasAbrigos.stream().filter(capa -> capa.noTieneParte(prenda.getCategoria())).findFirst().orElseGet(() -> {
			CapaDeAbrigo CapaNueva = new CapaDeAbrigo();
			agregarCapa(CapaNueva);
			return CapaNueva;
		});
		capaDisponible.agregarPrenda(prenda);
	}
	
}