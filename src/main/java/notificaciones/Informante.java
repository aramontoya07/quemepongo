package notificaciones;

import eventos.AsistenciaEvento;
import usuario.Usuario;


public enum Informante {
    
    CasillaDeMails(){
        @SuppressWarnings("unused")
        public void notificar(Usuario usuario) {
            usuario.marcarNotificado();
        }
    
        public void notificarGranizo(Usuario usuario) {
            usuario.marcarNotificado();
        }
    
        public void notificarNevada(Usuario usuario) {
            usuario.marcarNotificado();
        }
    
        public void notificarA(Usuario usuario, AsistenciaEvento evento) {
            usuario.marcarNotificado();
        }
    },

    MockSMS(){
        public void notificarTormenta(Usuario usuario) {
            usuario.marcarNotificado();
        }
    
        public void notificarGranizo(Usuario usuario) {
            usuario.marcarNotificado();
        }
    
        public void notificarNevada(Usuario usuario) {
            usuario.marcarNotificado();
        }
    
        public void notificarA(Usuario usuario, AsistenciaEvento evento) {
            usuario.marcarNotificado();
        }
    },

    InformanteMock(){
        public void notificarTormenta(Usuario usuario) {
            usuario.marcarNotificado();
        }
    
        public void notificarGranizo(Usuario usuario) {
            usuario.marcarNotificado();
        }
    
        public void notificarNevada(Usuario usuario) {
            usuario.marcarNotificado();
        }
    
        public void notificarA(Usuario usuario, AsistenciaEvento evento) {
            usuario.marcarNotificado();
        }
    };

    public void notificarA(Usuario usuario, AsistenciaEvento asistencia) {};
    public void notificarTormenta(Usuario usuario) {};
    public void notificarGranizo(Usuario usuario) {};
    public void notificarNevada(Usuario usuario) {};
}
