package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.entities.comunidades.Comunidad;
import models.entities.comunidades.Persona;
import models.repositorios.ICrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioComunidades implements ICrudRepository, WithSimplePersistenceUnit {

  EntityManager em;

  public RepositorioComunidades(EntityManager em){
    this.em = em;
  }

  @Override
  public List buscarTodos() {
    return em.createQuery("from " + Comunidad.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return em.find(Comunidad.class, id);
  }

  @Override
  public void guardar(Object... comunidad) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive()) tx.begin();
    for (Object o : comunidad) {
      em.persist(o);
    }
    tx.commit();
  }

  @Override
  public void actualizar(Object comunidad) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive()) tx.begin();
    em.merge(comunidad);
    tx.commit();
  }

  @Override
  public void eliminar(Object comunidad) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive()) tx.begin();
    em.remove(comunidad);
    tx.commit();
  }
}
