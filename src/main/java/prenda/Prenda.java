package prenda;

import java.util.List;

public class Prenda {

	private TipoPrenda tipo;
	private Material material;
	private Trama trama;
	private ColorRGB colorPrimario;
	private ColorRGB colorSecundario;

	public Material getMaterial() {
		return material;
	}

	public Trama getTrama() {
		return trama;
	}
	
	public boolean aceptaSuperponerPrenda(Prenda prenda) {
		List<TipoPrenda> tiposAceptados = tipo.getTiposAceptados();
		if(tiposAceptados == null) {
			return false;
		}
		return tiposAceptados.contains(prenda.getTipo());
	}

	public boolean esBasica() {
		return tipo.esTipoBasico();
	}

	public ColorRGB getColorPrimario() {
		return colorPrimario;
	}

	public ColorRGB getColorSecundario() {
		return colorSecundario;
	}

	public Prenda(TipoPrenda tipo, Material material, Trama trama, ColorRGB colorPrimario, ColorRGB colorSecundario) {
		this.tipo = tipo;
		this.material = material;
		this.trama = trama;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
	}

	public TipoPrenda getTipo() {
		return this.tipo;
	}

	public Categoria getCategoria() {
		return tipo.getCategoria();
	}

	public Boolean esDeCategoria(Categoria categoria) {
		return tipo.getCategoria() == categoria;
	}
}
