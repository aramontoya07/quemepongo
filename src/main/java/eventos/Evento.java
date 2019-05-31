package eventos;



import atuendo.Sugerencias;
import clima.ServicioClimatico;
import excepciones.EventoLejanoException;
import excepciones.NingunaSugerenciaParaEventoException;
import usuario.Usuario;

import java.util.*;


public class Evento extends TimerTask{
    private Calendar fecha;
    private String ubicacion;
    private Usuario user;
    private ServicioClimatico provedor;
    private Set<Sugerencias> sugerenciasEvento = new HashSet();


    public Evento(Usuario user, Calendar fecha, String ubicacion,ServicioClimatico provedor) {
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.user = user;
        this.provedor = provedor;
    }

    public void run(){
        sugerenciasEvento = user.pedirSugerenciaSegunClima(provedor,ubicacion);
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

    public Set<Sugerencias> pedirSugerencias(){
        if(Calendar.getInstance().getTime().before(this.dateEvento())) throw new EventoLejanoException();
        if(sugerenciasEvento.isEmpty()) throw new NingunaSugerenciaParaEventoException();
        return sugerenciasEvento;
    }
}
