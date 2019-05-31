package atuendo;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import clima.Clima;
import prenda.Categoria;
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
		Set<Prenda> superioresDeCapas = capasAbrigos.stream().map(capa -> capa.getPrendaDeCategoria(Categoria.PARTE_SUPERIOR)).collect(Collectors.toSet());
		Set<Prenda> inferioresDeCapas = capasAbrigos.stream().map(capa -> capa.getPrendaDeCategoria(Categoria.PARTE_INFERIOR)).collect(Collectors.toSet());
		Set<Prenda> calzadosDeCapas = capasAbrigos.stream().map(capa -> capa.getPrendaDeCategoria(Categoria.CALZADO)).collect(Collectors.toSet());
		Set<Prenda> todasJuntas = new HashSet<>();
		todasJuntas.addAll(superioresDeCapas);
		todasJuntas.addAll(inferioresDeCapas);
		todasJuntas.addAll(calzadosDeCapas);

		return todasJuntas.containsAll(capaBasica.prendasPermitidas(superioresDeCapas,inferioresDeCapas,calzadosDeCapas));
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

	public double nivelDeAdaptacionAlClima(Clima climaActual){
		return this.nivelAbrigo() - climaActual.nivelAbrigoRequerido();
	}
}