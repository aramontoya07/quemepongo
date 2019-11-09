package decisiones;

import javax.persistence.Embeddable;

import usuario.Usuario;
@Embeddable
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

	public void deshacerEn(Usuario usuario) {
	}
}
