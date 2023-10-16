package models.services.notificador.formas;

import models.repositorios.notificaciones.RepoNotificaciones;
import models.services.notificador.enviadores.EnviadorMail;
import models.services.notificador.enviadores.EnviadorWPP;
import models.services.notificador.Notificacion;

import java.io.IOException;

public class EnElMomento extends FormasNotificar {

    @Override
    public void notificar(Notificacion notificacion) throws IOException {
        enviarNotificacion(notificacion);
    }
}
