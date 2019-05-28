package decisiones;

import dominio.Usuario;

public class DecisionAceptar implements Decision {
	public void deshacerEn(Usuario usuario) {
		usuario.removerAceptado();
	}
}