package prenda;

import java.util.List;

import javax.persistence.*;

import db.EntidadPersistente;
import db.EntityManagerHelper;

import java.awt.image.BufferedImage;

@Entity
@Table(name = "Prendas")
public class Prenda extends EntidadPersistente {

	@ManyToOne(cascade = {CascadeType.PERSIST})
	private TipoPrenda tipo;

	@Enumerated(EnumType.STRING)
	private Material material;

	@Enumerated(EnumType.STRING)
	private Trama trama;

	@ManyToOne(cascade = {CascadeType.PERSIST})
	private ColorRGB colorPrimario;

	@ManyToOne(cascade = {CascadeType.PERSIST})
	private ColorRGB colorSecundario;

	@Transient
	private BufferedImage imagenNormalizada;

	@Enumerated(EnumType.STRING)
	private ParteAbrigada pateAbrigada;

	private String rutaImagen;

	public Prenda(TipoPrenda tipo, Material material, Trama trama, ColorRGB colorPrimario, ColorRGB colorSecundario) {
		this.tipo = tipo;
		this.material = material;
		this.trama = trama;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public boolean aceptaSuperponerPrenda(Prenda prenda) {
		List<TipoPrenda> tiposAceptados = tipo.getTiposAceptados();
		return tiposAceptados.contains(prenda.getTipo());
	}

	public Boolean esDeCategoria(Categoria categoria) {
		return tipo.getCategoria() == categoria;
	}

	public int nivelAbrigo() {
		return tipo.nivelAbrigo();
	}

	public boolean esPrimaria() {
		return tipo.esPrimaria();
	}

	public Material getMaterial() {
		return material;
	}

	public List<TipoPrenda> getTiposAceptados(){
		return tipo.getTiposAceptados();
	}

	public Trama getTrama() {
		return trama;
	}

	public ColorRGB getColorPrimario() {
		return colorPrimario;
	}

	public ColorRGB getColorSecundario() {
		return colorSecundario;
	}

	public TipoPrenda getTipo() {
		return this.tipo;
	}

	public Categoria getCategoria() {
		return tipo.getCategoria();
	}

	public BufferedImage getImagenNormalizada() {
		return imagenNormalizada;
	}
	
	public void agregarImagen(Imagen imagenOriginal) {
		this.imagenNormalizada = imagenOriginal.normalizar();
	}

    public Object abrigoEnParte(ParteAbrigada parte) {
		if(pateAbrigada == parte){
			return nivelAbrigo();
		}else{
			return 0;
		}
    }

	public void setTipo(TipoPrenda tipo) {
		this.tipo = tipo;
	}

	public void setTrama(Trama trama) {
		this.trama = trama;
	}

	public void setColorPrimario(ColorRGB colorPrimario) {
		this.colorPrimario = colorPrimario;
	}

	public void setColorSecundario(ColorRGB colorSecundario) {
		this.colorSecundario = colorSecundario;
	}

	public ParteAbrigada getPateAbrigada() {
		return pateAbrigada;
	}

	public void setPateAbrigada(ParteAbrigada pateAbrigada) {
		this.pateAbrigada = pateAbrigada;
	}

	public void persistir() {
		tipo.persistir();
		EntityManagerHelper.getEntityManager().persist(this);
	}
}
