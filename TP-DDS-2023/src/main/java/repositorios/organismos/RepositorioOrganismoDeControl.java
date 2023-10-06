package repositorios.organismos;

import domain.organismos.EntidadPrestadora;
import domain.organismos.OrganismoDeControl;
import domain.servicios.Servicio;
import domain.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;
import services.notificador.Notificacion;

import javax.persistence.EntityTransaction;

public class RepositorioOrganismoDeControl implements RepoOrganismoDeControl, WithSimplePersistenceUnit {

  public void add(OrganismoDeControl organismoDeControl) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(organismoDeControl);
    tx.commit();
  }

  public void eliminar(OrganismoDeControl organismoDeControl){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(organismoDeControl);
    tx.commit();
  }

  public void modificar(OrganismoDeControl organismoDeControl){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(organismoDeControl);
    tx.commit();
  }

  public List<OrganismoDeControl> getAll() {
    return entityManager().createQuery("from" + OrganismoDeControl.class.getName()).getResultList();
  }

  public OrganismoDeControl devolverPorId(int id){
    return entityManager().find(OrganismoDeControl.class,id);
  }
}
