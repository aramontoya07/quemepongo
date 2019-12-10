package prenda;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Imagen {

	private static int ancho = 100;
	private static int alto = 100;
	private int tipo;
	private BufferedImage imagenOriginal;

	public Imagen(String rutaImagen) throws IOException {
		this.imagenOriginal = ImageIO.read(new File(rutaImagen));
		this.tipo = imagenOriginal.getType();
	}

	public BufferedImage normalizar() {
		BufferedImage imagenNormalizada = new BufferedImage(ancho, alto, tipo);
		Graphics2D grafico = imagenNormalizada.createGraphics();
		grafico.drawImage(imagenOriginal, 0, 0, ancho, alto, null);
		grafico.dispose();
		return imagenNormalizada;
	}

	public int obtenerTipo() {
		return tipo;
	}
}
