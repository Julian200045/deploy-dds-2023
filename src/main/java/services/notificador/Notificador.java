package services.notificador;

import domain.incidentes.Incidente;
import domain.usuarios.Usuario;

public class Notificador {
    void generarNotificacion(Usuario usuario, String mensaje) {
        if (usuario.getMedioDeContacto() != null){
            Notificacion notificacion = new Notificacion(usuario, mensaje);
            usuario.getForma().notificar(notificacion);
        }
        else {
            throw new MedioDeContactoException();
        }
    }
}
