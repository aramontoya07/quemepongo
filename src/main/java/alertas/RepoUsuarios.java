package alertas;

import usuario.Usuario;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RepoUsuarios {
    private static RepoUsuarios repo;
    private Set<Usuario> listaUsuarios = new HashSet<>();

    public static RepoUsuarios getInstance() {
        if(repo == null) repo = new RepoUsuarios();
        return repo;
    }

    public void agregarUsuario(Usuario usuario){
        listaUsuarios.add(usuario);
    }

    public Set<Usuario> getTodos() {
        return listaUsuarios;
    }

    Set<Usuario> getInteresadosEn(String ubicacion) {
        return listaUsuarios.stream().filter(usuario -> usuario.leInteresaLaUbicacion(ubicacion)).collect(Collectors.toSet());
    }
}