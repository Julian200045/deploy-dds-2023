package models.repositorios.notificaciones;

import models.entities.incidentes.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.services.notificador.EstadoEnvio;
import models.services.notificador.Notificacion;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositorioNotificaciones implements RepoNotificaciones, WithSimplePersistenceUnit {
  List<Notificacion> notificaciones = new ArrayList<>();

  public List<Notificacion> getAllByEstado(EstadoEnvio estadoEnvio) {
    return notificaciones.stream().filter(notificacion -> notificacion.getEstadoEnvio().equals(estadoEnvio)).toList();
  }


  public void add(Notificacion notificacion) {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(notificacion);
    tx.commit();
    notificaciones.add(notificacion);
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
