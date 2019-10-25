package test;

import db.EntityManagerHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuario.Usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmTest extends SetUp {

	@BeforeEach
	public void setUp(){
		setear();
	}

	@Test
	public void persistirCosas(){
		EntityManagerHelper.beginTransaction();
		EntityManagerHelper.getEntityManager().persist(remera);
		EntityManagerHelper.getEntityManager().persist(remeraDesabrigada);
		EntityManagerHelper.getEntityManager().persist(remeraDe30);
		EntityManagerHelper.getEntityManager().persist(gorro);
		EntityManagerHelper.getEntityManager().persist(pantalon);
		EntityManagerHelper.getEntityManager().persist(pantalonDesabrigado);
		EntityManagerHelper.getEntityManager().persist(zapatilla);
		EntityManagerHelper.getEntityManager().persist(zapatillaDesabrigada);
		EntityManagerHelper.getEntityManager().persist(ojota);
		EntityManagerHelper.getEntityManager().persist(anteojo);
		EntityManagerHelper.getEntityManager().persist(campera);
		EntityManagerHelper.getEntityManager().persist(remeraAzul);

		EntityManagerHelper.getEntityManager().persist(colorGenerico);

		EntityManagerHelper.getEntityManager().persist(remeraAzul);
		EntityManagerHelper.getEntityManager().persist(remeraDeportiva);
		EntityManagerHelper.getEntityManager().persist(camperaGris);
		EntityManagerHelper.getEntityManager().persist(jeanRojo);
		EntityManagerHelper.getEntityManager().persist(jeanNegro);
		EntityManagerHelper.getEntityManager().persist(ojotas);
		EntityManagerHelper.getEntityManager().persist(zapatillasVerde);
		EntityManagerHelper.getEntityManager().persist(anteojos);
		EntityManagerHelper.getEntityManager().persist(anteojosDeSol);
		EntityManagerHelper.getEntityManager().persist(gorroLana);
		EntityManagerHelper.getEntityManager().persist(remeraDesabrigadaAzul);
		EntityManagerHelper.getEntityManager().persist(remeraDe30Azul);
		EntityManagerHelper.getEntityManager().persist(pantalonDesabrigadoAzul);
		EntityManagerHelper.getEntityManager().persist(zapatillasDesabrigadasAzules);

		EntityManagerHelper.getEntityManager().persist(guardarropa);
		EntityManagerHelper.getEntityManager().persist(otroGuardarropa);

		EntityManagerHelper.getEntityManager().persist(superclasico);
		EntityManagerHelper.getEntityManager().persist(fiesta);
		EntityManagerHelper.getEntityManager().persist(finalDelMundial);
		EntityManagerHelper.getEntityManager().persist(evento);

		EntityManagerHelper.getEntityManager().persist(atuendo1);

		EntityManagerHelper.getEntityManager().persist(asistenciaMundial);
		EntityManagerHelper.getEntityManager().persist(asistenciaSuperclasico);
		EntityManagerHelper.getEntityManager().persist(asistenciaFiesta);

		EntityManagerHelper.getEntityManager().persist(calendario);
		usuario1.setMail("nuevomail");

		EntityManagerHelper.getEntityManager().persist(usuario1);

		EntityManagerHelper.commit();

		assertTrue(EntityManagerHelper.getEntityManager().contains(usuario1));
	}

	//@Disabled
	//@Test
	//public void estefallanda() {
	//	assertEquals(usuario1,EntityManagerHelper.getEntityManager().find(Usuario.class, 0));
	//}
}