package prenda;

import excepciones.ColorPrimarioObligatorioException;
import excepciones.ColorSecundarioIgualAPrimarioException;
import excepciones.ColorSecundarioSinPrimarioException;
import excepciones.MaterialAntesQueTipoPrendaException;
import excepciones.MaterialNoPermitidoException;
import excepciones.MaterialObligatorioException;
import excepciones.TipoPrendaObligatorioException;

public class Borrador{
	TipoPrenda tipo;
	Material material;
	Trama trama = Trama.LISA;
	ColorRGB colorPrimario;
	ColorRGB colorSecundario;

	public void definirTipo(TipoPrenda tipo) {
		this.tipo = tipo;
	}
	
	public void definirMaterial(Material material) {
		if (tipo == null) {
			throw new MaterialAntesQueTipoPrendaException();
		}
		if (!tipo.permiteMaterial(material)) {
			throw new MaterialNoPermitidoException();
		}
		this.material = material;
	}

	public void definirColorPrimario(ColorRGB colorPrimario) {
		this.colorPrimario = colorPrimario;
	}

	public void definirColorSecundario(ColorRGB colorSecundario) {
		if (colorPrimario == null) {
			throw new ColorSecundarioSinPrimarioException();
		}
		if (colorPrimario.equals(colorSecundario)) {
			throw new ColorSecundarioIgualAPrimarioException();
		}
		this.colorSecundario = colorSecundario;
	}
	
	public void definirTrama(Trama trama) {
		this.trama = trama;
	}
	
	public void crearBorrador(ColorRGB color, TipoPrenda tipo, Material material) {
		this.definirColorPrimario(color);
		this.definirTipo(tipo);
		this.definirMaterial(material);
	}
	
	public Prenda crearPrenda() {
		if (tipo == null) throw new TipoPrendaObligatorioException();
		if (material == null) throw new MaterialObligatorioException();
		if (colorPrimario == null) throw new ColorPrimarioObligatorioException();
		return new Prenda(tipo, material, trama, colorPrimario, colorSecundario);
	}
}
