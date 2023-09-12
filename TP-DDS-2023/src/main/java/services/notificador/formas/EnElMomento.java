package services.notificador.formas;

import repositorios.notificaciones.RepoNotificaciones;
import services.notificador.enviadores.EnviadorMail;
import services.notificador.enviadores.EnviadorWPP;
import services.notificador.Notificacion;

import java.io.IOException;

public class EnElMomento extends FormasNotificar {

    @Override
    public void notificar(Notificacion notificacion) throws IOException {
        enviarNotificacion(notificacion);
    }
}
