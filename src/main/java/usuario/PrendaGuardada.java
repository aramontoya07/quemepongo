package usuario;

import prenda.Categoria;
import prenda.Prenda;

public class PrendaGuardada {
    Prenda prenda;
    EstadoPrenda estado;

    public PrendaGuardada(Prenda _prenda, EstadoPrenda _estado) {
        prenda = _prenda;
        estado = _estado;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public EstadoPrenda getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrenda estado) {
        this.estado = estado;
    }

    public Categoria getCategoria(){
        return prenda.getCategoria();
    }
}
