package decisiones;

import usuario.Usuario;

public enum Decision {

	ACEPTAR(){
		public void deshacerEn(Usuario usuario) {
			usuario.removerAceptado();
		}
	},
	RECHAZAR(){
		public void deshacerEn(Usuario usuario) {
			usuario.removerRechazado();
		}
	};
}
