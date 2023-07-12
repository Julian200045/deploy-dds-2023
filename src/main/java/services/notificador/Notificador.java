package services.notificador;

import domain.incidentes.Incidente;
import domain.usuarios.Usuario;

public interface Notificador {
    public void generarNotificacion(Usuario usuario, Incidente incidente);
}
