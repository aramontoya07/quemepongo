package eventos;

import clima.ServicioClimatico;
import usuario.Usuario;

import java.util.TimerTask;

public class DarSugerenciaTime extends TimerTask {
    Usuario usuario;
    Evento evento;
    ServicioClimatico servicio;

    public DarSugerenciaTime(Usuario usuario, Evento evento, ServicioClimatico servicio) {
        this.usuario = usuario;
        this.evento = evento;
        this.servicio = servicio;
    }

    @Override
    public void run() {
        this.evento.setSugerenciasEvento(this.usuario.pedirSugerenciaSegunClima(servicio));
    }
}