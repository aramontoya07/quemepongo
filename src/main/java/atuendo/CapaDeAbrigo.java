package atuendo;

import prenda.Categoria;
import prenda.Prenda;

public class CapaDeAbrigo {
	
	Prenda superior;
	Prenda inferior;
	Prenda calzado;
	Prenda accesorio;
	
	public CapaDeAbrigo() {
		
	}
	
	public boolean noTieneParte(Categoria categoria) {
		switch (categoria) {
		case PARTE_SUPERIOR:
			return superior.equals(null);
		case PARTE_INFERIOR:
			return inferior.equals(null);
		case CALZADO:
			return calzado.equals(null);
		case ACCESORIO:
			return accesorio.equals(null);
		default:
			return 1+1==0;
		}
	}
	
	public void agregarPrenda(Prenda prenda) {
		switch (prenda.getCategoria()) {
		case PARTE_SUPERIOR:
			superior = prenda;
			break;
		case PARTE_INFERIOR:
			inferior = prenda;
			break;
		case CALZADO:
			calzado = prenda;
			break;
		case ACCESORIO:
			accesorio = prenda;
			break;
		}
	}

}
