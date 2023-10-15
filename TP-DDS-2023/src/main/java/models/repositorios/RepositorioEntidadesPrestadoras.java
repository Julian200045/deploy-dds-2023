package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import javax.persistence.EntityTransaction;
import models.entities.incidentes.Incidente;
import models.entities.organismos.EntidadPrestadora;
import models.repositorios.ICrudRepository;

public class RepositorioEntidadesPrestadoras implements ICrudRepository, WithSimplePersistenceUnit {
  @Override
  public Object buscar(Long id) {
    return entityManager().find(EntidadPrestadora.class, id);
  }

  @Override
  public List buscarTodos() {
    return entityManager().createQuery("from " + EntidadPrestadora.class.getName()).getResultList();
  }

  @Override
  public void guardar(Object... o) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    for (Object entidadPrestadora :
        o) {
      entityManager().persist(entidadPrestadora);
    }
    tx.commit();
  }

  @Override
  public void actualizar(Object entidadPrestadora) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    entityManager().merge(entidadPrestadora);
    tx.commit();
  }

  @Override
  public void eliminar(Object entidadPrestadora) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    entityManager().remove(entidadPrestadora);
    tx.commit();
  }

}
