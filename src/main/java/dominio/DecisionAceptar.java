package dominio;

public class DecisionAceptar implements Decision {
	public void deshacerEn(Usuario usuario) {
		usuario.removerAceptado();
	}
}