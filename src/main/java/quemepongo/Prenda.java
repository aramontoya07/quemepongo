package quemepongo;
import java.awt.Color;

public class Prenda {
	private TipoPrenda tipo;
	private Material material;
	private Trama trama;
	private Color colorPrimario;
	private Color colorSecundario;
	private String nombre;

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
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		// TODO redefinir el toString para que describa al objeto
		return super.toString();
	}
	

}
