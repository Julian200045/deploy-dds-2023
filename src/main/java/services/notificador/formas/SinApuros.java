package services.notificador.formas;

import services.notificador.Notificacion;

public class SinApuros implements FormasNotificar {

    @Override
    public void notificar(Notificacion notificacion){
        notificacion.getUsuario().agregarNotificacion(notificacion);
    }
}
