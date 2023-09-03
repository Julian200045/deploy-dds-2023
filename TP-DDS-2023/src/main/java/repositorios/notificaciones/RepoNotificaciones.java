package repositorios.notificaciones;

import services.notificador.EstadoEnvio;
import services.notificador.Notificacion;

import java.util.ArrayList;
import java.util.List;

public interface RepoNotificaciones {
  List<Notificacion> getAll();
  List<Notificacion> getAllByEstado(EstadoEnvio estadoEnvio);
  void add(Notificacion notificacion);
}
