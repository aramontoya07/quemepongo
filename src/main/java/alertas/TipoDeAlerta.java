package alertas;



import notificaciones.Informante;
import usuario.Usuario;

public enum TipoDeAlerta {
    GRANIZO {
        public void notificarA(Informante informante, Usuario usuario) {
            informante.notificarGranizo(usuario);
        }
    },
    LLUVIA {
        public void notificarA(Informante informante, Usuario usuario) {
            informante.notificarTormenta(usuario);
        }
    },
    NIEVE {
        public void notificarA(Informante informante, Usuario usuario) {
            informante.notificarNevada(usuario);
        }
    },
    NINGUNA {
        public void notificarA(Informante informante, Usuario usuario) {
            //no hace nada, es como mi tp de operativos. //Es como mí grupo de operativos (by fede)
        }
    };

    public abstract void notificarA(Informante informante, Usuario usuario);
}
