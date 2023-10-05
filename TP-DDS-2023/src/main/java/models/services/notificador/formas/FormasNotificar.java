package models.services.notificador.formas;

import org.quartz.SchedulerException;
import models.repositorios.notificaciones.RepoNotificaciones;
import models.services.notificador.EstadoEnvio;
import models.services.notificador.Notificacion;
import models.services.notificador.enviadores.EnviadorMail;
import models.services.notificador.enviadores.EnviadorWPP;

import java.io.IOException;

public abstract class FormasNotificar {

    public abstract void notificar(Notificacion notificacion) throws SchedulerException, IOException;
    public void enviarNotificacion(Notificacion notificacion) throws IOException {
        switch (notificacion.getUsuario().getMedioDeContacto()) {
            case WPP:
                EnviadorWPP.enviar(notificacion);
            break;
            case EMAIL:
                EnviadorMail.enviar(notificacion);
                break;
        }
        notificacion.enviar();
    }
}
