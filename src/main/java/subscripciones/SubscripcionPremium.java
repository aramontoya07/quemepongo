package subscripciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("suscripcion_premium")
public class SubscripcionPremium extends TipoSubscripcion {

	public boolean puedoAgregar(int numeroPrendas) {
		return true;
	}

}
