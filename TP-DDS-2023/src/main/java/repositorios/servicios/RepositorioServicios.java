package repositorios.servicios;

import domain.organismos.OrganismoDeControl;
import domain.servicios.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class RepositorioServicios implements RepoServicios, WithSimplePersistenceUnit {

  public void add(Servicio servicio) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(servicio);
    tx.commit();
  }

  public void eliminar(Servicio servicio){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(servicio);
    tx.commit();
  }

  public void modificar(Servicio servicio){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(servicio);
    tx.commit();
  }

  public List<Servicio> getAll() {
    return entityManager().createQuery("from" + Servicio.class.getName()).getResultList();
  }

  public Servicio devolverPorId(int id){
    return entityManager().find(Servicio.class,id);
  }
}
