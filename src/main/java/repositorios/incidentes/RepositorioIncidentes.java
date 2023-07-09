package repositorios.incidentes;

import domain.comunidades.Comunidad;
import domain.incidentes.EstadoIncidente;
import domain.incidentes.Incidente;
import domain.servicios.PrestacionDeServicio;

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

  public List<Incidente> getByEstado(EstadoIncidente estadoIncidente) {
    return incidentes.stream().filter(incidente -> incidente.getEstado().equals(estadoIncidente) ).toList();
  }

  public List<Incidente> getByComunidad(Comunidad comunidad){
    return incidentes.stream().filter(incidente -> incidente.getComunidad().equals(comunidad)).toList();
  }

  public List<Incidente> getByPrestacion(PrestacionDeServicio prestacionDeServicio) {
    return incidentes.stream().filter(incidente -> incidente.getPrestacionDeServicio().equals(prestacionDeServicio)).toList();
  }
}
