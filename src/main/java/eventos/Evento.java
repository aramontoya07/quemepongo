package eventos;



import atuendo.Sugerencias;
import clima.ServicioClimatico;
import excepciones.EventoLejanoException;
import excepciones.NingunaSugerenciaParaEventoException;
import usuario.Usuario;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;


public class Evento extends TimerTask {
    private Calendar fecha;
    private String ubicacion;
    private Usuario user;
    private ServicioClimatico provedor;
    private Set<Sugerencias> sugerenciasEvento = new HashSet<Sugerencias>();


    public Evento(Usuario user, Calendar fecha, String ubicacion,ServicioClimatico provedor) {
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.user = user;
        this.provedor = provedor;
    }

    public void run(){
        sugerenciasEvento = user.pedirSugerenciaSegunClima(provedor,ubicacion);
    }

<<<<<<< HEAD
    public Calendar dateEventoCercano(){ //fecha a partir de la cual el evento esta cercano
        Calendar fechaEvento = (Calendar) fecha.clone();
        fechaEvento.add(Calendar.HOUR_OF_DAY, -12);
        return fechaEvento;
    }

    public Set<Sugerencias> pedirSugerencias(){
        if(Calendar.getInstance().getTime().before(this.dateEventoCercano().getTime())) throw new EventoLejanoException(); //Lo pidio muy antes
        if(sugerenciasEvento.isEmpty()) throw new NingunaSugerenciaParaEventoException(); //No hay sugerencias que se adapten
        return sugerenciasEvento;
=======
    public Calendar dateEvento(){
        Calendar fechaEvento = (Calendar) fecha.clone();
        fechaEvento.add(Calendar.HOUR_OF_DAY, -12);
        return fechaEvento;
>>>>>>> 0fa6e354bd54cd2b3805134cc0f2721d258a4978
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
<<<<<<< HEAD
=======

    public Set<Sugerencias> pedirSugerencias(){
        if(Calendar.getInstance().getTime().before(this.dateEvento().getTime())) throw new EventoLejanoException();
        if(sugerenciasEvento.isEmpty()) throw new NingunaSugerenciaParaEventoException();
        return sugerenciasEvento;
    }
>>>>>>> 0fa6e354bd54cd2b3805134cc0f2721d258a4978
}
