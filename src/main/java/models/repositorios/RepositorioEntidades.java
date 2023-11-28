package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import models.entities.entidades.Entidad;
import models.entities.incidentes.Incidente;
import models.repositorios.ICrudRepository;


public class RepositorioEntidades implements ICrudRepository, WithSimplePersistenceUnit {

  EntityManager em;

  public RepositorioEntidades(EntityManager em){
    this.em = em;
  }

  @Override
  public void guardar(Object... entidad) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    for (Object o :
        entidad) {
      em.persist(o);
    }
    tx.commit();
  }

  @Override
  public void eliminar(Object entidad) {
    EntityTransaction tx = em.getTransaction();
    if(!tx.isActive())
      tx.begin();
    em.remove(entidad);
    tx.commit();
  }

  public void actualizar(Object entidad) {
    EntityTransaction tx = em.getTransaction();
    if(!tx.isActive())
      tx.begin();
    em.merge(entidad);
    tx.commit();
  }

  @Override
  public Object buscar(Long id) {
    return em.find(Entidad.class, id);
  }

  @Override
  public List buscarTodos() {
    return em.createQuery("from " + Entidad.class.getName()).getResultList();
  }

  public Object buscarPorNombre(String nombre) {
    String jpql = "SELECT e from Entidad e where e.nombre = :nombre";
    Query query = em.createQuery(jpql);
    query.setParameter("nombre", nombre);
    try {
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}
