package subscripciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("suscripcion_premium")
public class SubscripcionPremium extends TipoSubscripcion {
    
    public String nombreSuscripcion = "Premium";
	public boolean puedoAgregar(int numeroPrendas) {
		return true;
	}
    
    public String getNombreSuscripcion(){
        return nombreSuscripcion;
    }
    public void setNombreSuscripcion(String nuevo){
        nombreSuscripcion = nuevo;
    }
}
