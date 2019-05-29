package decisiones;

import usuario.Usuario;

public class DecisionAceptar implements Decision {
	public void deshacerEn(Usuario usuario) {
		usuario.removerAceptado();
	}
}