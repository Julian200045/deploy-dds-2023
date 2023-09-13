package repositorios.usuarios;

import domain.servicios.Servicio;
import domain.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;

import javax.persistence.EntityTransaction;

public class RepositorioUsuarios implements RepoUsuarios, WithSimplePersistenceUnit {
	@Getter
	List<Usuario> usuarios = new ArrayList<>();


	public void nuevoUsuario(int id, String nombre, String contrasenia) throws java.io.IOException{
		//Usuario usuario = new Usuario(id, nombre, contrasenia);
		//usuarios.add(usuario);
	}

	public void add(Usuario usuario) {

		EntityTransaction tx = entityManager().getTransaction();
		tx.begin();
		entityManager().persist(usuario);
		tx.commit();
		usuarios.add(usuario);
	}

	public void eliminar(Usuario usuario){
		EntityTransaction tx = entityManager().getTransaction();
		tx.begin();
		entityManager().remove(usuario);
		tx.commit();
	}

	public void modificar(Usuario usuario){
		EntityTransaction tx = entityManager().getTransaction();
		tx.begin();
		entityManager().merge(usuario);
		tx.commit();
	}

	public List<Usuario> getAll() {
		return entityManager().createQuery("from" + Usuario.class.getName()).getResultList();
	}

	public Usuario devolverPorId(int id){
		return entityManager().find(Usuario.class,id);
	}
}
