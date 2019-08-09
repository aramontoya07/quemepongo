package alertas;

import clima.ServicioClimatico;

public class Alertador {
    private RepoUsuarios repoUsuarios;
    private String ubicacion = "London";

    public Alertador(){
        repoUsuarios = RepoUsuarios.getInstance();
    }

    public void comprobarAlertas() {
        ServicioClimatico.obtenerAlertas(ubicacion).forEach(alerta -> informarDe(alerta.getTipo()));
    }

    public void informarDe(TipoDeAlerta alerta) {
        this.repoUsuarios.getTodos().forEach(usuario -> usuario.actuarAnte(alerta));
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
