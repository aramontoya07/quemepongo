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
//para tests
	public Prenda getSuperior() {
		return superior;
	}

	public Prenda getInferior() {
		return inferior;
	}

	public Prenda getCalzado() {
		return calzado;
	}

	public Prenda getAccesorio() {
		return accesorio;
	}
	
	
}
