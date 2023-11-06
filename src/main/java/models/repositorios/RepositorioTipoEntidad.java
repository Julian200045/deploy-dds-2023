package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.entities.entidades.TipoEntidad;
import models.repositorios.ICrudRepository;

public class RepositorioTipoEntidad implements ICrudRepository, WithSimplePersistenceUnit {

  EntityManager em;

  public RepositorioTipoEntidad(EntityManager em){
    this.em = em;
  }

  @Override
  public List buscarTodos() {
    return em.createQuery("from " + TipoEntidad.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return em.find(TipoEntidad.class, id);
  }

  @Override
  public void guardar(Object... o) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    for (Object entidad :
        o) {
      em.persist(entidad);
    }
    tx.commit();
  }

  @Override
  public void actualizar(Object o) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    em.merge(o);
    tx.commit();
  }

  @Override
  public void eliminar(Object o) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    em.remove(o);
    tx.commit();
  }
}
