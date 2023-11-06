package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import models.entities.comunidades.Comunidad;
import models.entities.incidentes.EstadoIncidente;
import models.entities.incidentes.Incidente;
import models.entities.servicios.PrestacionDeServicio;
import models.repositorios.ICrudRepository;
import org.hibernate.FlushMode;
import org.hibernate.Session;

public class RepositorioIncidentes implements ICrudRepository, WithSimplePersistenceUnit {

  EntityManager em;

  public RepositorioIncidentes(EntityManager em){
    this.em = em;
  }
  @Override
  public void guardar(Object... incidente) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    for (Object inci :
        incidente) {
      em.persist(inci);
    }
    tx.commit();
  }

  @Override
  public void eliminar(Object incidente) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    em.remove(incidente);
    tx.commit();
  }

  @Override
  public void actualizar(Object incidente) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive())
      tx.begin();
    em.merge(incidente);
    tx.commit();
  }

  public void limpiarCacheIncidentes(){
    em.clear();
  }

  @Override
  public List buscarTodos() {
    this.limpiarCacheIncidentes();
    return em.createQuery("from " + Incidente.class.getName()).getResultList();
  }

  public List buscarTodosFiltrados(String establecimiento, String servicio, String comunidad){
    this.limpiarCacheIncidentes();
    String jpql = "SELECT i from Incidente i join i.prestacionDeServicio p join p.establecimiento e join p.servicio s join i.comunidad c where e.nombre LIKE '"+establecimiento+"%' and s.nombre LIKE '"+servicio+"%' and c.nombre LIKE '"+comunidad+"%'";
    Query query = em.createQuery(jpql);
    try {
      return query.getResultList();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }
  }

  public List buscarPorEstablecimiento(long idEstablecimiento){

    String jpql = "SELECT i from Incidente i join i.prestacionDeServicio p where p.establecimiento.id = :id_establecimiento";
    Query query = em.createQuery(jpql);
    query.setParameter("id_establecimiento",idEstablecimiento);
    try {
      return query.getResultList();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }
  }

  public List buscarPorServicio(long idServicio){

    String jpql = "SELECT i from Incidente i join i.prestacionDeServicio p where p.servicio.id = :id_servicio";
    Query query = em.createQuery(jpql);
    query.setParameter("id_servicio",idServicio);
    try {
      return query.getResultList();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }
  }

  public List buscarPorComunidad(long idComunidad){

    String jpql = "SELECT i from Incidente i join i.comunidad where i.comunidad.id  = :id_comunidad";
    Query query = em.createQuery(jpql);
    query.setParameter("id_comunidad",idComunidad);
    try {
      return query.getResultList();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }
  }

  public List buscarPorEstado(String estado){

    String jpql = "SELECT i from Incidente i where i.estado = :estado";
    Query query = em.createQuery(jpql);
    query.setParameter("estado",estado);
    try {
      return query.getResultList();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }
  }

  @Override
  public Object buscar(Long id) {
    return em.find(Incidente.class, id);
  }

  public List buscarPorEstado(EstadoIncidente estadoIncidente) {
    if (estadoIncidente == null) {
      throw new IllegalArgumentException("El estado del incidente no puede ser nulo.");
    }
    String jpql = "SELECT i FROM Incidente i WHERE i.estado = :parametro";
    Query query = em.createQuery(jpql);
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
    Query query = em.createQuery(jpql);
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
    Query query = em.createQuery(jpql);
    query.setParameter("parametro", prestacionDeServicio);

    try {
      return query.getResultList();
    } catch (NoResultException e) {
      return Collections.emptyList();
    }
  }
}
