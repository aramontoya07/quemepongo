package prenda;

public class Borrador{
	TipoPrenda tipo;
	Material material;
	Trama trama = Trama.LISA;
	ColorRGB colorPrimario;
	ColorRGB colorSecundario;

	public void definirTipo(TipoPrenda tipo) {
		this.tipo = tipo;
	}
	
	public void definirMaterial(Material material) {
		if (tipo == null) { // TODO subclasifiquen excepciones. No usen RuntimeErrorException, es tipo.... whaaaat
			throw new RuntimeException("Se debe definir el tipo de prenda antes de elegir un material");
		}
		if (!tipo.permiteMaterial(material)) {
			throw new RuntimeException("El material no esta permitido para este tipo de prenda");
		}
		this.material = material;
	}

	public void definirColorPrimario(ColorRGB colorPrimario) {
		this.colorPrimario = colorPrimario;
	}

	public void definirColorSecundario(ColorRGB colorSecundario) {
		if (colorPrimario == null) {
			System.out.println("exc 1");
			throw new RuntimeException("Se debe definir un color primario antes de elegir uno secundario");
		}
		if (colorPrimario.equals(colorSecundario)) {
			System.out.println("exc 2");
			throw new RuntimeException("El color secundario debe diferir del primario");
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
	
	public Prenda crearPrenda() {
		if (tipo == null) throw new RuntimeException("tipo de prenda es obligatorio");
		if (material == null) throw new RuntimeException("material es obligatorio");
		if (colorPrimario == null) throw new RuntimeException("color primario es obligatorio");
		return new Prenda(tipo, material, trama, colorPrimario, colorSecundario);
	}
}
