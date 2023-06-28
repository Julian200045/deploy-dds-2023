package repositorios.incidentes;

import domain.incidentes.EstadoIncidente;
import domain.incidentes.Incidente;

import java.util.List;


public interface RepoIncidentes {
  void add(Incidente ...incidente);
  List<Incidente> getAll();
  List<Incidente> getAll(EstadoIncidente estadoIncidente);


}
