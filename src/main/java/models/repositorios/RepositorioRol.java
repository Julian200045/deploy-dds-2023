package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.entities.roles.Rol;
import models.entities.usuarios.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioRol  implements ICrudRepository, WithSimplePersistenceUnit {

    EntityManager em;

    public RepositorioRol(EntityManager em){
        this.em = em;
    }

    @Override
    public List buscarTodos() {
        return em.createQuery("from " + Rol.class.getName()).getResultList();
    }

    @Override
    public Object buscar(Long id) {
        return em.find(Rol.class, id);
    }

    @Override
    public void guardar(Object... rol) {
        EntityTransaction tx = em.getTransaction();
        if (!tx.isActive()) tx.begin();
        for (Object o : rol) {
            em.persist(o);
        }
        tx.commit();
    }

    @Override
    public void actualizar(Object o) {

    }

    @Override
    public void eliminar(Object o) {

    }
}
