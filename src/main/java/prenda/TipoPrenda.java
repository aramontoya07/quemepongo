package prenda;

import java.util.ArrayList;
import java.util.List;

public class TipoPrenda {
	public TipoUso TipoBasico;
	public int nivelAbrigo;
	private Categoria categoria;
	private List<Material> materialesPermitidos;
	private List<TipoPrenda> tiposAceptados = new ArrayList<TipoPrenda>();

	public TipoPrenda(Categoria categoria, List<Material> materialesPermitidos,int nivelAbrigo,TipoUso tipoBasico) {
		super();
		TipoBasico = tipoBasico;
		this.nivelAbrigo = nivelAbrigo;
		this.categoria = categoria;
		this.materialesPermitidos = materialesPermitidos;
	}

	public boolean esPrimaria() {
		return TipoBasico.equals(TipoUso.PRIMARIA);
	}
	
	public int nivelAbrigo() {
		return nivelAbrigo;
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