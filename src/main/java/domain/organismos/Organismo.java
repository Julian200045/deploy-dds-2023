package domain.organismos;

import domain.entidades.Entidad;
import domain.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Organismo {
	@Getter
	String nombre;
	@Getter
	Usuario usuario;
	String emailResponsable;
	List<Entidad> entidades;

	public Organismo(String nombre, Usuario usuario,String email, List<Entidad> entidades){
		this.nombre = nombre;
		this.usuario = usuario;
		this.emailResponsable = email;
		this.entidades = entidades;
	}
}
