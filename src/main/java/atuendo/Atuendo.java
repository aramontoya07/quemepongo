package atuendo;

import java.util.ArrayList;
import java.util.List;

import clima.Clima;
import excepciones.PrendaFaultException;
import prenda.ParteAbrigada;
import prenda.Prenda;
import usuario.Guardarropa;

public class Atuendo{
	private Guardarropa guardarropaOrigen;
	private Prenda superior;
	private Prenda inferior;
	private Prenda calzado;
	private List<Prenda> accesorios = new ArrayList<>();
	private List<Prenda> capasAbrigos = new ArrayList<>();
	private Integer rangoDeAceptacion = 10;

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
		clon.setRangoDeAceptacion(rangoDeAceptacion);
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
				}catch(PrendaFaultException e){
					return true;
				}
		}
		return false;
	}

	public void agregarAbrigo(Prenda prenda){
		switch (prenda.getCategoria()){
			case PARTE_SUPERIOR:
				capasAbrigos.add(prenda);
				break;
			case ACCESORIO:
				accesorios.add(prenda);
				break;
		}
	}

	private Prenda ultimoAccesorio() throws PrendaFaultException{
			if(accesorios.isEmpty()){
				throw new PrendaFaultException("No hay accesorios en este atuendo. pinto.");
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
		obtenerPrendasTotales().forEach(prenda -> guardarropaOrigen.liberarPrenda(prenda));
	}

	public void marcarPrendasComoUsadas(){
		obtenerPrendasTotales().forEach(prenda -> guardarropaOrigen.usarPrenda(prenda));
	}

	public Integer abrigoEn(ParteAbrigada parte) {
		return obtenerPrendasTotales().stream().mapToInt(prenda-> (int) prenda.abrigoEnParte(parte)).sum();
	}

	public void setRangoDeAceptacion(Integer rangoDeAceptacion) {
		this.rangoDeAceptacion = rangoDeAceptacion;
	}


	public boolean estaDisponible() {
		return obtenerPrendasTotales().stream().allMatch(prenda -> guardarropaOrigen.existePrenda(prenda));
	}
}