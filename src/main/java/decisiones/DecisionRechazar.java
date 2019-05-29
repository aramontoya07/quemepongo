package decisiones;

import usuario.Usuario;

public class DecisionRechazar implements Decision {
	public void deshacerEn(Usuario usuario) {
		usuario.removerRechazado();
	}
}
