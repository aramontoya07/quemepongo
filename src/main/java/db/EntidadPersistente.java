package db;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass // esto hace q no me cree otra tabla sino que me traiga los atributos de la
					// superclase a la clase q lo implementa
public class EntidadPersistente {
	@Id
	@GeneratedValue
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}
}
