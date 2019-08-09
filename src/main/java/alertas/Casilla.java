package alertas;

import usuario.Usuario;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import eventos.AsistenciaEvento;



public class Casilla implements Interesado {
    public MailSender mailSender;
    public SimpleMailMessage message;

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
        message.setTo(direccion);
        message.setText(mail);
        mailSender.send(message);
    }
    public void notificarA(Usuario usuario, AsistenciaEvento evento) {
    	enviarMail(usuario.getMail(),"Hola estan tus sugerencias listas"+ evento.pedirSugerencias());
    }



}
