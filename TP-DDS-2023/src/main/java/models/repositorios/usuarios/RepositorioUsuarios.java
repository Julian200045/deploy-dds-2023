package models.repositorios.usuarios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityTransaction;
import lombok.Getter;
import models.entities.usuarios.Usuario;
import models.repositorios.ICrudRepository;

public class RepositorioUsuarios implements ICrudRepository, WithSimplePersistenceUnit {
  @Getter
  List<Usuario> usuarios = new ArrayList<>();

  public void nuevoUsuario(int id, String nombre, String contrasenia) throws java.io.IOException {
    //Usuario usuario = new Usuario(id, nombre, contrasenia);
    //usuarios.add(usuario);
  }

  @Override
  public void guardar(Object... usuario) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive()) tx.begin();
    for (Object o : usuario) {
      entityManager().persist(o);
    }
    tx.commit();
  }

  @Override
  public void eliminar(Object usuario) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive()) tx.begin();
    entityManager().remove(usuario);
    tx.commit();
  }

  @Override
  public void actualizar(Object usuario) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive()) tx.begin();
    entityManager().merge(usuario);
    tx.commit();
  }

  @Override
  public List buscarTodos() {
    return entityManager().createQuery("from " + Usuario.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return entityManager().find(Usuario.class, id);
  }
}
