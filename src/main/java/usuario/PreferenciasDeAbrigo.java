package usuario;

public class PreferenciasDeAbrigo {
    private Integer abrigoCabeza;
    private Integer abrigoCuello;
    private Integer abrigoPecho;
    private Integer abrigoManos;
    private Integer abrigoPiernas;
    private Integer abrigoPies;

    private Integer puntaje;

    public PreferenciasDeAbrigo() {
        this.abrigoCabeza = 0;
        this.abrigoCuello = 0;
        this.abrigoPecho = 0;
        this.abrigoManos = 0;
        this.abrigoPiernas = 0;
        this.abrigoPies = 0;
    }

    public Integer getPuntaje(){
        return puntaje;
    }

    public void setPuntaje(Integer puntaje){
        this.puntaje = puntaje;
    }

    public Integer getAbrigoCabeza() {
        return abrigoCabeza;
    }

    public Integer getAbrigoCuello() {
        return abrigoCuello;
    }

    public Integer getAbrigoPecho() {
        return abrigoPecho;
    }

    public Integer getAbrigoManos() {
        return abrigoManos;
    }

    public Integer getAbrigoPiernas() {
        return abrigoPiernas;
    }

    public Integer getAbrigoPies() {
        return abrigoPies;
    }

    public void setAbrigoCabeza(Integer abrigoCabeza) {
        this.abrigoCabeza = abrigoCabeza;
    }

    public void setAbrigoCuello(Integer abrigoCuello) {
        this.abrigoCuello = abrigoCuello;
    }

    public void setAbrigoPecho(Integer abrigoPecho) {
        this.abrigoPecho = abrigoPecho;
    }

    public void setAbrigoManos(Integer abrigoManos) {
        this.abrigoManos = abrigoManos;
    }

    public void setAbrigoPiernas(Integer abrigoPiernas) {
        this.abrigoPiernas = abrigoPiernas;
    }

    public void setAbrigoPies(Integer abrigoPies) {
        this.abrigoPies = abrigoPies;
    }
}