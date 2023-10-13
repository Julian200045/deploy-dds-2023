package models.services.notificador;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import models.entities.usuarios.Usuario;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificacion")
public class Notificacion {
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @ManyToOne()
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Getter
    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "hora_envio", columnDefinition = "TIMESTAMP")
    private LocalDateTime horarioDeEnvio;

    @Getter
    @Enumerated(EnumType.STRING)
    private EstadoEnvio estadoEnvio;
    public Notificacion (Usuario usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
        this.estadoEnvio = EstadoEnvio.PENDIENTE;
    }
    public Notificacion(){

    }

    public void enviar() {
        estadoEnvio = EstadoEnvio.ENVIADA;
        horarioDeEnvio = LocalDateTime.now();
    }
}