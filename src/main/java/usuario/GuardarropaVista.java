package usuario;

import prenda.Categoria;
import prenda.Prenda;

import java.util.Set;

public class GuardarropaVista{
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Prenda> getSuperiores() {
        return superiores;
    }

    public void setSuperiores(Set<Prenda> superiores) {
        this.superiores = superiores;
    }

    public Set<Prenda> getInferiores() {
        return inferiores;
    }

    public void setInferiores(Set<Prenda> inferiores) {
        this.inferiores = inferiores;
    }

    public Set<Prenda> getCalzado() {
        return calzado;
    }

    public void setCalzado(Set<Prenda> calzado) {
        this.calzado = calzado;
    }

    public Set<Prenda> getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(Set<Prenda> accesorios) {
        this.accesorios = accesorios;
    }

    public Set<Prenda> superiores;
    public Set<Prenda> inferiores;
    public Set<Prenda> calzado;
    public Set<Prenda> accesorios;

    public GuardarropaVista(Guardarropa guardarropa){
        id = guardarropa.getId();
        superiores = guardarropa.getPrendasDeParte(Categoria.PARTE_SUPERIOR);
        inferiores = guardarropa.getPrendasDeParte(Categoria.PARTE_INFERIOR);
        calzado = guardarropa.getPrendasDeParte(Categoria.CALZADO);
        accesorios = guardarropa.getPrendasDeParte(Categoria.ACCESORIO);
    }
}
