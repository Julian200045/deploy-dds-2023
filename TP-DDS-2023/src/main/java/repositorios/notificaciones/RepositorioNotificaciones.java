package repositorios.notificaciones;

import domain.incidentes.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import services.notificador.EstadoEnvio;
import services.notificador.Notificacion;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositorioNotificaciones implements RepoNotificaciones, WithSimplePersistenceUnit {

  public List<Notificacion> getAllByEstado(EstadoEnvio estadoEnvio) {
    return entityManager().createQuery("from" + Notificacion.class.getName() + "where estadoEnvio = " + estadoEnvio.toString() ).getResultList();
  }


  public void add(Notificacion notificacion) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(notificacion);
    tx.commit();
  }

  public void eliminar(Notificacion notificacion){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().remove(notificacion);
    tx.commit();
  }

  public void modificar(Notificacion notificacion){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().merge(notificacion);
    tx.commit();
  }

  public List<Notificacion> getAll() {
    return entityManager().createQuery("from" + Notificacion.class.getName()).getResultList();
  }

  public Notificacion devolverPorId(int id){
    return entityManager().find(Notificacion.class,id);
  }
}
