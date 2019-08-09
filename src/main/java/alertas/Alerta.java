package alertas;

public class Alerta {
    private TipoDeAlerta tipo;
    private String ubicacion;

    public Alerta(TipoDeAlerta tipoAlerta, String ubicacionElegida){
        ubicacion = ubicacionElegida;
        tipo = tipoAlerta;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public TipoDeAlerta getTipo() {
        return tipo;
    }
}