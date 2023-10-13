package models.repositorios.notificaciones;

import models.services.notificador.EstadoEnvio;
import models.services.notificador.Notificacion;

import java.util.ArrayList;
import java.util.List;

public interface RepoNotificaciones {
  List<Notificacion> getAll();
  List<Notificacion> getAllByEstado(EstadoEnvio estadoEnvio);
  void add(Notificacion notificacion);
}
