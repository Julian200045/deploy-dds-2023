package repositorios.incidentes;

import domain.incidentes.EstadoIncidente;
import domain.incidentes.Incidente;

import java.util.List;

public class RepositorioIncidentes implements RepoIncidentes{

  //Reemplazar a futuro con BD TODO
  List<Incidente> incidentes;
  public void add(Incidente... incidente) {
    incidentes.addAll(List.of(incidente));
  }

  public List<Incidente> getAll() {
    return incidentes;
  }

  public List<Incidente> getAll(EstadoIncidente estadoIncidente) {
    return incidentes.stream().filter(incidente -> incidente.getEstado().equals(estadoIncidente) ).toList();
  }

}
