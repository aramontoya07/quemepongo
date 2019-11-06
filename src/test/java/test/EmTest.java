package test;

import db.EntityManagerHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import repositorios.RepositorioUsuarios;
import usuario.Usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmTest extends SetUp {

	@BeforeEach
	public void setUp(){
		setear();
	}

	@Disabled
	@Test
	public void persistirCosas(){
		EntityManagerHelper.beginTransaction();


		EntityManagerHelper.getEntityManager().persist(usuario1);

		EntityManagerHelper.commit();

		assertTrue(EntityManagerHelper.getEntityManager().contains(usuario1));
	}

	@Test
	public void repoTest(){
		Usuario usuario = new Usuario();
		usuario.setMail("prueba");
		RepositorioUsuarios.persistirUsuario(usuario);
		int id = usuario.getId();
		String idChar = Integer.toString(id);
		Usuario otroUsuario = RepositorioUsuarios.obtenerUsuario(idChar);
		assertEquals("prueba", otroUsuario.getMail());
	}

	@Disabled
	@Test
	public void estefallanda() {
		String uno = "1";
		int unoo = Integer.parseInt(uno);
		assertEquals(usuario1,EntityManagerHelper.getEntityManager().find(Usuario.class, unoo));
	}
}