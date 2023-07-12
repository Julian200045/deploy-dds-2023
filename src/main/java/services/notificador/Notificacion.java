package services.notificador;

import domain.incidentes.Incidente;
import domain.usuarios.Usuario;
import lombok.Getter;

public class Notificacion {
    @Getter
    private Usuario usuario;
    @Getter
    private String mensaje;
    public Notificacion (Usuario usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }
}