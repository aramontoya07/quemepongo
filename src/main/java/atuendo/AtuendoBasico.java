package atuendo;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import prenda.Prenda;

public class AtuendoBasico {
	Prenda superior; 
	Prenda inferior;
	Prenda calzado;
	
	public AtuendoBasico(Prenda prendaSuperior, Prenda prendaInferior, Prenda prendaCalzado) {
		superior = prendaSuperior;
		inferior = prendaInferior;
		calzado = prendaCalzado;

	}
	
	public Set<Prenda> prendasPermitidas(Set<Prenda> superiores2, Set<Prenda> inferiores2, Set<Prenda> calzados2) {
		Set<Prenda> setPermitido = new HashSet<Prenda>();
		setPermitido.addAll(superiores2.stream().filter(prenda -> superior.aceptaSuperponerPrenda(prenda)).collect(Collectors.toSet()));
		setPermitido.addAll(inferiores2.stream().filter(prenda -> inferior.aceptaSuperponerPrenda(prenda)).collect(Collectors.toSet()));
		setPermitido.addAll(calzados2.stream().filter(prenda -> calzado.aceptaSuperponerPrenda(prenda)).collect(Collectors.toSet()));
		return setPermitido;
	}

	public boolean esAtuendoValido(Atuendo atuendo){
		return true;
	}
	
	public Prenda getSuperior() {
		return superior;
	}

	public void setSuperior(Prenda superior) {
		this.superior = superior;
	}

	public Prenda getInferior() {
		return inferior;
	}

	public void setInferior(Prenda inferior) {
		this.inferior = inferior;
	}

	public Prenda getCalzado() {
		return calzado;
	}

	public void setCalzado(Prenda calzado) {
		this.calzado = calzado;
	}

}