package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import models.entities.organismos.EntidadPrestadora;

public class RepositorioEntidadesPrestadoras implements ICrudRepository, WithSimplePersistenceUnit {

  EntityManager em;

  public RepositorioEntidadesPrestadoras(EntityManager em){
    this.em = em;
  }

  @Override
  public Object buscar(Long id) {
    return em.find(EntidadPrestadora.class, id);
  }

  @Override
  public List buscarTodos() {
    return em.createQuery("from " + EntidadPrestadora.class.getName()).getResultList();
  }

  public Object buscarPorNombre(String nombre) {
    String jpql = "SELECT ep from EntidadPrestadora ep where ep.nombre = :nombre";
    Query query = em.createQuery(jpql);
    query.setParameter("nombre", nombre);
    try {
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  public void guardar(Object... o) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    for (Object entidadPrestadora :
        o) {
      em.persist(entidadPrestadora);
    }
    tx.commit();
  }

  @Override
  public void actualizar(Object entidadPrestadora) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    em.merge(entidadPrestadora);
    tx.commit();
  }

  @Override
  public void eliminar(Object entidadPrestadora) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    em.remove(entidadPrestadora);
    tx.commit();
  }

}
