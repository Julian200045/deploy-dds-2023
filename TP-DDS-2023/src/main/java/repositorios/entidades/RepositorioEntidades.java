package repositorios.entidades;

import domain.entidades.Entidad;
import java.util.ArrayList;
import java.util.List;

import domain.entidades.TipoEntidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public class RepositorioEntidades implements RepoEntidades, WithSimplePersistenceUnit {
  @Getter
  List<Entidad> entidades = new ArrayList<>();

  public void agregarEntidad(int id, String nombre, TipoEntidad tipo) {
    Entidad entidad = new Entidad(id, nombre,tipo);
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(entidad);
    tx.commit();
    entidades.add(entidad);
  }

  public void eliminar(Entidad entidad){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(entidad);
    tx.commit();
  }

  public void modificar(Entidad entidad){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(entidad);
    tx.commit();
  }

	public Entidad devolverPorId(int id){
		return entityManager().find(Entidad.class,id);
	}

  public List<Entidad> devolverPorIds(List<Integer> ids) {
    return entityManager().createQuery("from" + Entidad.class.getName()).getResultList();
  }


}
