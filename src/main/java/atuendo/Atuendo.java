package atuendo;

import java.util.HashSet;
import java.util.Set;

import clima.Clima;
import prenda.Prenda;

public class Atuendo {
	AtuendoBasico capaBasica;
	Set<CapaDeAbrigo> capasAbrigos = new HashSet<CapaDeAbrigo>();

	public Atuendo(AtuendoBasico atuendoBasico, Set<Prenda> PrendasComplementarias) {
		capaBasica = atuendoBasico;
		agregarPrendas(PrendasComplementarias);
	}

	public void agregarCapa(CapaDeAbrigo capa) {
		capasAbrigos.add(capa);
	}

	public int nivelAbrigo() {
		return capaBasica.nivelAbrigo() + capasAbrigos.stream().mapToInt(capa -> capa.nivelAbrigo()).sum();
	}

	public boolean esAtuendoValido(Atuendo atuendo) {
		return true;
	}

	public void agregarPrendas(Set<Prenda> prendas) {
		if (prendas.isEmpty()) {
			return;
		}
		prendas.stream().forEach(prenda -> agregarPrenda(prenda));
	}

	public void agregarPrenda(Prenda prenda) {
		CapaDeAbrigo capaDisponible = capasAbrigos.stream().filter(capa -> capa.noTieneParte(prenda.getCategoria()))
				.findFirst().orElseGet(() -> {
					CapaDeAbrigo CapaNueva = new CapaDeAbrigo();
					agregarCapa(CapaNueva);
					return CapaNueva;
				});
		capaDisponible.agregarPrenda(prenda);
	}

	public boolean soportaClima(Clima climaActual) {
		return this.nivelAbrigo() >= climaActual.nivelAbrigoRequerido();
	}

}