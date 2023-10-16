package models.repositorios.prestaciondeservicios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.entities.servicios.PrestacionDeServicio;
import models.repositorios.ICrudRepository;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioPrestacionesDeServicio implements ICrudRepository, WithSimplePersistenceUnit {
  @Override
  public List buscarTodos() {
    return entityManager().createQuery("from " + PrestacionDeServicio.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return entityManager().find(PrestacionDeServicio.class, id);
  }

  @Override
  public void guardar(Object... prestacionDeServicio) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive()) tx.begin();
    for (Object o : prestacionDeServicio) {
      entityManager().persist(o);
    }
    tx.commit();
  }

  @Override
  public void actualizar(Object prestacionDeServicio) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive()) tx.begin();
    entityManager().merge(prestacionDeServicio);
    tx.commit();
  }

  @Override
  public void eliminar(Object prestacionDeServicio) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive()) tx.begin();
    entityManager().remove(prestacionDeServicio);
    tx.commit();
  }
}
