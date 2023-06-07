package domain.repositorios.organismos;

import domain.repositorios.entidades.RepoEntidades;
import domain.repositorios.usuarios.RepoUsuarios;
import services.csv.LectorCSV;
import domain.entidades.Entidad;
import domain.organismos.EntidadPrestadora;
import domain.usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class RepositorioEntidadesPrestadoras implements RepoOrganismos{
	@Getter
	List<EntidadPrestadora> entidadPrestadoras = new ArrayList<>();
	RepoEntidades repoEntidades;
	RepoUsuarios repoUsuarios;

	public RepositorioEntidadesPrestadoras(RepoEntidades repoEntidades,RepoUsuarios repoUsuarios){
		this.repoEntidades = repoEntidades;
		this.repoUsuarios = repoUsuarios;
	}

	public void cargarOrganismos(LectorCSV lector) throws java.io.FileNotFoundException ,java.io.IOException, com.opencsv.exceptions.CsvValidationException {
		while(!lector.getDatosOrganismos().isEmpty()){
			String[] datos = lector.getDatosOrganismos().get(0);
			List<Integer> ids = getIds(datos);
			List<Entidad> entidades = repoEntidades.devolverPorIds(ids);
			Usuario responsable = repoUsuarios.devolverPorId(Integer.parseInt(datos[1]));
			agregarOrganismo(datos[0], responsable , datos[2], entidades);
			lector.getDatosOrganismos().remove(0);
		}
	}

	public List<Integer> getIds(String[] datos){
		List<Integer> ids = new ArrayList<>();
		for (int i = 3; i < datos.length; i++) {
			ids.add(Integer.parseInt(datos[i]));
		}
		return ids;
	}

	public void agregarOrganismo(String nombre, Usuario responsable, String email, List<Entidad> entidades){
		organismos.add(new EntidadPrestadora(nombre, responsable, email, entidades));
	}
}
