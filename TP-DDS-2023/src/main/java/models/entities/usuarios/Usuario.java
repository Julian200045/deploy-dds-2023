package models.entities.usuarios;

import models.converters.FormasNotificarConverter;
import models.entities.roles.Rol;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import models.services.notificador.formas.EnElMomento;
import models.services.notificador.formas.FormasNotificar;
import models.services.notificador.MedioDeContacto;
import models.services.notificador.Notificacion;
@Entity
@Table(name = "usuario")
public class Usuario {
  @Getter
  @Id
  @GeneratedValue
  private long id;
  @Getter
  @Column(name = "nombre")
  private String nombre;
  @Column(name = "contrasenia")
  private String contrasenia;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rol_id", referencedColumnName = "id")
  private Rol rol;
  @Getter
  @Column(name = "mail")
  private String mail;
  @Getter
  @Column(name = "num_celular")
  private String numeroCelular;

  @Getter
  @Enumerated(value = EnumType.STRING)
  private MedioDeContacto medioDeContacto;

  @Getter
  @Setter
  @Convert( converter =  FormasNotificarConverter.class)
  @Column(name = "forma_notificar")
  private FormasNotificar forma;

  @Getter
  @Column(name = "inicio_horario_disponible", columnDefinition = "TIMESTAMP")
  private LocalDateTime inicioHorarioDisponible;
  @Getter
  @Column(name = "fin_horario_disponible", columnDefinition = "TIMESTAMP")
  private LocalDateTime finHorarioDisponible;

  public Usuario(String nombre, String contrasenia, LocalDateTime inicioHorarioDisponible, LocalDateTime finHorarioDisponible) throws IOException {
    this.contrasenia = contrasenia;
    this.nombre = nombre;
    this.medioDeContacto = null;
    this.forma = new EnElMomento();

    this.inicioHorarioDisponible = inicioHorarioDisponible;
    this.finHorarioDisponible = finHorarioDisponible;
  }

  public Boolean estaDisponible(LocalDateTime horario) {
    return horario.isAfter(inicioHorarioDisponible) && horario.isBefore(finHorarioDisponible);
  }

  public Usuario(){

  }
}
