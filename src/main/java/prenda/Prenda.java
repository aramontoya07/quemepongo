package prenda;

import java.util.List;
import java.awt.image.BufferedImage;

public class Prenda {

	private TipoPrenda tipo;
	private Material material;
	private Trama trama;
	private ColorRGB colorPrimario;
	private ColorRGB colorSecundario;
	private BufferedImage imagenNormalizada;

	public Prenda(TipoPrenda tipo, Material material, Trama trama, ColorRGB colorPrimario, ColorRGB colorSecundario) {
		this.tipo = tipo;
		this.material = material;
		this.trama = trama;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
	}

	public boolean aceptaSuperponerPrenda(Prenda prenda) {
		List<TipoPrenda> tiposAceptados = tipo.getTiposAceptados();
		return tiposAceptados.contains(prenda.getTipo());
	}

	public Boolean esDeCategoria(Categoria categoria) {
		return tipo.getCategoria() == categoria;
	}

	public int nivelAbrigo() {
		return tipo.nivelAbrigo();
	}

	public boolean esPrimaria() {
		return tipo.esPrimaria();
	}

	public Material getMaterial() {
		return material;
	}

	public Trama getTrama() {
		return trama;
	}

	public ColorRGB getColorPrimario() {
		return colorPrimario;
	}

	public ColorRGB getColorSecundario() {
		return colorSecundario;
	}

	public TipoPrenda getTipo() {
		return this.tipo;
	}

	public Categoria getCategoria() {
		return tipo.getCategoria();
	}

	public void agregarImagen(Imagen imagenOriginal) {
		this.imagenNormalizada = imagenOriginal.normalizar();
	}

}
