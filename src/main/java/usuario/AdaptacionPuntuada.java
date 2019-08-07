package usuario;

public class AdaptacionPuntuada {
    private Integer nivelDeAdaptacion;
    private Integer puntaje;

    public AdaptacionPuntuada(Integer nivelDeAbrigo, Integer Temperatura, Integer unPuntaje) {
        nivelDeAdaptacion = Temperatura + nivelDeAbrigo;
        puntaje = unPuntaje;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {

        this.puntaje = puntaje;
    }

    public Integer getNivelDeAdaptacion() {
        return nivelDeAdaptacion;
    }

    public void setearElMejor(AdaptacionPuntuada nuevaAdaptacion) {
        if (nuevaAdaptacion.getPuntaje() > this.getPuntaje()){
            nivelDeAdaptacion = nuevaAdaptacion.getNivelDeAdaptacion();
            puntaje = nuevaAdaptacion.getPuntaje();
        }
    }
}
