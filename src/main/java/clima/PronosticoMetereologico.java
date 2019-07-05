package clima;

public class PronosticoMetereologico {
	private String categoria;
	
	public PronosticoMetereologico (String categoria) {
		this.categoria = categoria;
	}
	
	public String getPronostico() {
		return categoria;
	}
}
