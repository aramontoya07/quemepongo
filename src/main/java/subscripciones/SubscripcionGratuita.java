package subscripciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("suscripcion_gratuita")
public class SubscripcionGratuita extends TipoSubscripcion{
	private int cantidadMaxima = 5;
    
    public String nombreSuscripcion = "Gratuita";
    
    public String getNombreSuscripcion(){
        return nombreSuscripcion;
    }
    public void setNombreSuscripcion(String nuevo){
        nombreSuscripcion = nuevo;
    }
	public boolean puedoAgregar(int numeroDePrendas) {
		return numeroDePrendas < cantidadMaxima;
	}
}
