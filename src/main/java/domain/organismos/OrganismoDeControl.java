package domain.organismos;

import domain.entidades.Entidad;
import domain.servicios.Servicio;
import domain.usuarios.Usuario;
import java.util.List;
import lombok.Getter;

public class OrganismoDeControl {
	@Getter
	String nombre;
	@Getter
	Usuario usuario;
	String emailResponsable;
	List<EntidadPrestadora> entidadesPrestadoras;
	Servicio servicio;

	public OrganismoDeControl(String nombre, Usuario usuario, String email, List<EntidadPrestadora> entidades, Servicio servicio){
		this.nombre = nombre;
		this.usuario = usuario;
		this.emailResponsable = email;
		this.entidadesPrestadoras = entidades;
		this.servicio = servicio;
	}
}
