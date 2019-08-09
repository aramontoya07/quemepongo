package alertas;

import eventos.AsistenciaEvento;
import usuario.Usuario;

public interface Interesado {
    void notificarTormenta(Usuario usuario);
    void notificarGranizo(Usuario usuario);
    void notificarNevada(Usuario usuario);
    void notificarA(Usuario usuario, AsistenciaEvento evento);
    
}
