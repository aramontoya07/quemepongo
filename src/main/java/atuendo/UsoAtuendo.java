package atuendo;

import java.time.LocalDateTime;

import javax.persistence.*;

import atuendo.Atuendo;
import clima.ServicioClimatico;
import db.EntidadPersistente;
import usuario.EstadoAtuendo;

@Entity
@Table(name = "Usos")
public class UsoAtuendo extends EntidadPersistente{
    //el usoAtuendo sirve para cuando el usuario acepta la sugerencia hecha por el guardaropa, no existe la opcion de que haya varios usos para el mismo atuendo. es OneToOne
    @OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    public Atuendo atuendo;

    @Enumerated(EnumType.STRING)
    public EstadoAtuendo estado;

    private boolean puntuado = false;

    public LocalDateTime fechaDeUso;
    public String fechaFormateada;

    public UsoAtuendo(){
    }

    public Double temperaturaDeUso;

    public UsoAtuendo(Atuendo atuendo2, EstadoAtuendo estado2) {
        fechaDeUso = LocalDateTime.now();
        atuendo = atuendo2;
        estado = estado2;
        temperaturaDeUso = ServicioClimatico.obtenerClimaEnDefault().getTemperatura();
        fechaFormateada = traducirDia(fechaDeUso.getDayOfWeek().toString()) + " " + fechaDeUso.getDayOfMonth() + " de "
                + traducirMes(fechaDeUso.getMonth().toString()) + " del " + fechaDeUso.getYear();
	}

	public Atuendo getAtuendo() {
        return atuendo;
    }

    public void setAtuendo(Atuendo atuendo) {
        this.atuendo = atuendo;
    }

    public EstadoAtuendo getEstado() {
        return estado;
    }

    public void setEstado(EstadoAtuendo estado) {
        this.estado = estado;
    }

    public Double getTemperaturaDeUso() {
        return temperaturaDeUso;
    }

    public void setTemperaturaDeUso(Double temperaturaDeUso) {
        this.temperaturaDeUso = temperaturaDeUso;
    }

    public LocalDateTime getFechaDeUso() {
        return fechaDeUso;
    }

    public void setFechaDeUso(LocalDateTime fechaDeUso) {
        this.fechaDeUso = fechaDeUso;
    }

    public boolean getPuntuado(){
        return puntuado;
    }
    public void setPuntuado(boolean puntuo){
        puntuado = puntuo;
    }

    public String getFechaFormateada() {
        return fechaFormateada;
    }

    public void setFechaFormateada(String fecha) {
        fechaFormateada = fecha;
    }

    private String traducirDia(String day){
        switch(day){
            case "MONDAY":
                return "lunes";
            case "TUESDAY":
                return "martes";
            case "WEDNESDAY":
                return "miercoles";
            case "THRUSDAY":
                return "jueves";
            case "FRIDAY":
                return "viernes";
            case "SATURDAY":
                return "sabado";
            case "SUNDAY":
                return "domingo";
            default:
                return "ERROR EN EL DIA";
        }
    }

    private String traducirMes(String month) {
        switch (month) {
        case "JANUARY":
            return "enero";
        case "FEBRUARY":
            return "febrero";
        case "MARCH":
            return "marzo";
        case "APRIL":
            return "abril";
        case "MAY":
            return "mayo";
        case "JUNE":
            return "junio";
        case "JULY":
            return "julio";
        case "AUGUST":
            return "agosto";
        case "SEPTEMBER":
            return "septiembre";
        case "OCTOBER":
            return "octubre";
        case "NOVEMBER":
            return "noviembre";
        case "DECEMBER":
            return "diciembre";
        default:
            return "ERROR EN EL MES";
        }
    }
}
