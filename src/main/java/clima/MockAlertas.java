package clima;

import alertas.Alerta;
import alertas.TipoDeAlerta;

import java.util.HashSet;
import java.util.Set;

public class MockAlertas extends ProvedorClimatico {

    @Override
    public Clima obtenerClima(String nombre_ciudad) {
        return new MockAgradable().obtenerClima(nombre_ciudad);
    }

    @Override
    public Set<Alerta> obtenerAlertas(String ubicacion) {
        Set<Alerta> alertas = new HashSet <>();
        Alerta alerta = new Alerta(TipoDeAlerta.GRANIZO,ubicacion);
        alertas.add(alerta);
        return alertas;
    }
}
