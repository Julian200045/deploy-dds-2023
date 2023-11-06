package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.entities.organismos.OrganismoDeControl;
import models.repositorios.ICrudRepository;

public class RepositorioOrganismoDeControl implements ICrudRepository, WithSimplePersistenceUnit {

  EntityManager em;

  public RepositorioOrganismoDeControl(EntityManager em){
    this.em = em;
  }

  @Override
  public void guardar(Object... organismoDeControl) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    for (Object o :
        organismoDeControl) {
      em.persist(o);
    }
    tx.commit();
  }

  @Override
  public void eliminar(Object organismoDeControl) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    em.remove(organismoDeControl);
    tx.commit();
  }

  @Override
  public void actualizar(Object organismoDeControl) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    em.merge(organismoDeControl);
    tx.commit();
  }

  @Override
  public List buscarTodos() {
    return em.createQuery("from " + OrganismoDeControl.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return em.find(OrganismoDeControl.class, id);
  }
}
