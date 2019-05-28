package decisiones;

import dominio.Usuario;

public class DecisionRechazar implements Decision{
	public void deshacerEn(Usuario usuario) {
		usuario.removerRechazado();
	}
}
