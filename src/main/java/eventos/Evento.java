package eventos;

import java.time.LocalDateTime;

public class Evento{
	private String tituloEvento;
    private LocalDateTime fecha;
    private String ubicacion;
    private final int horasEventoCercano = 12;

    public Evento(String titulo,LocalDateTime fecha, String ubicacion) {
    	this.tituloEvento = titulo;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
    }

	public boolean esEventoLejano() {
		return fecha.isBefore(LocalDateTime.now().minusHours(horasEventoCercano));
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public String getNombre() {
		return tituloEvento;
	}

	public String getUbicacion() {
		return ubicacion;
	}

}
