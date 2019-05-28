package prenda;

import java.util.List;

public class TipoPrenda {
	public boolean TipoBasico;
	public int nivelAbrigo;
	public Categoria categoria;
	public List<Material> materialesPermitidos;
	private List<TipoPrenda> tiposAceptados;


	public TipoPrenda(Categoria categoria, List<Material> materialesPermitidos,int nivelAbrigo,boolean tipoBasico) {
		super();
		TipoBasico = tipoBasico;
		this.nivelAbrigo = nivelAbrigo;
		this.categoria = categoria;
		this.materialesPermitidos = materialesPermitidos;
	}

	public boolean esTipoBasico() {
		return TipoBasico;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	
	public boolean permiteMaterial(Material material) {
		return materialesPermitidos.contains(material);
	}

	public List<TipoPrenda> getTiposAceptados() {
		return tiposAceptados;
	}

	public void setTiposAceptados(List<TipoPrenda> tiposAceptados) {
		this.tiposAceptados = tiposAceptados;
	}
}