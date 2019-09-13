package test;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import eventos.AsistenciaEvento;
import eventos.Calendario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import prenda.Prenda;
import usuario.AdaptacionPuntuada;
import usuario.PreferenciasDeAbrigo;
import usuario.Usuario;
import db.EntityManagerHelper;
import eventos.Evento;
import eventos.Frecuencia;
import subscripciones.SubscripcionGratuita;
import subscripciones.TipoSubscripcion;

public class EmTest {
   /* @Test public void persistir1UsuarioTest() {

        Usuario usuario1 = new Usuario();
        usuario1.setMail("usuario1@gmail.com");
        usuario1.setNotificado(false);

        EntityManagerHelper.beginTransaction();
	    EntityManagerHelper.getEntityManager().persist(usuario1);
	    EntityManagerHelper.commit();
    }*/
	@BeforeEach

	public void inicio () {
		EntityManagerHelper.beginTransaction();

	}

	Calendario calendario = new Calendario();
	Evento superclasico = new Evento("Prueba3",LocalDateTime.now(),"Capital",Frecuencia.UNICO);
	Evento eventoB = new Evento("Prueba4",LocalDateTime.now(),"Capital",Frecuencia.UNICO);
	Evento evento2 = new Evento("Prueba2",LocalDateTime.now(),"UTN",Frecuencia.UNICO);
	Evento evento = new Evento("Prueba",LocalDateTime.now(),"UTN",Frecuencia.UNICO);
	AsistenciaEvento asistencia = new AsistenciaEvento(evento2);
	AsistenciaEvento asistenciaA = new AsistenciaEvento(superclasico);
	AsistenciaEvento asistenciaB = new AsistenciaEvento(eventoB);
	AdaptacionPuntuada ap = new AdaptacionPuntuada(1,1.0,1);
	PreferenciasDeAbrigo abrigo = new PreferenciasDeAbrigo();
	TipoSubscripcion suscripcion = new SubscripcionGratuita();

	@Test
	public void persistirSuscripcion() {

        EntityManagerHelper.getEntityManager().persist(suscripcion);
        EntityManagerHelper.commit();
	}
	
	@Test
	public void persistirPreferenciasDeAbrigo() {

		abrigo.setPreferenciasDeAbrigo(ap);
		EntityManagerHelper.getEntityManager().persist(ap);
		EntityManagerHelper.getEntityManager().persist(abrigo);
		  EntityManagerHelper.commit();
	}
	
	@Test 
	public void persitirEvento() {

		EntityManagerHelper.getEntityManager().persist(evento);
		  EntityManagerHelper.commit();
	}

	@Test
    public void persistirAsistenciaEvento() {

        EntityManagerHelper.getEntityManager().persist(evento2);
        EntityManagerHelper.getEntityManager().persist(asistencia);
        EntityManagerHelper.commit();
    }

    @Test
    public void persistirCalendario() {

        calendario.getEventos().add(asistenciaA);
        calendario.getEventos().add(asistenciaB);
        EntityManagerHelper.getEntityManager().persist(eventoB);
        EntityManagerHelper.getEntityManager().persist(superclasico);
        EntityManagerHelper.getEntityManager().persist(asistenciaA);
        EntityManagerHelper.getEntityManager().persist(asistenciaB);
        EntityManagerHelper.getEntityManager().persist(calendario);
        EntityManagerHelper.commit();
    }



}
