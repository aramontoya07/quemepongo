package eventos;

import java.util.Calendar;

public class Evento{
    private Calendar fecha;
    private String ubicacion;

    public Evento(Calendar fecha, String ubicacion) {
        this.fecha = fecha;
        this.ubicacion = ubicacion;
    }
    
    public Calendar dateEventoCercano(){ 
        Calendar fechaEvento = (Calendar) fecha.clone();
        fechaEvento.add(Calendar.HOUR_OF_DAY, -12);
        return fechaEvento;
    }

	public boolean esEventoLejano() {
		return false;
	}
	
	public Calendar getFecha() {
		return fecha;
	}

	public String getUbicacion() {
		return ubicacion;
	}

}
