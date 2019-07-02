package alertas;

import usuario.Usuario;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;



public class Casilla implements Interesado, CasillaDeMails {

    public MailSender mailSender;
    public SimpleMailMessage message;

    public void notificarTormenta(Usuario usuario) {
        this.enviarMail(usuario.getMail(), "No olvides llevar tu paraguas!");
    }

    public void notificarGranizo(Usuario usuario) {
        this.enviarMail(usuario.getMail(), "Ojo, va a caer granizo!");
    }

    public void enviarMail(String direccion, String mail) {
        message.setTo(direccion);
        message.setText(mail);
        mailSender.send(message);
    }



}
