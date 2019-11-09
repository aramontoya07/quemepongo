package usuario;

import atuendo.Atuendo;
import db.EntidadPersistente;
import prenda.ParteAbrigada;

import javax.persistence.*;

@Entity
public class PreferenciasDeAbrigo extends EntidadPersistente {
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="nivelDeAdaptacion", column=@Column(name="NIVEL_CABEZA")),
            @AttributeOverride(name="puntaje", column=@Column(name="PUNTAJE_CABEZA"))
    })
    private AdaptacionPuntuada abrigoCabeza;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="nivelDeAdaptacion", column=@Column(name="NIVEL_CUELLO")),
            @AttributeOverride(name="puntaje", column=@Column(name="PUNTAJE_CUELLO"))
    })
    private AdaptacionPuntuada abrigoCuello;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="nivelDeAdaptacion", column=@Column(name="NIVEL_PECHO")),
            @AttributeOverride(name="puntaje", column=@Column(name="PUNTAJE_PECHO"))
    })
    private AdaptacionPuntuada abrigoPecho;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="nivelDeAdaptacion", column=@Column(name="NIVEL_MANOS")),
            @AttributeOverride(name="puntaje", column=@Column(name="PUNTAJE_MANOS"))
    })
    private AdaptacionPuntuada abrigoManos;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="nivelDeAdaptacion", column=@Column(name="NIVEL_PIERNAS")),
            @AttributeOverride(name="puntaje", column=@Column(name="PUNTAJE_PIERNAS"))
    })
    private AdaptacionPuntuada abrigoPiernas;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="nivelDeAdaptacion", column=@Column(name="NIVEL_PIES")),
            @AttributeOverride(name="puntaje", column=@Column(name="PUNTAJE_PIES"))
    })
    private AdaptacionPuntuada abrigoPies;


    public PreferenciasDeAbrigo() {
        this.abrigoCabeza = new AdaptacionPuntuada();
        abrigoCabeza.setnivelDeAdaptacion(0,25.0);
        abrigoCabeza.setPuntaje(0);
        this.abrigoCuello = new AdaptacionPuntuada();
        abrigoCuello.setnivelDeAdaptacion(0,25.0);
        abrigoCuello.setPuntaje(0);
        this.abrigoPecho = new AdaptacionPuntuada();
        abrigoPecho.setnivelDeAdaptacion(0,25.0);
        abrigoPecho.setPuntaje(0);
        this.abrigoManos = new AdaptacionPuntuada();
        abrigoManos.setnivelDeAdaptacion(0,25.0);
        abrigoManos.setPuntaje(0);
        this.abrigoPiernas = new AdaptacionPuntuada();
        abrigoPiernas.setnivelDeAdaptacion(0,25.0);
        abrigoPiernas.setPuntaje(0);
        this.abrigoPies = new AdaptacionPuntuada();
        abrigoPies.setnivelDeAdaptacion(0,25.0);
        abrigoPies.setPuntaje(0);
    }

    private double adaptacionTotal(){
        return abrigoPiernas.getNivelDeAdaptacion() +
                abrigoCuello.getNivelDeAdaptacion() +
                abrigoPecho.getNivelDeAdaptacion() +
                abrigoManos.getNivelDeAdaptacion() +
                abrigoCabeza.getNivelDeAdaptacion() +
                abrigoPies.getNivelDeAdaptacion();
    }

    public void setPreferenciasDeAbrigo(AdaptacionPuntuada ap) {
        this.abrigoCabeza = ap;
        this.abrigoCuello = ap;
        this.abrigoPecho = ap;
        this.abrigoManos =ap;
        this.abrigoPiernas = ap;
        this.abrigoPies = ap;
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