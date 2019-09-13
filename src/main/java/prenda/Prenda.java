package prenda;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import db.EntidadPersistente;

import java.awt.image.BufferedImage;

@Entity
@Table(name = "Prendas")
public class Prenda extends EntidadPersistente{

	@ManyToOne
	private TipoPrenda tipo;

	@Enumerated(EnumType.STRING)
	private Material material;

	@Enumerated(EnumType.STRING)
	private Trama trama;

	@ManyToOne
	private ColorRGB colorPrimario;

	@ManyToOne
	private ColorRGB colorSecundario;

	@Transient
	private BufferedImage imagenNormalizada;

	@Enumerated(EnumType.STRING)
	private ParteAbrigada pateAbrigada;


	public Prenda(TipoPrenda tipo, Material material, Trama trama, ColorRGB colorPrimario, ColorRGB colorSecundario) {
		this.tipo = tipo;
		this.material = material;
		this.trama = trama;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
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
}
