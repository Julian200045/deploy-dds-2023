package repositorios.incidentes;

import domain.comunidades.Comunidad;
import domain.entidades.Entidad;
import domain.incidentes.EstadoIncidente;
import domain.incidentes.Incidente;
import domain.servicios.PrestacionDeServicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.List;

public class RepositorioIncidentes implements RepoIncidentes, WithSimplePersistenceUnit {

  //Reemplazar a futuro con BD TODO
  public void add(Incidente... incidente) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    for (Incidente inci:
         Arrays.asList(incidente)) {
      entityManager().persist(inci);
    }
    tx.commit();
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
    return entityManager().createQuery("from" + Incidente.class.getName() + "where estado = " + estadoIncidente.name()).getResultList();
  }

  public List<Incidente> getByComunidad(Comunidad comunidad){
    return entityManager().createQuery("from" + Incidente.class.getName() + "join comunidad c on c.id = " + Long.toString(comunidad.getId()) ).getResultList();
  }

  public List<Incidente> getByPrestacion(PrestacionDeServicio prestacionDeServicio) {
    return entityManager().createQuery("from" + PrestacionDeServicio.class.getName() + "join prestacion_servicio p on p.id = " + Long.toString(prestacionDeServicio.getId()) ).getResultList();
  }
}
