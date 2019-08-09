package alertas;

public class Alerta {
    private TipoDeAlerta tipo;

    public Alerta(String tipoObtenido){
        switch (tipoObtenido){
            case "Rain":
                tipo = TipoDeAlerta.LLUVIA;
                return;
            case "Ice":
                tipo = TipoDeAlerta.GRANIZO;
                return;
            case "Snow":
                tipo = TipoDeAlerta.NIEVE;
                return;
        }
    }

    public TipoDeAlerta getTipo() {
        return tipo;
    }
}