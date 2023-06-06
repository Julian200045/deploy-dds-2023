package domain.repositorios;

import domain.entidades.Entidad;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

public class RepositorioEntidades {
	@Getter
	List<Entidad> entidades = new ArrayList<>();
	static RepositorioEntidades instancia;

	public static RepositorioEntidades instancia() {
		if(instancia == null) {
			instancia = new RepositorioEntidades();
			return instancia;
		}
		else {
			return instancia;
		}
	}

	public void nuevaEntidad(int id, String nombre){
		Entidad entidad = new Entidad(id, nombre);
		entidades.add(entidad);
	}

	public Entidad devolverPorId(int id){
		Entidad entidad;
		entidad = entidades.stream().filter(entidad1 -> entidad1.getId() == id).collect(Collectors.toList()).get(0); //rompe aca en caso de que se pida un id que no existe
		return entidad;
	}

	public List<Entidad> devolverPorIds(List<Integer> ids){
		List<Entidad> listaEntidades = new ArrayList<>();
		for(int i=0; i < ids.size(); i++){
			listaEntidades.add(devolverPorId(ids.get(i)));
		}
		return listaEntidades;
	}
}
