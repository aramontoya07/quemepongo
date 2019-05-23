package quemepongo;

import java.util.ArrayList;
import java.util.List;

public class TipoPrenda {
	private List<TipoPrenda> tiposAceptados;
	public int nivelAbrigo;
	public Categoria categoria;
	public List<Material> materialesPermitidos;

	public TipoPrenda(Categoria categoria, ArrayList<Material> materialesPermitidos) {
		this.categoria = categoria;
		this.materialesPermitidos = materialesPermitidos;
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