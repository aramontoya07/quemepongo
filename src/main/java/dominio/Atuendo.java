package dominio;

import java.util.List;
import java.util.Set;

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
}