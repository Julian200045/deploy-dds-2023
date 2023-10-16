package models.repositorios.comunidades;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.entities.comunidades.Comunidad;
import models.entities.comunidades.Persona;
import models.repositorios.ICrudRepository;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioComunidades implements ICrudRepository, WithSimplePersistenceUnit {
  @Override
  public List buscarTodos() {
    return entityManager().createQuery("from " + Comunidad.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return entityManager().find(Comunidad.class, id);
  }

  @Override
  public void guardar(Object... comunidad) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive()) tx.begin();
    for (Object o : comunidad) {
      entityManager().persist(o);
    }
    tx.commit();
  }

  @Override
  public void actualizar(Object comunidad) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive()) tx.begin();
    entityManager().merge(comunidad);
    tx.commit();
  }

  @Override
  public void eliminar(Object comunidad) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive()) tx.begin();
    entityManager().remove(comunidad);
    tx.commit();
  }
}
