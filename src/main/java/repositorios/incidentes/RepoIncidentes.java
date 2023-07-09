package repositorios.incidentes;

import domain.comunidades.Comunidad;
import domain.incidentes.EstadoIncidente;
import domain.incidentes.Incidente;
import domain.servicios.PrestacionDeServicio;

import java.util.List;


public interface RepoIncidentes {
  void add(Incidente... incidente);
  List<Incidente> getAll();
  List<Incidente> getByEstado(EstadoIncidente estadoIncidente);
  List<Incidente> getByComunidad(Comunidad comunidad);
  List<Incidente> getByPrestacion(PrestacionDeServicio prestacionDeServicio);

}
