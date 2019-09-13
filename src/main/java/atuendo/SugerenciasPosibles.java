package atuendo;

import db.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "SugerenciasPosibles")
public class SugerenciasPosibles extends EntidadPersistente {

    @OneToOne //Chequear esto
	Atuendo atuendo;
    @Enumerated
    TipoSugerencia tipo;

    public SugerenciasPosibles(Atuendo atuendo2, TipoSugerencia tipo2) {
        atuendo = atuendo2;
        tipo = tipo2;
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
