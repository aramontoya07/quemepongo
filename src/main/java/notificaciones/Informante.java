package notificaciones;

import javax.persistence.Entity;

//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;

import eventos.AsistenciaEvento;
import usuario.Usuario;

@Entity
public enum Informante {
    
    CasillaDeMails(){
        public void notificarTormenta(Usuario usuario) {
            this.enviarMail(usuario.getMail(), "No olvides llevar tu paraguas!");
        }
    
        public void notificarGranizo(Usuario usuario) {
            this.enviarMail(usuario.getMail(), "Ojo, va a caer granizo!");
        }
    
        public void notificarNevada(Usuario usuario) {
            this.enviarMail(usuario.getMail(), "Mira que va a nevar!");
        }

        public void enviarMail(String direccion, String mail) {
            /*
            MailSender mailSender;
            SimpleMailMessage message;
            
            message.setTo(direccion);
            message.setText(mail);
            mailSender.send(message);
            */
        }
        public void notificarA(Usuario usuario, AsistenciaEvento evento) {
            enviarMail(usuario.getMail(),"Hola estan tus sugerencias listas para el evento "+ evento.toString());
        }
    },

    MockSMS(){
        public void notificarA(Usuario usuario, AsistenciaEvento evento) {
            System.out.println("Motificacion enviada por medio el MOCK SMS");
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
