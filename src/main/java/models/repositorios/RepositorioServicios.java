package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import models.entities.servicios.Servicio;
import models.repositorios.ICrudRepository;

public class RepositorioServicios implements ICrudRepository, WithSimplePersistenceUnit {

  EntityManager em;

  public RepositorioServicios(EntityManager em){
    this.em = em;
  }

  public void guardar(Object... servicio) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    for (Object o :
        servicio) {
      em.persist(o);
    }
    tx.commit();
  }

  @Override
  public void eliminar(Object servicio) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    em.remove(servicio);
    tx.commit();
  }

  @Override
  public void actualizar(Object servicio) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    em.merge(servicio);
    tx.commit();
  }

  @Override
  public List buscarTodos() {
    return em.createQuery("from " + Servicio.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return em.find(Servicio.class, id);
  }

  public Object buscarPorNombre(String nombre) {
    String jpql = "SELECT s from Servicio s where s.nombre = :nombre";
    Query query = em.createQuery(jpql);
    query.setParameter("nombre", nombre);
    try {
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}
