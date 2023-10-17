package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import models.entities.comunidades.Comunidad;
import models.entities.incidentes.EstadoIncidente;
import models.entities.incidentes.Incidente;
import models.entities.servicios.PrestacionDeServicio;
import models.repositorios.ICrudRepository;

public class RepositorioIncidentes implements ICrudRepository, WithSimplePersistenceUnit {

  @Override
  public void guardar(Object... incidente) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    for (Object inci :
        incidente) {
      entityManager().persist(inci);
      System.out.println("Entre a guardar el incidente");
    }
    tx.commit();
  }

  @Override
  public void eliminar(Object incidente) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    entityManager().remove(incidente);
    tx.commit();
  }

  @Override
  public void actualizar(Object incidente) {
    EntityTransaction tx = entityManager().getTransaction();
    if (!tx.isActive())
      tx.begin();
    entityManager().merge(incidente);
    tx.commit();
  }

  @Override
  public List buscarTodos() {
    return entityManager().createQuery("from " + Incidente.class.getName()).getResultList();
  }

  public List buscarTodosFiltrados(String establecimiento, String servicio, String comunidad){
    String jpql = "SELECT i from Incidente i join i.prestacionDeServicio p join p.establecimiento e join p.servicio s join i.comunidad c where e.nombre LIKE '"+establecimiento+"%' and s.nombre LIKE '"+servicio+"%' and c.nombre LIKE '"+comunidad+"%'";
    Query query = entityManager().createQuery(jpql);

    return query.getResultList();
  }

  public List buscarPorEstablecimiento(long idEstablecimiento){

    String jpql = "SELECT i from Incidente i join prestacion on prestacion.id = i.prestacion_id where prestacion.establecimiento_id = :id_establecimiento";
    Query query = entityManager().createQuery(jpql);
    query.setParameter("id_establecimiento",idEstablecimiento);
    return query.getResultList();
  }

  public List buscarPorServicio(long idServicio){

    String jpql = "SELECT i from Incidente i join prestacion on prestacion.id = i.prestacion_id where prestacion.servicio_id = :id_servicio";
    Query query = entityManager().createQuery(jpql);
    query.setParameter("id_servicio",idServicio);
    return query.getResultList();
  }

  public List buscarPorComunidad(long idComunidad){

    String jpql = "SELECT i from Incidente i where i.comunidad_id = :id_comunidad";
    Query query = entityManager().createQuery(jpql);
    query.setParameter("id_comunidad",idComunidad);
    return query.getResultList();
  }

  public List buscarPorEstado(String estado){

    String jpql = "SELECT i from Incidente i where i.estado = :estado";
    Query query = entityManager().createQuery(jpql);
    query.setParameter("estado",estado);
    return query.getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return entityManager().find(Incidente.class, id);
  }

  public List buscarPorEstado(EstadoIncidente estadoIncidente) {
    if (estadoIncidente == null) {
      throw new IllegalArgumentException("El estado del incidente no puede ser nulo.");
    }
    String jpql = "SELECT i FROM Incidente i WHERE i.estado = :parametro";
    Query query = entityManager().createQuery(jpql);
    query.setParameter("parametro", estadoIncidente);

    try {
        return query.getResultList();
    } catch (NoResultException e) {
        return Collections.emptyList();
    }
  }

  public List buscarPorComunidad(Comunidad comunidad) {
    if (comunidad == null) {
      throw new IllegalArgumentException("La comunidad no puede ser nula.");
    }
    String jpql = "SELECT i FROM Incidente i WHERE i.comunidad = :parametro";
    Query query = entityManager().createQuery(jpql);
    query.setParameter("parametro", comunidad);

    try {
      return query.getResultList();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }
  }

  public List buscarPorPrestacion(PrestacionDeServicio prestacionDeServicio) {
    if (prestacionDeServicio == null) {
      throw new IllegalArgumentException("La prestacion de servicio no puede ser nula.");
    }
    String jpql = "SELECT i FROM Incidente i WHERE i.prestacionDeServicio = :parametro";
    Query query = entityManager().createQuery(jpql);
    query.setParameter("parametro", prestacionDeServicio);

    try {
      return query.getResultList();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }
  }
}
