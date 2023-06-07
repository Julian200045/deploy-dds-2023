package domain.repositorios.servicios;

import domain.servicios.Servicio;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioServicios implements RepoServicios{
	List<Servicio> Servicios = new ArrayList<>();

	public Servicio devolverPorId(int id){
		Servicio servicio;
		servicio = Servicios.stream().filter(entidad1 -> entidad1.getId() == id).collect(Collectors.toList()).get(0); //rompe aca en caso de que se pida un id que no existe
		return servicio;
	}

	public void agregarServicio(int id, String nombre){
		Servicios.add(new Servicio(id, nombre));
	}
}
