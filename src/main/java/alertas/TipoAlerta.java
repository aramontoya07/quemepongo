package alertas;



import usuario.Usuario;

public enum TipoAlerta {

    GRANIZO {

        public void notificarA(Interesado interesado, Usuario usuario) {
            interesado.notificarGranizo(usuario);
        }
    },

    TORMENTA {

        public void notificarA(Interesado interesado, Usuario usuario) {
            interesado.notificarTormenta(usuario);
        }
    };

    public abstract void notificarA(Interesado interesado, Usuario usuario);
}
