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
		setTipo();
	}

	public void setTipo() {
		this.tipo = imagenOriginal.getType();
	}

	public int obtenerTipo(BufferedImage imagenOriginal) {
		int tipo = imagenOriginal.getType();
		return tipo;
	}

	public BufferedImage normalizar() {
		BufferedImage imagenNormalizada = new BufferedImage(ancho, alto, tipo);
		Graphics2D grafico = imagenNormalizada.createGraphics();
		grafico.drawImage(imagenOriginal, 0, 0, ancho, alto, null);
		grafico.dispose();
		return imagenNormalizada;
	}
	/*
	 * public void leerImagen(String ruta) throws IOException {
	 * 
	 * BufferedImage imagenOriginal = ImageIO.read(new File(ruta));
	 * 
	 * }
	 */

	/*
	 * public void guardarImagenJPG(String ruta, BufferedImage imagenOriginal) {
	 * BufferedImage resizeImageJpg = normalizar(imagenOriginal, tipo);
	 * ImageIO.write(resizeImageJpg, "jpg", new File(ruta)); } //throw new
	 * NoSePuedeGuardarImagenException
	 * 
	 * public void guardarImagenPNG(String ruta, BufferedImage imagenOriginal) {
	 * BufferedImage resizeImagePng = normalizar(imagenOriginal, tipo);
	 * ImageIO.write(resizeImagePng, "png", new File(ruta)); }//throw new
	 * NoSePuedeGuardarImagenException
	 */

	/*
	 * private static BufferedImage normalizar(BufferedImage imagenOriginal, int
	 * tipo){ BufferedImage imagenNormalizada = new BufferedImage(ancho, alto,
	 * tipo); Graphics2D grafico = imagenNormalizada.createGraphics();
	 * grafico.drawImage(imagenOriginal, 0, 0, ancho, alto, null);
	 * grafico.dispose(); return imagenNormalizada;
	 * 
	 * }
	 */
}
