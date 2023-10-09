package models.services.importadorDatosCSV;

import models.entities.entidades.Entidad;
import models.repositorios.entidades.RepoEntidades;
import models.repositorios.entidadesprestadoras.RepoEntidadesPrestadoras;
import models.repositorios.usuarios.RepoUsuarios;
import models.entities.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;
import models.services.csv.LectorCSV;

public class ImportadorEntidadesPrestadoras {
	LectorCSV lector;
	RepoEntidades repoEntidades;
	RepoUsuarios repoUsuarios;
	RepoEntidadesPrestadoras repoEntidadesPrestadoras;

	public ImportadorEntidadesPrestadoras(LectorCSV lector, RepoEntidades repoEntidades, RepoUsuarios repoUsuarios, RepoEntidadesPrestadoras repoEntidadesPrestadoras){
		this.lector = lector;
		this.repoEntidades = repoEntidades;
		this.repoUsuarios = repoUsuarios;
		this.repoEntidadesPrestadoras = repoEntidadesPrestadoras;
	}

	public void cargarDatos() throws java.io.FileNotFoundException, java.io.IOException, com.opencsv.exceptions.CsvValidationException {
		int id = 0;
		while (!lector.getDatos().isEmpty()) {
			String[] datos = lector.getDatos().get(0);
			List<Integer> ids = getIds(datos);
			List<Entidad> entidades = repoEntidades.devolverPorIds(ids);
			Usuario responsable = repoUsuarios.devolverPorId(Integer.parseInt(datos[1]));
			repoEntidadesPrestadoras.agregarEntidadPrestadora(id, datos[0], responsable, datos[2], entidades);
			lector.getDatos().remove(0);
			id += 1;
		}
	}

	public List<Integer> getIds(String[] datos) {
		List<Integer> ids = new ArrayList<>();
		for (int i = 3; i < datos.length; i++) {
			ids.add(Integer.parseInt(datos[i]));
		}
		return ids;
	}
}
