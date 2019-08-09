package alertas;



import usuario.Usuario;

public enum TipoDeAlerta {
    GRANIZO {
        public void notificarA(Interesado interesado, Usuario usuario) {
            interesado.notificarGranizo(usuario);
        }
    },
    LLUVIA {
        public void notificarA(Interesado interesado, Usuario usuario) {
            interesado.notificarTormenta(usuario);
        }
    },
    NIEVE {
        public void notificarA(Interesado interesado, Usuario usuario) {
            interesado.notificarNevada(usuario);
        }
    };

    public abstract void notificarA(Interesado interesado, Usuario usuario);
}
