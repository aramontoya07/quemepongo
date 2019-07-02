package alertas;

import clima.Clima;

public class Alertador {
    RepoUsuarios repoUsuarios;
    Clima clima;

    public void comprobarAlertas() {
        this.clima.getAlertas().forEach(a -> informarDe(a));
    }

    public void informarDe(TipoAlerta alerta) {
        this.repoUsuarios.getTodos().forEach(usuario -> usuario.actuarAnte(alerta));
    }
}
