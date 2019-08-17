package notificaciones;

import eventos.AsistenciaEvento;
import usuario.Usuario;

public interface Informante {

    /*
    fixme tener 3 métodos distintos para estas cosas me hace pensar que
    en el caso de querer agregar más alertas hay que seguir creando métodos
    Quizás un enum pasado por parámetro evitaría tener que hacer un método
     distinto por situación
     */
    void notificarTormenta(Usuario usuario);
    void notificarGranizo(Usuario usuario);
    void notificarNevada(Usuario usuario);
    void notificarA(Usuario usuario, AsistenciaEvento evento);
}
