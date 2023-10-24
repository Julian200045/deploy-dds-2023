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
@Getter
@Entity
@Table(name = "usuario")
public class Usuario {
  @Id
  @GeneratedValue
  private long id;

  @Setter
  @Column(name = "nombre")
  private String nombre;

  @Column(name = "contrasenia")
  private String contrasenia;

  @ManyToOne
  @JoinColumn(name = "rol_id", referencedColumnName = "id")
  @Setter
  private Rol rol;

  @Setter
  @Column(name = "mail")
  private String mail;

  @Setter
  @Column(name = "num_celular")
  private String numeroCelular;

  @Setter
  @Enumerated(value = EnumType.STRING)
  private MedioDeContacto medioDeContacto;

  @Setter
  @Convert( converter =  FormasNotificarConverter.class)
  @Column(name = "forma_notificar")
  private FormasNotificar forma = new EnElMomento();;

  @Setter
  @Column(name = "inicio_horario_disponible", columnDefinition = "TIMESTAMP")
  private LocalDateTime inicioHorarioDisponible;

  @Setter
  @Column(name = "fin_horario_disponible", columnDefinition = "TIMESTAMP")
  private LocalDateTime finHorarioDisponible;

  public Usuario(String nombre, String contrasenia, String mail, String celular) {
    this.nombre = nombre;
    this.contrasenia = contrasenia;
    this.mail=mail;
    this.numeroCelular=celular;
    this.medioDeContacto = null;
  }

  public Boolean estaDisponible(LocalDateTime horario) {
    return horario.isAfter(inicioHorarioDisponible) && horario.isBefore(finHorarioDisponible);
  }

  public Usuario(){

  }
}
