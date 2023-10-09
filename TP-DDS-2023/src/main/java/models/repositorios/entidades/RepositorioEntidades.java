package models.repositorios.entidades;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityTransaction;
import lombok.Getter;
import models.entities.entidades.Entidad;
import models.entities.entidades.TipoEntidad;
import models.entities.incidentes.Incidente;
import models.repositorios.ICrudRepository;


public class RepositorioEntidades implements ICrudRepository, WithSimplePersistenceUnit {
  @Override
  public void guardar(Object... entidad) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    for (Object o :
        entidad) {
      entityManager().persist(o);
    }
    tx.commit();
  }

  @Override
  public void eliminar(Object entidad) {
    EntityTransaction tx = entityManager().getTransaction();
    if(!tx.isActive())
      tx.begin();
    entityManager().remove(entidad);
    tx.commit();
  }

  public void actualizar(Object entidad) {
    EntityTransaction tx = entityManager().getTransaction();
    if(!tx.isActive())
      tx.begin();
    entityManager().merge(entidad);
    tx.commit();
  }

  @Override
  public Object buscar(Long id) {
    return entityManager().find(Entidad.class, id);
  }

  @Override
  public List buscarTodos() {
    return entityManager().createQuery("from " + Incidente.class.getName()).getResultList();
  }
}
