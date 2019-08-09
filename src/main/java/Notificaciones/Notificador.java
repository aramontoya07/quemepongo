package Notificaciones;

import eventos.AsistenciaEvento;
import usuario.Usuario;

public class Notificador implements Informante{
    ServicioNotificador notificationService;

    public void notificarTormenta(Usuario usuario) {
        notificationService.notificar("No olvides llevar tu paraguas!");
    }

    public void notificarGranizo(Usuario usuario) {
        notificationService.notificar("No salgas en auto!");
    }

    public void notificarNevada(Usuario usuario) {
        notificationService.notificar("Hora de hacer muñecos de nieve!");
    }

    public void notificarA(Usuario usuario, AsistenciaEvento evento) {
        notificationService.notificar("Tus sugerencias para el evento" + evento.toString() + "estan listas");
    }

}
