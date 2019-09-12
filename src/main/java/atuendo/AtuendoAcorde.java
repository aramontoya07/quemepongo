package atuendo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SugerenciasPosibles")
public class SugerenciasPosibles {

	Atuendo atuendo;
    TipoSugerencia tipo;

    public SugerenciasPosibles(Atuendo atuendo2, TipoSugerencia exacta) {
	}

    public Atuendo getAtuendo() {
        return atuendo;
    }

    public void setAtuendo(Atuendo atuendo) {
        this.atuendo = atuendo;
    }

    public TipoSugerencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoSugerencia tipo) {
        this.tipo = tipo;
    }
}
