package services.notificador;

import domain.incidentes.EstadoIncidente;
import domain.incidentes.Incidente;
import domain.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Notificacion {
    @Getter
    private Usuario usuario;
    @Getter
    private String mensaje;
    private LocalDateTime horarioDeEnvio;
    @Getter
    private EstadoEnvio estadoEnvio;
    public Notificacion (Usuario usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;

        this.estadoEnvio = EstadoEnvio.PENDIENTE;
    }

    public void enviar() {
        estadoEnvio = EstadoEnvio.ENVIADA;
        horarioDeEnvio = LocalDateTime.now();
    }
}