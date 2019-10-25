package usuario;

import db.EntidadPersistente;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class AdaptacionPuntuada{
    private Double nivelDeAdaptacion;
    private Integer puntaje;

    public AdaptacionPuntuada() {
        super();
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje){
        this.puntaje = puntaje;
    }

    public void setnivelDeAdaptacion(int temperatura, Double nivelDeAbrigo) {
        this.nivelDeAdaptacion = temperatura + nivelDeAbrigo;
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
