package usuario;

import atuendo.Atuendo;
import prenda.ParteAbrigada;

public class PreferenciasDeAbrigo {
    private AdaptacionPuntuada abrigoCabeza;
    private AdaptacionPuntuada abrigoCuello;
    private AdaptacionPuntuada abrigoPecho;
    private AdaptacionPuntuada abrigoManos;
    private AdaptacionPuntuada abrigoPiernas;
    private AdaptacionPuntuada abrigoPies;


    public PreferenciasDeAbrigo() {
        this.abrigoCabeza = new AdaptacionPuntuada(25, 0.0, 0);
        this.abrigoCuello = new AdaptacionPuntuada(25, 0.0, 0);
        this.abrigoPecho = new AdaptacionPuntuada(25, 0.0, 0);
        this.abrigoManos = new AdaptacionPuntuada(25, 0.0, 0);
        this.abrigoPiernas = new AdaptacionPuntuada(25, 0.0, 0);
        this.abrigoPies = new AdaptacionPuntuada(25, 0.0, 0);
    }

    private double adaptacionTotal(){
        return abrigoPiernas.getNivelDeAdaptacion() +
                abrigoCuello.getNivelDeAdaptacion() +
                abrigoPecho.getNivelDeAdaptacion() +
                abrigoManos.getNivelDeAdaptacion() +
                abrigoCabeza.getNivelDeAdaptacion() +
                abrigoPies.getNivelDeAdaptacion();
    }

    public AdaptacionPuntuada getPuntaje(ParteAbrigada parte) {
        switch (parte) {
            case CABEZA:
                return abrigoCabeza;
            case PIES:
                return abrigoPies;
            case MANOS:
                return abrigoManos;
            case PECHO:
                return abrigoPecho;
            case CUELLO:
                return abrigoCuello;
            case PIERNAS:
                return abrigoPiernas;
        }
        return null;
    }


    public double obtenerNivelDeAdaptacion(double temperatura, Atuendo atuendo) { //mientras menor sea el numero de nivel de adaptacion, mejor es el atuendo en cuestion
        return Math.abs(this.adaptacionTotal() - (atuendo.nivelAbrigo() + 6.0*temperatura));
    }
}