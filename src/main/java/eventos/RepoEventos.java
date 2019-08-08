package eventos;

import java.util.ArrayList;
import java.util.List;

public class RepoEventos {
	
		private static List<Evento> eventos = new ArrayList<>();
	    private static RepoEventos instance;
	    
	    private RepoEventos(){}
	    
	    public static RepoEventos getInstance(){
	        if(instance == null){
	            instance = new RepoEventos();
	        }
	        return instance;
	    }
	    
	    public static void agendar(Evento evento) {
	    	eventos.add(evento);
	    }
}