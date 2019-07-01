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

	private void agregarLosQueAcepta(Set<Prenda> setAManipular, Set<Prenda> prendasAEvaluar, Prenda prendaCriterio){
		setAManipular.addAll(prendasAEvaluar.stream().filter(prenda -> prendaCriterio.aceptaSuperponerPrenda(prenda))
				.collect(Collectors.toSet()));
	}

	public Set<Prenda> prendasPermitidas(Set<Prenda> superiores) {
		Set<Prenda> setPermitido = new HashSet<Prenda>();
		agregarLosQueAcepta(setPermitido, superiores, superior);
		return setPermitido;
	}

	public int nivelAbrigo() {
		return superior.nivelAbrigo() + inferior.nivelAbrigo() + calzado.nivelAbrigo();
	}

	public boolean esAtuendoValido(Atuendo atuendo) {
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
