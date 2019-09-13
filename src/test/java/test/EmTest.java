package test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import atuendo.EstadoAtuendo;
import atuendo.SugerenciasClima;
import atuendo.UsoAtuendo;
import decisiones.Decision;
import eventos.AsistenciaEvento;
import eventos.Calendario;
import notificaciones.Informante;
import org.hibernate.mapping.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import prenda.ColorRGB;
import prenda.Material;
import prenda.Prenda;
import prenda.Trama;
import usuario.AdaptacionPuntuada;
import usuario.Guardarropa;
import usuario.PreferenciasDeAbrigo;
import usuario.Usuario;
import db.EntityManagerHelper;
import eventos.Evento;
import eventos.Frecuencia;
import subscripciones.SubscripcionGratuita;
import subscripciones.TipoSubscripcion;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmTest extends SetUp {
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
	private void setUp() {
		setear();
	}

	Usuario usuario1 = new Usuario();

	Calendario calendario = new Calendario();
	Evento superclasico = new Evento("Superclasico",LocalDateTime.now(),"Cancha de Boca",Frecuencia.UNICO);
	Evento fiesta = new Evento("Fiesta",LocalDateTime.now(),"Capital",Frecuencia.UNICO);
	Evento finalDelMundial = new Evento("Final del mundial",LocalDateTime.now(),"China",Frecuencia.UNICO);
	Evento evento = new Evento("Prueba",LocalDateTime.now(),"UTN",Frecuencia.UNICO);
	AsistenciaEvento asistenciaMundial = new AsistenciaEvento(finalDelMundial);
	AsistenciaEvento asistenciaSuperclasico = new AsistenciaEvento(superclasico);
	AsistenciaEvento asistenciaFiesta = new AsistenciaEvento(fiesta);
	AdaptacionPuntuada ap = new AdaptacionPuntuada(1,1.0,1);

	PreferenciasDeAbrigo abrigo = new PreferenciasDeAbrigo();
	TipoSubscripcion suscripcion = new SubscripcionGratuita();

	ColorRGB unColor = new ColorRGB(77, 4, 10);
	ColorRGB otroColor = new ColorRGB(77, 4, 10);



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

        EntityManagerHelper.getEntityManager().persist(finalDelMundial);
        EntityManagerHelper.getEntityManager().persist(asistenciaMundial);
        EntityManagerHelper.commit();
    }

    @Test
    public void persistirCalendario() {

        calendario.getEventos().add(asistenciaSuperclasico);
        calendario.getEventos().add(asistenciaFiesta);
        EntityManagerHelper.getEntityManager().persist(fiesta);
        EntityManagerHelper.getEntityManager().persist(superclasico);
        EntityManagerHelper.getEntityManager().persist(asistenciaSuperclasico);
        EntityManagerHelper.getEntityManager().persist(asistenciaFiesta);
        EntityManagerHelper.getEntityManager().persist(calendario);
        EntityManagerHelper.commit();
    }

    @Test
	public void persistirSugerenciasClima() {
		SugerenciasClima unaSugerencia = new SugerenciasClima();
		EntityManagerHelper.getEntityManager().persist(unaSugerencia);
		EntityManagerHelper.commit();
	}

    @Test
	public void persistirGuardarropa() {
		EntityManagerHelper.getEntityManager().persist(guardarropa);
		EntityManagerHelper.commit();
	}

	 /*@Test public void recuperandoUnGuardaropas(){
		Guardarropa guardarropa = new Guardarropa();
			EntityManagerHelper.createQuery("from Guardarropas where id = 3").
			getSingleResult();
	 		assertEquals(3, guardarropa.getId());
	}*/

	/*@Test
	public void persistirRemeraAzul() {
		//Prenda remeraAzul = new Prenda(remera, Material.ALGODON, Trama.RAYADA, unColor,  otroColor);
		remeraAzul.setTipo(remera);
		remeraAzul.setColorPrimario(unColor);
		remeraAzul.setColorSecundario(otroColor);
		remeraAzul.setTrama(Trama.RAYADA);
		EntityManagerHelper.getEntityManager().persist(unColor);
		EntityManagerHelper.getEntityManager().persist(otroColor);
		EntityManagerHelper.getEntityManager().persist(remeraAzul);
		EntityManagerHelper.commit();
	}*/

    /*@Test
	public void persistirUsuario() { //Falta
		usuario1.setUltimaDecision(Decision.ACEPTAR);
		usuario1.getInformantes().add(Informante.MockSMS);
		usuario1.getInformantes().add(Informante.CasillaDeMails);
		usuario1.agregarGuardarropa(guardarropa);
		usuario1.agregarPrendas(guardarropa, prendasOrdenables);
		usuario1.setMail("usuario1@gmail.com");
		usuario1.setNotificado(true);
		usuario1.setSubscripcion(suscripcion);
		usuario1.setCalendarioEventos(calendario);
		usuario1.setPreferenciasDeAbrigo(abrigo);
		abrigo.setPreferenciasDeAbrigo(ap);
		EntityManagerHelper.getEntityManager().persist(suscripcion);
		EntityManagerHelper.getEntityManager().persist(usuario1);
		EntityManagerHelper.getEntityManager().persist(calendario);
		EntityManagerHelper.getEntityManager().persist(guardarropa);
		EntityManagerHelper.getEntityManager().persist(prendasOrdenables);
		EntityManagerHelper.getEntityManager().persist(abrigo);
		EntityManagerHelper.getEntityManager().persist(ap);
		EntityManagerHelper.commit();

	}*/








}
