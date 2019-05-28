package dominio;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import prenda.Prenda;

public class Atuendo {
	public List<Prenda> superiores; 
	public List<Prenda> inferiores;
	public List<Prenda> calzados;
	public List<Prenda> accesorios;
	
	public Atuendo(Prenda superior, Prenda inferior, Prenda calzado) {
		this.superiores.add(superior);
		this.inferiores.add(inferior);
		this.calzados.add(calzado);
	}

	public boolean esAtuendoValido(Atuendo atuendo){
		return true;
	}
	
	public Set<Prenda> prendasPermitidas(Set<Prenda> superiores2, Set<Prenda> inferiores2, Set<Prenda> calzados2) {
		Set<Prenda> setPermitido = new HashSet<Prenda>();
		setPermitido.addAll(superiores2.stream().filter(prenda -> superiores.get(0).aceptaSuperponerPrenda(prenda)).collect(Collectors.toSet()));
		setPermitido.addAll(inferiores2.stream().filter(prenda -> inferiores.get(0).aceptaSuperponerPrenda(prenda)).collect(Collectors.toSet()));
		setPermitido.addAll(calzados2.stream().filter(prenda -> calzados.get(0).aceptaSuperponerPrenda(prenda)).collect(Collectors.toSet()));
		return setPermitido;
	}
	
	public void agregarPrendas(Set<Prenda> prendas) {
		prendas.stream().forEach(prenda -> agregarPrenda(prenda));
	}
	
	private void agregarPrenda(Prenda prenda) {
		switch (prenda.getCategoria()) {
		case PARTE_SUPERIOR:
			superiores.add(prenda);
			break;
		case PARTE_INFERIOR:
			inferiores.add(prenda);
			break;
		case ACCESORIO:
			accesorios.add(prenda);
			break;
		case CALZADO:
			calzados.add(prenda);
			break;
		}
	}
}