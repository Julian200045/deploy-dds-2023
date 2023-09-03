package services.notificador.formas;

import org.quartz.SchedulerException;
import repositorios.notificaciones.RepoNotificaciones;
import services.notificador.EstadoEnvio;
import services.notificador.Notificacion;
import services.notificador.enviadores.EnviadorMail;
import services.notificador.enviadores.EnviadorWPP;

public abstract class FormasNotificar {

    public abstract void notificar(Notificacion notificacion) throws SchedulerException;
    public void enviarNotificacion(Notificacion notificacion){
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
