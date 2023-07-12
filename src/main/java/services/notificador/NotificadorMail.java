package services.notificador;

import domain.incidentes.Incidente;
import domain.usuarios.Usuario;

public class NotificadorMail implements Notificador{
    @Override
    public void generarNotificacion(Usuario usuario, Incidente incidente) {
        if (usuario.getMedioDeContacto() != null){
            Notificacion notificacion = new Notificacion(usuario, incidente);
            usuario.getForma().notificar(notificacion);
        }
        else {
            throw new MedioDeContactoException();
        }
    }
}
