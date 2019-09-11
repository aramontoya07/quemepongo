package usuario;

import javax.persistence.Entity;

@Entity
public class AdaptacionPuntuada extends EntidadPersistente {
    private Double nivelDeAdaptacion;
    private Integer puntaje;

    public AdaptacionPuntuada(Integer nivelDeAbrigo, Double Temperatura, Integer unPuntaje) {
        nivelDeAdaptacion = Temperatura + nivelDeAbrigo;
        puntaje = unPuntaje;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {

        this.puntaje = puntaje;
    }

    public Double getNivelDeAdaptacion() {
        return nivelDeAdaptacion;
    }

    public void setearElMejor(AdaptacionPuntuada nuevaAdaptacion) {
        if (nuevaAdaptacion.getPuntaje() > this.getPuntaje()){
            nivelDeAdaptacion = nuevaAdaptacion.getNivelDeAdaptacion();
            puntaje = nuevaAdaptacion.getPuntaje();
        }
    }
}
