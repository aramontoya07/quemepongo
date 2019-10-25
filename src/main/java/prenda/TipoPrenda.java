package prenda;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import db.EntidadPersistente;
import db.EntityManagerHelper;

@Entity
public class TipoPrenda extends EntidadPersistente{

	@Enumerated(EnumType.STRING)
	public TipoUso TipoBasico;

	public int nivelAbrigo;

	@Enumerated(EnumType.STRING)
	private Categoria categoria;

	@ElementCollection(targetClass = Material.class)
	@Enumerated(EnumType.STRING)
	private List<Material> materialesPermitidos;

	@ManyToMany(cascade = {CascadeType.PERSIST})
	private List<TipoPrenda> tiposAceptados = new ArrayList<TipoPrenda>();

	public TipoPrenda(Categoria categoria, List<Material> materialesPermitidos, int nivelAbrigo, TipoUso tipoBasico){
		super();
		TipoBasico = tipoBasico;
		this.nivelAbrigo = nivelAbrigo;
		this.categoria = categoria;
		this.materialesPermitidos = materialesPermitidos;
	}

	public boolean esPrimaria() {
		return TipoBasico.equals(TipoUso.PRIMARIA);
	}

	public int nivelAbrigo() {
		return nivelAbrigo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public boolean permiteMaterial(Material material) {
		return materialesPermitidos.contains(material);
	}

	public List<TipoPrenda> getTiposAceptados() {
		return tiposAceptados;
	}

	public void setTiposAceptados(List<TipoPrenda> tiposAceptados) {
		this.tiposAceptados = tiposAceptados;
	}

	public void persistir() {

		EntityManagerHelper.getEntityManager().persist(this);
	}
}