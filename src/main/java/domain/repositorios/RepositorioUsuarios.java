package domain.repositorios;

import domain.entidades.Entidad;
import domain.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

public class RepositorioUsuarios {
	@Getter
	List<Usuario> usuarios = new ArrayList<>();
	static RepositorioUsuarios instancia;

	public static RepositorioUsuarios instancia() {
		if(instancia == null) {
			instancia = new RepositorioUsuarios();
			return instancia;
		}
		else {
			return instancia;
		}
	}

	public void nuevoUsuario(int id, String nombre, String contrasenia) throws java.io.IOException{
		Usuario usuario = new Usuario(id, nombre, contrasenia);
		usuarios.add(usuario);
	}

	public Usuario devolverPorId(int id){
		Usuario usuario;
		usuario = usuarios.stream().filter(usuario1 -> usuario1.getId() == id).collect(Collectors.toList()).get(0); //rompe aca en caso de que se pida un id que no existe
		return usuario;
	}
}
