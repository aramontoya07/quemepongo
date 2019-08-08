package prenda;

import excepciones.BorradorException;

public class Borrador {
	TipoPrenda tipo;
	Material material;
	Trama trama = Trama.LISA;
	ColorRGB colorPrimario;
	ColorRGB colorSecundario;

	public void definirTipo(TipoPrenda tipo) {
		this.tipo = tipo;
	}

	public void definirMaterial(Material material)throws BorradorException {
		if (tipo == null) {
			throw new BorradorException("No se puede definir el material de una prenda antes que su tipo");
		}
		if (!tipo.permiteMaterial(material)) {
			throw new BorradorException("El material que se quiso definir para la prenda no es permitido por su tipo");
		}
		this.material = material;
	}

	public void definirColorPrimario(ColorRGB colorPrimario) {
		this.colorPrimario = colorPrimario;
	}

	public void definirColorSecundario(ColorRGB colorSecundario) throws BorradorException {
		if (colorPrimario == null) {
			throw new BorradorException("No se puede definir el color secundario de una prenda sin antes definir su color primario");
		}
		if (colorPrimario.equals(colorSecundario)) {
			throw new BorradorException("El color secundario de una prenda no puede ser igual al primario");
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

	public Prenda crearPrenda() throws BorradorException {
		if (tipo == null)
			throw new BorradorException("Es necesario definir el tipo de prenda antes de crearla");
		if (material == null)
			throw new BorradorException("Es necesario definir el material de la prenda antes de crearla");
		if (colorPrimario == null)
			throw new BorradorException("Es necesario definir el color primario de la prenda antes de crearla");
		return new Prenda(tipo, material, trama, colorPrimario, colorSecundario);
	}
}
