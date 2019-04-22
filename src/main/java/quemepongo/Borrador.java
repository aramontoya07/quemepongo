package quemepongo;

import java.awt.Color;
import java.util.Objects;

import javax.management.RuntimeErrorException;

public class Borrador{
	TipoPrenda tipo;
	Material material;
	Trama trama = Trama.LISA;
	Color colorPrimario;
	Color colorSecundario = null;

	public void definirTipo(TipoPrenda tipo) {
		this.tipo = Objects.requireNonNull(tipo, "tipo de prenda es obligatorio"); //Preguntar!
	}

	public void definirMaterial(Material material) {
		if (!tipo.permiteMaterial(material)) {
			throw new RuntimeErrorException(null,"El material no esta permitido para este tipo de prenda");
		}
		this.material = material;
	}

	public void definirColorPrimario(Color colorPrimario) {
		this.colorPrimario = colorPrimario;
	}

	public void definirColorSecundario(Color colorSecundario) {
		if (colorSecundario != colorPrimario) {
			throw new RuntimeErrorException(null,"El color secundario debe diferir del primario");
		}
		this.colorSecundario = colorSecundario;
	}
	
	public void definirTrama(Trama trama) {
		this.trama = trama;
	}
	
	public Prenda crearPrenda() {
		this.material = Objects.requireNonNull(material, "material es obligatorio");
		this.trama = Objects.requireNonNull(trama, "trama es obligatorio");
		this.colorPrimario = Objects.requireNonNull(colorPrimario, "color es obligatorio");
		Prenda prenda = new Prenda(tipo, material, trama, colorPrimario, colorSecundario);
		return prenda;
	}
}
