package quemepongo;

import java.awt.Color;
import java.util.Set;

public class Main {
	
	public static void main(String[] args) {
		TipoPrenda remera = new TipoPrenda(Categoria.PARTE_SUPERIOR);
		Borrador borrador1 = new Borrador();
		borrador1.definirColorPrimario(new Color(255,255,0));
		borrador1.definirTipo(remera);
		borrador1.definirMaterial(Material.ALGODON);

		TipoPrenda pantalon = new TipoPrenda(Categoria.PARTE_INFERIOR);
		Borrador borrador2 = new Borrador();
		borrador2.definirColorPrimario(new Color(255,0,0));
		borrador2.definirTipo(pantalon);
		borrador2.definirMaterial(Material.JEAN);
		
		TipoPrenda zapatilla = new TipoPrenda(Categoria.CALZADO);
		Borrador borrador3 = new Borrador();
		borrador3.definirColorPrimario(new Color(55,123,60));
		borrador3.definirTipo(zapatilla);
		borrador3.definirMaterial(Material.CUERO);
		
		Prenda remeraAzul = borrador1.crearPrenda();
		Prenda jeanRojo = borrador2.crearPrenda();
		Prenda zapatillasVerde = borrador3.crearPrenda();
		
		TipoPrenda remera1 = new TipoPrenda(Categoria.PARTE_SUPERIOR);
		Borrador aborrador1 = new Borrador();
		aborrador1.definirColorPrimario(new Color(77,4,10));
		aborrador1.definirTipo(remera1);
		aborrador1.definirMaterial(Material.ALGODON);

		TipoPrenda pantalon1 = new TipoPrenda(Categoria.PARTE_INFERIOR);
		Borrador aborrador2 = new Borrador();
		aborrador2.definirColorPrimario(new Color(255,0,255));
		aborrador2.definirTipo(pantalon1);
		aborrador2.definirMaterial(Material.JEAN);
		
		TipoPrenda zapatilla1 = new TipoPrenda(Categoria.CALZADO);
		Borrador aborrador3 = new Borrador();
		aborrador3.definirColorPrimario(new Color(0,0,0));
		aborrador3.definirTipo(zapatilla1);
		aborrador3.definirMaterial(Material.CUERO);
		
		
		
		Prenda remeraHola = aborrador1.crearPrenda();
		Prenda jeanD = aborrador2.crearPrenda();
		Prenda zapatillasDPrenda = aborrador3.crearPrenda();
		
		Guardarropas guardarropa1 = new Guardarropas([remeraAzul], [jeanRojo], [zapatillasVerde], []);
		
		//Usuario azul = new Usuario(guardarropas);
		
	}

}