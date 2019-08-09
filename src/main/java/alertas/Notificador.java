package alertas;

import eventos.AsistenciaEvento;
import usuario.Usuario;

public class Notificador implements Interesado, NotificadorSMS {
    NotificationService notificationService;

    public void notificarTormenta(Usuario usuario) {
        this.mostrarNotificacion("No olvides llevar tu paraguas!");
    }

    public void notificarGranizo(Usuario usuario) {
        this.mostrarNotificacion("No salgas en auto!");
    }

    public void notificarNevada(Usuario usuario) {
        this.mostrarNotificacion("Hora de hacer muñecos de nieve!");
    }

    public void mostrarNotificacion(String mensaje) {
        notificationService.notify(mensaje);
    }

    public void notificarA(Usuario usuario, AsistenciaEvento evento) {

    }

}
