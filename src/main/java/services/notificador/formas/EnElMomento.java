package services.notificador.formas;

import services.notificador.enviadores.EnviadorMail;
import services.notificador.enviadores.EnviadorWPP;
import services.notificador.Notificacion;

public class EnElMomento implements FormasNotificar {
public static EnviadorWPP enviadorWPP;
public static EnviadorMail enviadorMail;
    @Override
    public void notificar(Notificacion notificacion) {
        switch (notificacion.getUsuario().getMedioDeContacto()) {
            case WPP:
                EnviadorWPP.enviar(notificacion);
                break;
            case EMAIL:
                EnviadorMail.enviar(notificacion);
                break;
        }
    }
}
