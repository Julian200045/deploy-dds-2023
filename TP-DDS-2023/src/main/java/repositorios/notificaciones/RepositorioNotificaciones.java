package repositorios.notificaciones;

import services.notificador.EstadoEnvio;
import services.notificador.Notificacion;

import java.util.ArrayList;
import java.util.List;

public class RepositorioNotificaciones implements RepoNotificaciones{
  List<Notificacion> notificaciones = new ArrayList<>();
  public List<Notificacion> getAll(){
    return notificaciones;
  }
  public List<Notificacion> getAllByEstado(EstadoEnvio estadoEnvio) {
    return notificaciones.stream().filter(notificacion -> notificacion.getEstadoEnvio().equals(estadoEnvio)).toList();
  }
  public void add(Notificacion notificacion){
    notificaciones.add(notificacion);
  }
}
