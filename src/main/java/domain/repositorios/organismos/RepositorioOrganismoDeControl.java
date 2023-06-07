package domain.repositorios.organismos;

import domain.organismos.EntidadPrestadora;
import domain.organismos.OrganismoDeControl;
import domain.repositorios.servicios.RepoServicios;
import domain.repositorios.usuarios.RepoUsuarios;
import domain.servicios.Servicio;
import domain.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import services.csv.LectorCSV;

public class RepositorioOrganismoDeControl implements RepoOrganismoDeControl{
	@Getter
	List<OrganismoDeControl> organismosDeControl = new ArrayList<>();
	RepoServicios repoServicios;
	RepoEntidadesPrestadoras repoEntidadesPrestadoras;
	RepoUsuarios repoUsuarios;

	public RepositorioOrganismoDeControl(RepoEntidadesPrestadoras repoEntidades,RepoUsuarios repoUsuarios, RepoServicios repoServicio){
		this.repoEntidadesPrestadoras = repoEntidades;
		this.repoUsuarios = repoUsuarios;
		this.repoServicios = repoServicio;
	}

	public void cargarOrganismosDeControl(LectorCSV lector) throws java.io.FileNotFoundException ,java.io.IOException, com.opencsv.exceptions.CsvValidationException {
		while(!lector.getDatosOrganismosDeControl().isEmpty()){
			String[] datos = lector.getDatosOrganismosDeControl().get(0);
			List<Integer> ids = getIds(datos);
			List<EntidadPrestadora> entidades = repoEntidadesPrestadoras.devolverPorIds(ids);
			Usuario responsable = repoUsuarios.devolverPorId(Integer.parseInt(datos[1]));
			Servicio servicio = repoServicios.devolverPorId(Integer.parseInt(datos[3]));
			agregarOrganismoDeControl(datos[0], responsable , datos[2], servicio, entidades);
			lector.getDatosOrganismosDeControl().remove(0);

		}
	}

	public List<Integer> getIds(String[] datos){
		List<Integer> ids = new ArrayList<>();
		for (int i = 4; i < datos.length; i++) {
			ids.add(Integer.parseInt(datos[i]));
		}
		return ids;
	}

	public void agregarOrganismoDeControl(String nombre, Usuario responsable, String email, Servicio servicio,List<EntidadPrestadora> entidades){
		organismosDeControl.add(new OrganismoDeControl(nombre, responsable, email, entidades, servicio));
	}
}
