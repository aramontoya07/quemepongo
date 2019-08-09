package notificaciones;

import eventos.AsistenciaEvento;
import usuario.Usuario;

public interface Informante {
    void notificarTormenta(Usuario usuario);
    void notificarGranizo(Usuario usuario);
    void notificarNevada(Usuario usuario);
    void notificarA(Usuario usuario, AsistenciaEvento evento);
}
