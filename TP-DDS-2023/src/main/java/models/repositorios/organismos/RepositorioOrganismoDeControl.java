package models.repositorios.organismos;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityTransaction;
import lombok.Getter;
import models.entities.incidentes.Incidente;
import models.entities.organismos.EntidadPrestadora;
import models.entities.organismos.OrganismoDeControl;
import models.entities.servicios.Servicio;
import models.entities.usuarios.Usuario;
import models.repositorios.ICrudRepository;

public class RepositorioOrganismoDeControl implements ICrudRepository, WithSimplePersistenceUnit {
  @Getter
  List<OrganismoDeControl> organismosDeControl = new ArrayList<>();

  public void agregarOrganismoDeControl(String nombre, Usuario responsable, String email, Servicio servicio, List<EntidadPrestadora> entidades) {
    organismosDeControl.add(new OrganismoDeControl(nombre, responsable, email, entidades, servicio));
  }

  @Override
  public void guardar(Object... organismoDeControl) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    for (Object o :
        organismoDeControl) {
      entityManager().persist(o);
    }
    tx.commit();
  }

  @Override
  public void eliminar(Object organismoDeControl) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    entityManager().remove(organismoDeControl);
    tx.commit();
  }

  @Override
  public void actualizar(Object organismoDeControl) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    entityManager().merge(organismoDeControl);
    tx.commit();
  }

  @Override
  public List buscarTodos() {
    return entityManager().createQuery("from " + Incidente.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return entityManager().find(OrganismoDeControl.class, id);
  }
}
