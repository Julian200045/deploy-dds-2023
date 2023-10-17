package models.repositorios.personas;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.entities.comunidades.Persona;
import models.repositorios.ICrudRepository;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class RepositorioPersonas implements ICrudRepository, WithSimplePersistenceUnit {

  @Override
  public List buscarTodos() {
    return entityManager().createQuery("from " + Persona.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return entityManager().find(Persona.class, id);
  }

  public Object buscarPorUsuarioId(Long idUsuario){
    if (idUsuario == null) {
      throw new IllegalArgumentException("El id del usuario no puede ser nulo.");
    }
    String jpql = "SELECT p FROM Persona p WHERE p.usuario = :parametro";
    Query query = entityManager().createQuery(jpql);
    query.setParameter("parametro", idUsuario);

    try {
      return query.getResultList();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }
  }

  @Override
  public void guardar(Object... persona) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive()) tx.begin();
    for (Object o : persona) {
      entityManager().persist(o);
    }
    tx.commit();
  }

  @Override
  public void actualizar(Object persona) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive()) tx.begin();
    entityManager().merge(persona);
    tx.commit();
  }

  @Override
  public void eliminar(Object persona) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive()) tx.begin();
    entityManager().remove(persona);
    tx.commit();
  }
}
