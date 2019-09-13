package test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import atuendo.Atuendo;
import atuendo.EstadoAtuendo;
import atuendo.SugerenciasClima;
import atuendo.UsoAtuendo;
import decisiones.Decision;
import eventos.AsistenciaEvento;
import eventos.Calendario;
import notificaciones.Informante;
import org.checkerframework.common.aliasing.qual.LeakedToResult;
import org.hibernate.mapping.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import prenda.*;
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
	@BeforeEach
	public void setUp() {
		setear();
	}

	@Test
	public void persistirUsuario(){
		EntityManagerHelper.getEntityManager().persist(usuario1);
		EntityManagerHelper.getEntityManager().persist(peter);
		EntityManagerHelper.commit();
	}

	/*@Disabled
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
		EntityManagerHelper.getEntityManager().persist(fiesta);
		EntityManagerHelper.getEntityManager().persist(superclasico);
		EntityManagerHelper.getEntityManager().persist(finalDelMundial);
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
	}

	 @Test
	 public void persistirColor() {
		 EntityManagerHelper.getEntityManager().persist(unColor);
		 EntityManagerHelper.getEntityManager().persist(otroColor);
		 EntityManagerHelper.commit();
	 }


	 public void persistirTipoPrenda(TipoPrenda tipo) {
		 EntityManagerHelper.getEntityManager().persist(tipo);
		 EntityManagerHelper.commit();
	 }*/

	/*@Test
	public void persistirRemeraAzul() {

		remeraAzul.setTipo(remera);
		remeraAzul.setColorPrimario(unColor);
		remeraAzul.setColorSecundario(otroColor);
		remeraAzul.setTrama(Trama.RAYADA);
		persistirTipoPrenda(remera);
		persistirColor();
		EntityManagerHelper.getEntityManager().persist(remeraAzul);
		EntityManagerHelper.commit();
	}

	@Test
	public void persistirZapatillasConverse() {

		zapatillasConverse.setTipo(zapatillas);
		zapatillasConverse.setColorPrimario(unColor);
		zapatillasConverse.setColorSecundario(otroColor);
		zapatillasConverse.setTrama(Trama.RAYADA);
		persistirTipoPrenda(zapatillas);
		persistirColor();
		EntityManagerHelper.getEntityManager().persist(zapatillasConverse);
		EntityManagerHelper.commit();
	}

	/*@Test
	public void persistirPantalonJean() {

		pantalonJean.setTipo(zapatillas);
		pantalonJean.setColorPrimario(unColor);
		pantalonJean.setColorSecundario(otroColor);
		pantalonJean.setTrama(Trama.RAYADA);
		persistirTipoPrenda(pantalon);
		persistirColor();
		EntityManagerHelper.getEntityManager().persist(pantalonJean);
		EntityManagerHelper.commit();
	}*/
	
//	public void persistirPrenda(Prenda prenda,TipoPrenda tipo, ColorRGB unColor, ColorRGB otroColor,Trama trama) {
//
//		prenda.setTipo( tipo);
//		prenda.setColorPrimario(unColor);
//		prenda.setColorSecundario(otroColor);
//		prenda.setTrama(trama);
//		persistirTipoPrenda( tipo);
//		persistirColor();
//		EntityManagerHelper.getEntityManager().persist(tipo);
//		EntityManagerHelper.commit();
//	}
	
	/*@Test
	public void persistirAtuendo(){
		persistirRemeraAzul();
		persistirZapatillasConverse();
		persistirPantalonJean();
		persistirGuardarropa();
		EntityManagerHelper.getEntityManager().persist(atuendo);
		EntityManagerHelper.commit();
		
	}
    @Test
	public void persistirUsuario() { //Falta
		EntityManagerHelper.getEntityManager().persist(usuario1);
		EntityManagerHelper.commit();

	}*/








}
