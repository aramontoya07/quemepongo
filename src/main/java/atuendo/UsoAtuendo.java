package atuendo;

import java.time.LocalDateTime;

import javax.persistence.*;

import atuendo.Atuendo;
import clima.ServicioClimatico;
import db.EntidadPersistente;

@Entity
@Table(name = "Usos")
public class UsoAtuendo extends EntidadPersistente{
    @OneToOne(cascade = {CascadeType.ALL})
    public Atuendo atuendo;

    @Enumerated(EnumType.STRING)
    public EstadoAtuendo estado;

    public LocalDateTime fechaDeUso;

    public Double temperaturaDeUso;

    public UsoAtuendo(Atuendo atuendo2, EstadoAtuendo estado2) {
        fechaDeUso = LocalDateTime.now();
        atuendo = atuendo2;
        estado = estado2;
        temperaturaDeUso = ServicioClimatico.obtenerClimaEnDefault().getTemperatura();
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
}
