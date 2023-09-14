package domain.usuarios;

import domain.roles.Rol;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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
  @ManyToOne
  @JoinColumn(name = "rol_id", referencedColumnName = "id")
  Rol rol;
  @Getter
  @Column
  String mail;
  @Getter
  @Column
  String numeroCelular;

  @Getter
  @Enumerated(value = EnumType.STRING)
  MedioDeContacto medioDeContacto;

  @Getter
  @Setter
  @Transient
  FormasNotificar forma;

  @Getter
  @Column(name = "inicio_horario_disponible", columnDefinition = "TIMESTAMP")
  LocalDateTime inicioHorarioDisponible;
  @Getter
  @Column(name = "fin_horario_disponible", columnDefinition = "TIMESTAMP")
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

  public Usuario(){

  }
}
