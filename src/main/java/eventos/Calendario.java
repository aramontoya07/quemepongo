package eventos;

import java.util.List;

import usuario.Usuario;

public class Calendario{
	private List<AsistenciaEvento> eventos;
	private Usuario user;
	
	void agregarEvento(Evento evento) {
		eventos.add(new AsistenciaEvento(evento));
		
	}
}
