package quemepongo;
import java.awt.Color;

public class Prenda {
	TipoPrenda tipo;
	Material material;
	Trama trama;
	Color colorPrimario;
	Color colorSecundario;

	public Prenda(TipoPrenda tipo, Material material,Trama trama, Color colorPrimario, Color colorSecundario) {
		this.tipo = tipo;
		this.material = material;
		this.trama = trama;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
	}

	//Esto es para el test
	public TipoPrenda getTipo() {
		return tipo;
	}
	
	public Categoria getCategoria() {
		return tipo.getCategoria();
	}

}
