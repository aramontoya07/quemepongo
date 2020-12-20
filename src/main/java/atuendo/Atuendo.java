package atuendo;

import clima.Clima;
import db.EntidadPersistente;
import excepciones.PrendaException;
import prenda.ParteAbrigada;
import prenda.Prenda;
import usuario.Guardarropa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Atuendos")
public class Atuendo extends EntidadPersistente {

	@OneToOne(cascade = {CascadeType.PERSIST})
	private Guardarropa guardarropaOrigen;

	@ManyToOne(cascade = {CascadeType.PERSIST})
	private Prenda superior;

	@ManyToOne(cascade = {CascadeType.PERSIST})
	private Prenda inferior;

	@ManyToOne(cascade = {CascadeType.PERSIST})
	private Prenda calzado;

	@ManyToMany(cascade = {CascadeType.PERSIST})
	private List<Prenda> accesorios = new ArrayList<>();

	@ManyToMany(cascade = {CascadeType.PERSIST})
	private List<Prenda> capasAbrigos = new ArrayList<>();

	public Atuendo(){
	
	}

	public void setSuperior(Prenda superior) {
		this.superior = superior;
	}

	public void setInferior(Prenda inferior) {
		this.inferior = inferior;
	}

	public void setCalzado(Prenda calzado) {
		this.calzado = calzado;
	}

	public void setAccesorios(List<Prenda> accesorios) {
		this.accesorios = accesorios;
	}

	public void setCapasAbrigos(List<Prenda> capasAbrigos) {
		this.capasAbrigos = capasAbrigos;
	}

	public void setGuardarropaOrigen(Guardarropa guardarropaOrigen) {
		this.guardarropaOrigen = guardarropaOrigen;
	}

	public Guardarropa getGuardarropaOrigen() {
		return guardarropaOrigen;
	}

	public Prenda getSuperior() {
		return superior;
	}

	public Prenda getInferior() {
		return inferior;
	}

	public Prenda getCalzado() {
		return calzado;
	}

	public List<Prenda> getAccesorios() {
		return accesorios;
	}

	public List<Prenda> getCapasAbrigos() {
		return capasAbrigos;
	}

	public boolean esIgualA(Atuendo atuendo) {
		return superior.equals(atuendo.getSuperior())
				&& inferior.equals(atuendo.getInferior())
				&& calzado.equals(atuendo.getCalzado())
				&& accesorios.equals(atuendo.getAccesorios())
				&& capasAbrigos.equals(atuendo.getCapasAbrigos());
	}

	public Atuendo(Prenda pSuperior, Prenda pInferior, Prenda pCalzado, Guardarropa origen) {
		superior = pSuperior;
		inferior = pInferior;
		calzado = pCalzado;
		guardarropaOrigen = origen;
	}

	public Atuendo clonar(){
		Atuendo clon = new Atuendo(superior,inferior,calzado,guardarropaOrigen);
		clon.setAccesorios(new ArrayList<>(accesorios));
		clon.setAcapasAbrigos(new ArrayList<>(capasAbrigos));
		return clon;
	}

	private void setAccesorios(ArrayList<Prenda> prendas) {
		accesorios = prendas;
	}

	private void setAcapasAbrigos(ArrayList<Prenda> prendas) {
		capasAbrigos = prendas;
	}

	public boolean aceptaSuperponer(Prenda prenda){
		switch (prenda.getCategoria()){
			case PARTE_SUPERIOR:
				return ultimoSuperior().aceptaSuperponerPrenda(prenda);
			case ACCESORIO:
				try{
					return ultimoAccesorio().aceptaSuperponerPrenda(prenda);
				}catch(PrendaException e){
					return true;
				}
			case CALZADO: break;
			case PARTE_INFERIOR: break;
		}
		return false;
	}

	public Boolean noEsDeId(List<Integer> ids){
		return ids.stream().allMatch(id -> (!this.mismaId(id)));
	}

	public void agregarAbrigo(Prenda prenda){
		switch (prenda.getCategoria()){
			case PARTE_SUPERIOR:
				capasAbrigos.add(prenda);
				break;
			case ACCESORIO:
				accesorios.add(prenda);
				break;
			case CALZADO: break;
			case PARTE_INFERIOR: break;
		}
	}

	private Prenda ultimoAccesorio() throws PrendaException {
			if(accesorios.isEmpty()){
				throw new PrendaException("No hay accesorios en este atuendo. pinto.");
			}
			return accesorios.get(accesorios.size()-1);
	}

	private Prenda ultimoSuperior() {
		if(capasAbrigos.isEmpty()){
			return superior;
		}else{
			return capasAbrigos.get(capasAbrigos.size()-1);
		}
	}

	public int nivelAbrigo() {
		return obtenerPrendasTotales().stream().mapToInt(Prenda::nivelAbrigo).sum();
	}

	double nivelDeAdaptacionAlClima(Clima climaActual){
		return this.nivelAbrigo() - climaActual.nivelAbrigoRequerido();
	}

	public List<Prenda> obtenerPrendasTotales(){
		List<Prenda> prendasTotales = new ArrayList<>();
		prendasTotales.add(superior);
		prendasTotales.add(inferior);
		prendasTotales.add(calzado);
		prendasTotales.addAll(capasAbrigos);
		prendasTotales.addAll(accesorios);
		return prendasTotales;
	}

	public void liberarPrendasUsadas() {
		List <Prenda> prendas = obtenerPrendasTotales();
		prendas.forEach(prenda -> guardarropaOrigen.liberarPrenda(prenda));
	}

	public void marcarPrendasComoUsadas(){
		obtenerPrendasTotales().forEach(prenda -> guardarropaOrigen.usarPrenda(prenda));
	}

	public Integer abrigoEn(ParteAbrigada parte) {
		return obtenerPrendasTotales().stream().mapToInt(prenda-> (int) prenda.abrigoEnParte(parte)).sum();
	}

	public boolean estaDisponible() {
		return obtenerPrendasTotales().stream().allMatch(prenda -> guardarropaOrigen.prendaDisponible(prenda));
	}

	public String getRutaImagen() {
		return superior.getRutaImagen() + " " + inferior.getRutaImagen() + " " + calzado.getRutaImagen();
	}
}