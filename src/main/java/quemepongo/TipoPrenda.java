package quemepongo;

import java.util.ArrayList;

public class TipoPrenda {
	public Categoria categoria;
	public ArrayList<Material> materialesPermitidos; //ESTO ES UNA MIERDA

	public TipoPrenda(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	
	public boolean permiteMaterial(Material material) {
		return materialesPermitidos.contains(material);
	}

TipoPrenda remera = new TipoPrenda(Categoria.PARTE_SUPERIOR);

}