package alertas;

import javax.persistence.Entity;
import javax.persistence.Transient;
import db.EntidadPersistente;
@Entity
public class Alerta extends EntidadPersistente{
	
	@Transient
    private TipoDeAlerta tipo;
    private String ubicacion;

    public Alerta(TipoDeAlerta tipoAlerta, String ubicacionElegida){
        ubicacion = ubicacionElegida;
        tipo = tipoAlerta;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public TipoDeAlerta getTipo() {
        return tipo;
    }
}