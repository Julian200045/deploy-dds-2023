package services.notificador;

import domain.incidentes.Incidente;
import domain.usuarios.Usuario;
import lombok.Getter;

public class Notificacion {
    @Getter
    private Usuario usuario;
    @Getter
    private Incidente incidente;
    public Notificacion (Usuario usuario, Incidente incidente) {
        this.usuario = usuario;
        this.incidente = incidente;
    }
}