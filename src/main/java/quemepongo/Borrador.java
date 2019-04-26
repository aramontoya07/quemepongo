package quemepongo;

import java.awt.Color;
import java.util.Objects;

import javax.management.RuntimeErrorException;

public class Borrador{
	TipoPrenda tipo;
	Material material;
	Trama trama = Trama.LISA;
	Color colorPrimario;
	Color colorSecundario;

	public void definirTipo(TipoPrenda tipo) {
		this.tipo = tipo;
	}
	
	public void definirMaterial(Material material) {
		if (tipo == null) {
			throw new RuntimeErrorException(null,"Se debe definir el tipo de prenda antes de elegir un material");
		}
		if (!tipo.permiteMaterial(material)) {
			throw new RuntimeErrorException(null,"El material no esta permitido para este tipo de prenda");
		}
		this.material = material;
	}

	public void definirColorPrimario(Color colorPrimario) {
		this.colorPrimario = colorPrimario;
	}

	public void definirColorSecundario(Color colorSecundario) {
		if (colorPrimario == null) {
			throw new RuntimeErrorException(null,"Se debe definir un color primario antes de elegir uno secundario");
		}
		if (colorPrimario.equals(colorSecundario)) {
			throw new RuntimeErrorException(null,"El color secundario debe diferir del primario");
		}
		this.colorSecundario = colorSecundario;
	}
	
	public void definirTrama(Trama trama) {
		this.trama = trama;
	}
	
	public Prenda crearPrenda() {
		if (tipo == null) throw new RuntimeErrorException(null,"tipo de prenda es obligatorio");
		if (material == null) throw new RuntimeErrorException(null,"material es obligatorio");
		if (trama == null) throw new RuntimeErrorException(null,"trama es obligatorio");
		if (colorPrimario == null) throw new RuntimeErrorException(null,"color primario es obligatorio");
		return new Prenda(tipo, material, trama, colorPrimario, colorSecundario);
	}
}
