package eventos;



import atuendo.Sugerencias;
import clima.ServicioClimatico;
import excepciones.EventoLejanoException;
import excepciones.NingunaSugerenciaParaEventoException;
import usuario.Usuario;

import java.util.*;


public class Evento {
    private Calendar fecha;
    private String ubicacion;
    private Set<Sugerencias> sugerenciasEvento = new HashSet();

    public Evento(Calendar fecha, String ubicacion) {
        this.fecha = fecha;
        this.ubicacion = ubicacion;
    }

    public void generarSugerencias(Usuario user, ServicioClimatico servicio){
        Timer tarea = new Timer();
        tarea.schedule(new DarSugerenciaTime(user, this, servicio), this.dateEvento());
        return;
    }

    public Date dateEvento(){
        Calendar fechaPedirSugerencia = (Calendar) fecha.clone();
        fechaPedirSugerencia.add(Calendar.HOUR_OF_DAY, -12);
        return fechaPedirSugerencia.getTime();
    }

    public Calendar getFecha() {
        return fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setSugerenciasEvento(Set<Sugerencias> sugerenciasEvento) {
        this.sugerenciasEvento = sugerenciasEvento;
    }

    public Set<Sugerencias> pedirSugerencias() {
        if(Calendar.getInstance().getTime().before(this.dateEvento())) throw new EventoLejanoException();
        if(sugerenciasEvento.isEmpty()) throw new NingunaSugerenciaParaEventoException();
        return sugerenciasEvento;
    }
}
