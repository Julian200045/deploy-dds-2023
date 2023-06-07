package domain.repositorios.entidadesprestadoras;

import domain.repositorios.entidades.RepoEntidades;
import domain.repositorios.entidadesprestadoras.RepoEntidadesPrestadoras;
import domain.repositorios.usuarios.RepoUsuarios;
import java.util.stream.Collectors;
import services.csv.LectorCSV;
import domain.entidades.Entidad;
import domain.organismos.EntidadPrestadora;
import domain.usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class RepositorioEntidadesPrestadoras implements RepoEntidadesPrestadoras {
	@Getter
	List<EntidadPrestadora> entidadesPrestadoras = new ArrayList<>();
	RepoEntidades repoEntidades;
	RepoUsuarios repoUsuarios;

	public RepositorioEntidadesPrestadoras(RepoEntidades repoEntidades,RepoUsuarios repoUsuarios){
		this.repoEntidades = repoEntidades;
		this.repoUsuarios = repoUsuarios;
	}

	public void cargarEntidadesPrestadoras(LectorCSV lector) throws java.io.FileNotFoundException ,java.io.IOException, com.opencsv.exceptions.CsvValidationException {
		int id = 0;
		while(!lector.getDatosEntidadesPrestadoras().isEmpty()){
			String[] datos = lector.getDatosEntidadesPrestadoras().get(0);
			List<Integer> ids = getIds(datos);
			List<Entidad> entidades = repoEntidades.devolverPorIds(ids);
			Usuario responsable = repoUsuarios.devolverPorId(Integer.parseInt(datos[1]));
			agregarEntidadPrestadora(id, datos[0], responsable , datos[2], entidades);
			lector.getDatosEntidadesPrestadoras().remove(0);
			id +=1;
		}
	}

	public List<Integer> getIds(String[] datos){
		List<Integer> ids = new ArrayList<>();
		for (int i = 3; i < datos.length; i++) {
			ids.add(Integer.parseInt(datos[i]));
		}
		return ids;
	}

	public void agregarEntidadPrestadora(int id, String nombre, Usuario responsable, String email, List<Entidad> entidades){
		entidadesPrestadoras.add(new EntidadPrestadora(id, nombre, responsable, email, entidades));
	}

	public EntidadPrestadora devolverPorId(int id){
		EntidadPrestadora entidad;

		entidad = entidadesPrestadoras.stream().filter(entidad1 -> entidad1.getId() == id).toList().get(0); //rompe aca en caso de que se pida un id que no existe
		return entidad;
	}
	public List<EntidadPrestadora> devolverPorIds(List<Integer> ids){
		List<EntidadPrestadora> listaEntidades = new ArrayList<>();
		for(int i=0; i < ids.size(); i++){
			listaEntidades.add(devolverPorId(ids.get(i)));
		}
		return listaEntidades;
	}
}
