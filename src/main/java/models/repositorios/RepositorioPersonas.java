package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.entities.comunidades.Persona;
import models.entities.usuarios.Usuario;
import models.repositorios.ICrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class RepositorioPersonas implements ICrudRepository, WithSimplePersistenceUnit {

  EntityManager em;

  public RepositorioPersonas(EntityManager em){
    this.em = em;
  }

  @Override
  public List buscarTodos() {
    return em.createQuery("from " + Persona.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return em.find(Persona.class, id);
  }

  public Object buscarPorUsuarioId(Long idUsuario){
    if (idUsuario == null) {
      throw new IllegalArgumentException("El id del usuario no puede ser nulo.");
    }
    String jpql = "SELECT p FROM Persona p WHERE p.usuario.id = :parametro";
    Query query = em.createQuery(jpql);
    query.setParameter("parametro", idUsuario);

    try {
      return query.getResultList();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }
  }

  @Override
  public void guardar(Object... persona) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive()) tx.begin();
    for (Object o : persona) {
      em.persist(o);
    }
    tx.commit();
  }

  @Override
  public void actualizar(Object persona) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive()) tx.begin();
    em.merge(persona);
    tx.commit();
  }

  @Override
  public void eliminar(Object persona) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive()) tx.begin();
    em.remove(persona);
    tx.commit();
  }

  public Object buscarPorUsuario(Usuario usuarioEnSesion) {
    if (usuarioEnSesion == null) {
      throw new IllegalArgumentException("El usuario no puede ser nulo.");
    }

    String jpql = "SELECT p FROM Persona p WHERE p.usuario = :parametro";
    Query query = em.createQuery(jpql);
    query.setParameter("parametro", usuarioEnSesion);

    try {
      return query.getSingleResult();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }

  }
}
