package alertas;

import clima.ServicioClimatico;

class Alertador {
    private RepoUsuarios repoUsuarios;

    Alertador(){
        repoUsuarios = RepoUsuarios.getInstance();
    }

    void comprobarAlertas(/*String ubicacion*/){ //La idea es obtener la ubicacion armando un repo de ubicaciones donde hay eventos cercanos
        ServicioClimatico.obtenerAlertas("Buenos Aires").forEach(alerta -> informarDe(alerta));
    }

    private void informarDe(Alerta alerta){
        repoUsuarios.getInteresadosEn(alerta.getUbicacion()).forEach(usuario -> usuario.actuarAnte(alerta));
    }
}
