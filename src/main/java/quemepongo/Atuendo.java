package quemepongo;

public class Atuendo {
	public Prenda superior; 
	public Prenda inferior;
	public Prenda calzado;
	public Prenda accesorio;
	
	public Atuendo(Prenda superior, Prenda inferior, Prenda calzado, Prenda accesorio) {
		this.superior = superior;
		this.inferior = inferior;
		this.calzado = calzado;
		this.accesorio = accesorio;
	}
	
	public boolean esAtuendoValido(Atuendo atuendo){
		return 
			this.superior.esDeCategoria(Categoria.PARTE_SUPERIOR) && 
			this.inferior.esDeCategoria(Categoria.PARTE_INFERIOR) && 
			this.calzado.esDeCategoria(Categoria.CALZADO) &&
			this.accesorio.esDeCategoria(Categoria.ACCESORIO);
	}
}
