package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import models.entities.incidentes.Incidente;
import models.repositorios.ICrudRepository;
import models.services.notificador.EstadoEnvio;
import models.services.notificador.Notificacion;

public class RepositorioNotificaciones implements ICrudRepository, WithSimplePersistenceUnit {

  EntityManager em;

  public RepositorioNotificaciones(EntityManager em){
    this.em = em;
  }

  public List buscarPorEstado(EstadoEnvio estadoEnvio) {
    if (estadoEnvio == null) {
      throw new IllegalArgumentException("El estado del envio no puede ser nulo.");
    }
    String jpql = "SELECT n FROM Notificacion n WHERE n.estadoEnvio = :parametro";
    Query query = em.createQuery(jpql);
    query.setParameter("parametro", estadoEnvio);
    try {
      return query.getResultList();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }
  }

  @Override
  public void guardar(Object... notificacion) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    for (Object o :
        notificacion) {
      em.persist(o);
    }
    tx.commit();
  }

  @Override
  public void eliminar(Object notificacion) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    em.remove(notificacion);
    tx.commit();
  }

  @Override
  public void actualizar(Object notificacion) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    em.merge(notificacion);
    tx.commit();
  }

  @Override
  public List buscarTodos() {
    return em.createQuery("from " + Notificacion.class.getName()).getResultList();
  }

  @Override
  public Notificacion buscar(Long id) {
    return em.find(Notificacion.class, id);
  }
}
