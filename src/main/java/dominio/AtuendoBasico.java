package dominio;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AtuendoBasico {
	public Prenda superior; 
	public Prenda inferior;
	public Prenda calzado;
	
	public AtuendoBasico(Prenda superior, Prenda inferior, Prenda calzado) {
		this.superior = superior;
		this.inferior = inferior;
		this.calzado = calzado;
	}

	public boolean esAtuendoValido(Atuendo atuendo){
		return true;
	}

	public Set<Prenda> prendasPermitidas(Set<Prenda> superiores, Set<Prenda> inferiores, Set<Prenda> calzados) {
		Set<Prenda> setPermitido = new HashSet<Prenda>();
		setPermitido.addAll(superiores.stream().filter(prenda -> superior.aceptaSuperponerPrenda(prenda)).collect(Collectors.toSet()));
		setPermitido.addAll(inferiores.stream().filter(prenda -> inferior.aceptaSuperponerPrenda(prenda)).collect(Collectors.toSet()));
		setPermitido.addAll(calzados.stream().filter(prenda -> calzado.aceptaSuperponerPrenda(prenda)).collect(Collectors.toSet()));
		return setPermitido;
	}
}