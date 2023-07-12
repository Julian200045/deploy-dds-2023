package domain.usuarios;

import domain.roles.Rol;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import services.notificador.formas.FormasNotificar;
import services.notificador.MedioDeContacto;
import services.notificador.Notificacion;

public class Usuario {
  @Getter
  int id;
  @Getter
  String nombre;
  String contrasenia;
  Rol rol;
  @Getter
  String mail;
  @Getter
  String numeroCelular;

  @Getter
  MedioDeContacto medioDeContacto;

  @Getter
  @Setter
  FormasNotificar forma;
  @Getter
  List<Notificacion> notifiacionesPendientes;

  @Getter
  LocalDateTime inicioHorarioDisponible;
  @Getter
  LocalDateTime finHorarioDisponible;

  public Usuario(int id, String nombre, String contrasenia, LocalDateTime inicioHorarioDisponible, LocalDateTime finHorarioDisponible) throws IOException {
    this.id = id;
    this.contrasenia = contrasenia;
    this.nombre = nombre;
    this.medioDeContacto = null;
    this.notifiacionesPendientes = new ArrayList<>();

    this.inicioHorarioDisponible = inicioHorarioDisponible;
    this.finHorarioDisponible = finHorarioDisponible;
  }

  public void agregarNotificacion(Notificacion notificacion) {
    this.notifiacionesPendientes.add(notificacion);
  }

  public Boolean estaDisponible(LocalDateTime horario){
    return horario.isAfter(inicioHorarioDisponible) && horario.isBefore(finHorarioDisponible);
  }

  public Boolean tieneNotificacionesPendientes(){
    return !notifiacionesPendientes.isEmpty();
  }
}
