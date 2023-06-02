package domain.organismos;

import domain.entidades.Entidad;
import domain.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Organismo {
	@Getter
	String nombre;
	Usuario usuario;
	String emailResponsable;
	List<Entidad> entidades;

	public Organismo(String nombre, String email, List<Entidad> entidades){
		this.nombre = nombre;
		//this.usuario = usuario;
		this.emailResponsable = email;
		this.entidades = entidades;
	}
}
