package alertas;

import usuario.Usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepoUsuarios { //TODO
    private static RepoUsuarios repo;
    public Set<Usuario> listaUsuarios = new HashSet<>();

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
}
