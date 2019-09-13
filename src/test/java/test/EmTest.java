package test;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	
	@Test
	public void persistirSuscripcion() {
        TipoSubscripcion suscripcion = new SubscripcionGratuita();
        EntityManagerHelper.getEntityManager().persist(suscripcion);
		  EntityManagerHelper.commit();
	}
	
	@Test
	public void persistirPreferenciasDeAbrigo() {
		AdaptacionPuntuada ap = new AdaptacionPuntuada(1,1.0,1);
		PreferenciasDeAbrigo abrigo = new PreferenciasDeAbrigo();
		abrigo.setPreferenciasDeAbrigo(ap);
		EntityManagerHelper.getEntityManager().persist(ap);
		EntityManagerHelper.getEntityManager().persist(abrigo);
		  EntityManagerHelper.commit();
	}
	
	@Test 
	public void persitirEvento() {
		Evento evento = new Evento("Prueba",LocalDateTime.now(),"UTN",Frecuencia.UNICO);
		EntityManagerHelper.getEntityManager().persist(evento);
		  EntityManagerHelper.commit();
	}
	
	
}
