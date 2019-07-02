package alertas;

import usuario.Usuario;

public interface Interesado {
    void notificarTormenta(Usuario usuario);
    void notificarGranizo(Usuario usuario);
}
