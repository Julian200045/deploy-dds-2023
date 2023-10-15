package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import models.entities.incidentes.Incidente;
import models.repositorios.ICrudRepository;
import models.services.notificador.EstadoEnvio;
import models.services.notificador.Notificacion;

public class RepositorioNotificaciones implements ICrudRepository, WithSimplePersistenceUnit {

  public List buscarPorEstado(EstadoEnvio estadoEnvio) {
    if (estadoEnvio == null) {
      throw new IllegalArgumentException("El estado del envio no puede ser nulo.");
    }
    String jpql = "SELECT n FROM Notificacion n WHERE n.estadoEnvio = :parametro";
    Query query = entityManager().createQuery(jpql);
    query.setParameter("parametro", estadoEnvio);
    try {
      return query.getResultList();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }
  }

  @Override
  public void guardar(Object... notificacion) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    for (Object o :
        notificacion) {
      entityManager().persist(o);
    }
    tx.commit();
  }

  @Override
  public void eliminar(Object notificacion) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    entityManager().remove(notificacion);
    tx.commit();
  }

  @Override
  public void actualizar(Object notificacion) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    entityManager().merge(notificacion);
    tx.commit();
  }

  @Override
  public List buscarTodos() {
    return entityManager().createQuery("from " + Notificacion.class.getName()).getResultList();
  }

  @Override
  public Notificacion buscar(Long id) {
    return entityManager().find(Notificacion.class, id);
  }
}
