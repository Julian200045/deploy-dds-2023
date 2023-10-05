package models.repositorios.organismos;

import models.entities.organismos.EntidadPrestadora;
import models.entities.organismos.OrganismoDeControl;
import models.entities.servicios.Servicio;
import models.entities.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;
import models.services.notificador.Notificacion;

import javax.persistence.EntityTransaction;

public class RepositorioOrganismoDeControl implements RepoOrganismoDeControl, WithSimplePersistenceUnit {
  @Getter
  List<OrganismoDeControl> organismosDeControl = new ArrayList<>();
  public void agregarOrganismoDeControl(String nombre, Usuario responsable, String email, Servicio servicio, List<EntidadPrestadora> entidades) {
    organismosDeControl.add(new OrganismoDeControl(nombre, responsable, email, entidades, servicio));
  }

  public void add(OrganismoDeControl organismoDeControl) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(organismoDeControl);
    tx.commit();
    organismosDeControl.add(organismoDeControl);
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
