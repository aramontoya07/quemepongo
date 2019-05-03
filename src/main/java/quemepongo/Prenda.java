package quemepongo;


public class Prenda {
	private TipoPrenda tipo;
	private Material material;
	private Trama trama;
	private ColorRGB colorPrimario;
	private ColorRGB colorSecundario;

	public Prenda(TipoPrenda tipo, Material material,Trama trama, ColorRGB colorPrimario, ColorRGB colorSecundario) {
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
	
	public Boolean esDeCategoria (Categoria categoria){
		return tipo.getCategoria() == categoria;
	}	
}
