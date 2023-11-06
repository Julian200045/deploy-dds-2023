package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.entities.servicios.PrestacionDeServicio;
import models.repositorios.ICrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class RepositorioPrestacionesDeServicio implements ICrudRepository, WithSimplePersistenceUnit {

  EntityManager em;

  public RepositorioPrestacionesDeServicio(EntityManager em){
    this.em = em;
  }

  @Override
  public List buscarTodos() {
    return em.createQuery("from " + PrestacionDeServicio.class.getName()).getResultList();
  }

  @Override
  public Object buscar(Long id) {
    return em.find(PrestacionDeServicio.class, id);
  }

  public Object buscarPorEstablecimiento(Long idEstablecimiento){
    String jpql = "SELECT p from PrestacionDeServicio p join p.establecimiento e where e.id = :id_establecimiento";
    Query query = em.createQuery(jpql);
    query.setParameter("id_establecimiento",idEstablecimiento);

    return query.getResultList();
  }

  public Object buscarPorEstablecimientoYServicio(String nombreEstablecimiento, String nombreServicio){
    String jpql = "SELECT p from PrestacionDeServicio p join p.establecimiento e join p.servicio s where e.nombre = :nombre_establecimiento and s.nombre = :nombre_servicio";
    Query query = em.createQuery(jpql);
    query.setParameter("nombre_establecimiento",nombreEstablecimiento);
    query.setParameter("nombre_servicio",nombreServicio);

    return query.getSingleResult();
  }

  @Override
  public void guardar(Object... prestacionDeServicio) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive()) tx.begin();
    for (Object o : prestacionDeServicio) {
      em.persist(o);
    }
    tx.commit();
  }

  @Override
  public void actualizar(Object prestacionDeServicio) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive()) tx.begin();
    em.merge(prestacionDeServicio);
    tx.commit();
  }

  @Override
  public void eliminar(Object prestacionDeServicio) {
    EntityTransaction tx = em.getTransaction();
    if (!tx.isActive()) tx.begin();
    em.remove(prestacionDeServicio);
    tx.commit();
  }
}
