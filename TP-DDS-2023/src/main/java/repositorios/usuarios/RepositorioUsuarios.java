package repositorios.usuarios;

import domain.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

public class RepositorioUsuarios implements RepoUsuarios{
	@Getter
	List<Usuario> usuarios = new ArrayList<>();


	public void nuevoUsuario(int id, String nombre, String contrasenia) throws java.io.IOException{
		//Usuario usuario = new Usuario(id, nombre, contrasenia);
		//usuarios.add(usuario);
	}

	public Usuario devolverPorId(int id){
		Usuario usuario;
		usuario = usuarios.stream().filter(usuario1 -> usuario1.getId() == id).collect(Collectors.toList()).get(0); //rompe aca en caso de que se pida un id que no existe
		return usuario;
	}
}
