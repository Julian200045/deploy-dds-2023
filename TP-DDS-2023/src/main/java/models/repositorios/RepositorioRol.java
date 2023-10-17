package models.repositorios;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioRol  implements ICrudRepository, WithSimplePersistenceUnit {
    @Override
    public List buscarTodos() {
        return null;
    }

    @Override
    public Object buscar(Long id) {
        return null;
    }

    @Override
    public void guardar(Object... rol) {
        EntityTransaction tx = entityManager().getTransaction();
        if (!tx.isActive()) tx.begin();
        for (Object o : rol) {
            entityManager().persist(o);
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
