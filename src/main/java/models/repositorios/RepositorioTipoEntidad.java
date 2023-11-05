package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import javax.persistence.EntityTransaction;
import models.entities.entidades.TipoEntidad;
import models.repositorios.ICrudRepository;

public class RepositorioTipoEntidad implements ICrudRepository, WithSimplePersistenceUnit {

  @Override
  public List buscarTodos() {
    return entityManager().createQuery("from " + TipoEntidad.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return entityManager().find(TipoEntidad.class, id);
  }

  @Override
  public void guardar(Object... o) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    for (Object entidad :
        o) {
      entityManager().persist(entidad);
    }
    tx.commit();
  }

  @Override
  public void actualizar(Object o) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    entityManager().merge(o);
    tx.commit();
  }

  @Override
  public void eliminar(Object o) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    entityManager().remove(o);
    tx.commit();
  }
}
