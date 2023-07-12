package services.notificador.formas;

import services.notificador.enviadores.EnviadorMail;
import services.notificador.enviadores.EnviadorWPP;
import services.notificador.Notificacion;

public class EnElMomento implements FormasNotificar {

    @Override
    public void notificar(Notificacion notificacion) {
        switch (notificacion.getUsuario().getMedioDeContacto()) {
            case WPP:
                EnviadorWPP enviadorWPP = new EnviadorWPP();
                enviadorWPP.enviar(notificacion);
                break;
            case EMAIL:
                EnviadorMail enviadorMail = new EnviadorMail();
                enviadorMail.enviar(notificacion);
                break;
        }
    }
}
