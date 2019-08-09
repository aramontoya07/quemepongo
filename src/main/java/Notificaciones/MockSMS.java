package Notificaciones;

public class MockSMS implements ServicioNotificador{
    public void notificar(String mensaje) {
        System.out.println(mensaje);
    }
}
