package alertas;

import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RepoUsuarios { //TODO
    private static RepoUsuarios repo;
    public Set<Usuario> listaUsuarios = new HashSet<>();

    public static RepoUsuarios getInstance() {
        if(repo == null) repo = new RepoUsuarios();
        return repo;
    }

    public void agregarUsuario(Usuario usuario){
        repo.add(usuario);
    }

    public Set<Usuario> getTodos() {
        return listaUsuarios;
    }
}
