package repositorios.incidentes;

import domain.comunidades.Comunidad;
import domain.incidentes.EstadoIncidente;
import domain.incidentes.Incidente;
import domain.servicios.PrestacionDeServicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.List;

public class RepositorioIncidentes implements RepoIncidentes, WithSimplePersistenceUnit {

  //Reemplazar a futuro con BD TODO
  List<Incidente> incidentes;
  public void add(Incidente... incidente) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    for (Incidente inci:
         Arrays.asList(incidente)) {
      entityManager().persist(inci);
    }
    tx.commit();
    incidentes.addAll(List.of(incidente));
  }

  public void eliminar(Incidente incidente){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(incidente);
    tx.commit();
  }

  public void modificar(Incidente incidente){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(incidente);
    tx.commit();
  }

  public List<Incidente> getAll() {
    return entityManager().createQuery("from" + Incidente.class.getName()).getResultList();
  }

  public Incidente devolverPorId(int id){
    return entityManager().find(Incidente.class,id);
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
