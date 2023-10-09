package models.repositorios.servicios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityTransaction;
import models.entities.servicios.Servicio;
import models.repositorios.ICrudRepository;

public class RepositorioServicios implements ICrudRepository, WithSimplePersistenceUnit {
  List<Servicio> servicios = new ArrayList<>();

  public void guardar(Object... servicio) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    for (Object o :
        servicio) {
      entityManager().persist(o);
    }
    tx.commit();
  }

  @Override
  public void eliminar(Object servicio) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    entityManager().remove(servicio);
    tx.commit();
  }

  @Override
  public void actualizar(Object servicio) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    entityManager().merge(servicio);
    tx.commit();
  }

  @Override
  public List buscarTodos() {
    return entityManager().createQuery("from " + Servicio.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return entityManager().find(Servicio.class, id);
  }
}
