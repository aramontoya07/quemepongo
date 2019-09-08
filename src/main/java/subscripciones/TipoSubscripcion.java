package subscripciones;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import usuario.EntidadPersistente;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_Suscripcion")
public abstract class TipoSubscripcion extends EntidadPersistente{
	public abstract boolean puedoAgregar(int numeroPrendas);
}