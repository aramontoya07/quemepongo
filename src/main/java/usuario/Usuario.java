package usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import atuendo.Atuendo;
import clima.ServicioClimatico;
import decisiones.Decision;
import decisiones.DecisionAceptar;
import decisiones.DecisionRechazar;
import excepciones.AgregarPrendaException;
import prenda.Prenda;
import subscripciones.SubscripcionGratuita;
import subscripciones.SubscripcionPremium;
import subscripciones.TipoSubscripcion;

public class Usuario {
	public Decision ultimaDecision;
	public Queue<Atuendo> aceptados;
	public Queue<Atuendo> rechazados;
	public Set<Guardarropas> guardarropas = new HashSet<Guardarropas>();
	public TipoSubscripcion subscripcion;

	public Usuario() {
		this.subscripcion = new SubscripcionGratuita();
	}

	public void agregarGuardarropa(Guardarropas guardarropa) {
		guardarropas.add(guardarropa);
	}

	public void agregarPrendas(Guardarropas guardarropa, Set<Prenda> prendas) {
		prendas.stream().forEach(prenda -> agregarPrenda(guardarropa, prenda));
	}

	public void agregarPrenda(Guardarropas guardarropa, Prenda prenda) {
		if (subscripcion.puedoAgregar(guardarropa.cantidadDePrendas())) {
			guardarropa.agregarPrenda(prenda);
		} else {
			throw new AgregarPrendaException();
		}
	}

	public void actualizarSubscripcion() {
		subscripcion = new SubscripcionPremium();
	}

	public void cancelarPremium() {
		subscripcion = new SubscripcionGratuita();
	}

	public List<Atuendo> pedirSugerencia() {
		return guardarropas.stream().map(unGuardarropa -> unGuardarropa.generarSugerencias())
				.flatMap(atuendos -> atuendos.stream()).collect(Collectors.toList());
	}

	public List<Atuendo> pedirSugerenciaSegunClima(ServicioClimatico provedor) {
		return guardarropas.stream().map(unGuardarropa -> unGuardarropa.generarSugerenciasSegunClima(provedor))
				.flatMap(atuendos -> atuendos.stream()).collect(Collectors.toList());
	}

	public void aceptarAtuendo(Atuendo atuendo) {
		aceptados.add(atuendo);
		ultimaDecision = new DecisionAceptar();
	}

	public void rechazarAtuendo(Atuendo atuendo) {
		rechazados.add(atuendo);
		ultimaDecision = new DecisionRechazar();
	}

	public void deshacerDecision() {
		ultimaDecision.deshacerEn(this);
	}

	public void removerAceptado() {
		aceptados.poll();
	}

	public void removerRechazado() {
		rechazados.poll();
	}
}