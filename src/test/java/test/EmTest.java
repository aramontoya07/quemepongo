package test;

import db.EntityManagerHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import repositorios.RepositorioUsuarios;
import usuario.Usuario;

import javax.persistence.TypedQuery;

import java.util.List;

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


		EntityManagerHelper.getEntityManager().persist(usuario1);
		usuario1.setNombre("Jorge");

		EntityManagerHelper.commit();

		assertTrue(EntityManagerHelper.getEntityManager().contains(usuario1));
	}

	@Disabled
	@Test
	public void testQuery(){
		String mail = "usuario@gmail.com";
		String contrasenia = "469925199";
		TypedQuery <Usuario> query = EntityManagerHelper.getEntityManager()
				.createQuery("SELECT u FROM Usuario u WHERE mail = '" + mail +"' AND contrasenia = '" + contrasenia + "'", Usuario.class);
		Usuario usuario = query.getSingleResult();
		assertEquals(7, usuario.getId());
	}

	@Disabled
	@Test
	public void repoTest(){
		Usuario usuario = new Usuario();
		usuario.setMail("usuario@gmail.com");
		usuario.setContrasenia("469925199");

		RepositorioUsuarios.persistirUsuario(usuario);
		int id = usuario.getId();
		String idChar = Integer.toString(id);
		Usuario otroUsuario = RepositorioUsuarios.obtenerUsuario("1");
		assertEquals("usuario1@gmail.com", otroUsuario.getMail());
	}

	@Disabled
	@Test
	public void estefallanda() {
		String uno = "1";
		int unoo = Integer.parseInt(uno);
		assertEquals(usuario1,EntityManagerHelper.getEntityManager().find(Usuario.class, unoo));
	}
}