package notificaciones;

import eventos.AsistenciaEvento;
import usuario.Usuario;

public class InformanteMock implements Informante {

    @Override
    public void notificarTormenta(Usuario usuario) {
        usuario.marcarNotificado();
    }

    @Override
    public void notificarGranizo(Usuario usuario) {
        usuario.marcarNotificado();
    }

    @Override
    public void notificarNevada(Usuario usuario) {
        usuario.marcarNotificado();
    }

    @Override
    public void notificarA(Usuario usuario, AsistenciaEvento evento) {
        usuario.marcarNotificado();
    }
}
