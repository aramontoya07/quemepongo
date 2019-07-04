package decisiones;

import usuario.PreferenciasDeAbrigo;
import usuario.Usuario;

public class DecisionPuntuar implements Decision {
    PreferenciasDeAbrigo preferenciaAntigua;
    public DecisionPuntuar(PreferenciasDeAbrigo preferenciasDeAbrigo) {
        preferenciaAntigua = preferenciasDeAbrigo;
    }

    public void deshacerEn(Usuario usuario) {
        usuario.removerPuntuado(preferenciaAntigua);
    }
}