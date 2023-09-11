package domain.usuarios;

import domain.roles.Rol;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import services.notificador.formas.FormasNotificar;
import services.notificador.MedioDeContacto;
import services.notificador.Notificacion;
@Entity
@Table(name = "usuario")
public class Usuario {
  @Getter
  @Id
  @GeneratedValue
  long id;
  @Getter
  @Column
  String nombre;
  @Column
  String contrasenia;
  @Transient
  Rol rol;
  @Getter
  @Column
  String mail;
  @Getter
  @Column
  String numeroCelular;

  @Getter
  @Transient
  MedioDeContacto medioDeContacto;

  @Getter
  @Setter
  @Transient
  FormasNotificar forma;

  @Getter
  @Column
  LocalDateTime inicioHorarioDisponible;
  @Getter
  @Column
  LocalDateTime finHorarioDisponible;

  public Usuario(int id, String nombre, String contrasenia, LocalDateTime inicioHorarioDisponible, LocalDateTime finHorarioDisponible) throws IOException {
    this.id = id;
    this.contrasenia = contrasenia;
    this.nombre = nombre;
    this.medioDeContacto = null;

    this.inicioHorarioDisponible = inicioHorarioDisponible;
    this.finHorarioDisponible = finHorarioDisponible;
  }

  public Boolean estaDisponible(LocalDateTime horario) {
    return horario.isAfter(inicioHorarioDisponible) && horario.isBefore(finHorarioDisponible);
  }
}
