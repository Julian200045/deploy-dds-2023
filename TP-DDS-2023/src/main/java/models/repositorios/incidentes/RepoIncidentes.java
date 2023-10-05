package models.repositorios.incidentes;

import models.entities.comunidades.Comunidad;
import models.entities.incidentes.EstadoIncidente;
import models.entities.incidentes.Incidente;
import models.entities.servicios.PrestacionDeServicio;

import java.util.List;


public interface RepoIncidentes {
  void add(Incidente... incidente);
  List<Incidente> getAll();
  List<Incidente> getByEstado(EstadoIncidente estadoIncidente);
  List<Incidente> getByComunidad(Comunidad comunidad);
  List<Incidente> getByPrestacion(PrestacionDeServicio prestacionDeServicio);

}
