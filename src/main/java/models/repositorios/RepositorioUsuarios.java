package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import lombok.Getter;
import models.entities.usuarios.Usuario;
import models.repositorios.ICrudRepository;

public class RepositorioUsuarios implements ICrudRepository, WithSimplePersistenceUnit {

  EntityManager em;

  public RepositorioUsuarios(EntityManager em){
    this.em = em;
  }

  @Override
  public void guardar(Object... usuario) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive()) tx.begin();
    for (Object o : usuario) {
      em.persist(o);
    }
    tx.commit();
  }

  @Override
  public void eliminar(Object usuario) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive()) tx.begin();
    em.remove(usuario);
    tx.commit();
  }

  @Override
  public void actualizar(Object usuario) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive()) tx.begin();
    em.merge(usuario);
    tx.commit();
  }

  @Override
  public List buscarTodos() {
    return em.createQuery("from " + Usuario.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return em.find(Usuario.class, id);
  }

  public Object buscarPorNombre(String nombre) {
    String jpql = "SELECT u from Usuario u where u.nombre = :nombre";
    Query query = em.createQuery(jpql);
    query.setParameter("nombre", nombre);
    try {
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}
