package services.importadorDatosCSV;

import domain.organismos.EntidadPrestadora;
import repositorios.entidades.entidadesprestadoras.RepoEntidadesPrestadoras;
import repositorios.organismos.RepoOrganismoDeControl;
import repositorios.servicios.RepoServicios;
import repositorios.usuarios.RepoUsuarios;
import domain.servicios.Servicio;
import domain.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;

import services.csv.LectorCSV;

public class ImportadorOrganismos implements ImportadorDatosCSV{
	LectorCSV lector;
	RepoServicios repoServicios;
	RepoEntidadesPrestadoras repoEntidadesPrestadoras;
	RepoUsuarios repoUsuarios;
	RepoOrganismoDeControl repoOrganismoDeControl;

	public ImportadorOrganismos(LectorCSV lector, RepoEntidadesPrestadoras repoEntidades, RepoUsuarios repoUsuarios, RepoServicios repoServicio, RepoOrganismoDeControl repoOrganismoDeControl){
		this.lector = lector;
		this.repoEntidadesPrestadoras = repoEntidades;
		this.repoUsuarios = repoUsuarios;
		this.repoServicios = repoServicio;
		this.repoOrganismoDeControl = repoOrganismoDeControl;
	}

	public void cargarDatos() throws java.io.FileNotFoundException, java.io.IOException, com.opencsv.exceptions.CsvValidationException {
		while (!lector.getDatos().isEmpty()) {
			String[] datos = lector.getDatos().get(0);
			List<Integer> ids = getIds(datos);
			List<EntidadPrestadora> entidades = repoEntidadesPrestadoras.devolverPorIds(ids);
			Usuario responsable = repoUsuarios.devolverPorId(Integer.parseInt(datos[1]));
			Servicio servicio = repoServicios.devolverPorId(Integer.parseInt(datos[3]));
			repoOrganismoDeControl.agregarOrganismoDeControl(datos[0], responsable, datos[2], servicio, entidades);
			lector.getDatos().remove(0);
		}
	}

	public List<Integer> getIds(String[] datos) {
		List<Integer> ids = new ArrayList<>();
		for (int i = 4; i < datos.length; i++) {
			ids.add(Integer.parseInt(datos[i]));
		}
		return ids;
	}

}
