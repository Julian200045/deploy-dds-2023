package models.repositorios.personas;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.entities.comunidades.Persona;
import models.entities.usuarios.Usuario;
import models.repositorios.ICrudRepository;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioPersona implements ICrudRepository, WithSimplePersistenceUnit {

  @Override
  public List buscarTodos() {
    return entityManager().createQuery("from " + Persona.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return entityManager().find(Persona.class, id);
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
