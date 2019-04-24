package quemepongo;

import java.util.ArrayList;

public class TipoPrenda {
	public Categoria categoria;
	public ArrayList<Material> materialesPermitidos;

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
}