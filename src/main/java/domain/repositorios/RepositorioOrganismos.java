package domain.repositorios;

import com.opencsv.CSVReader;
import domain.csv.LectorCSV;
import domain.entidades.Entidad;
import domain.organismos.Organismo;
import domain.usuarios.Usuario;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class RepositorioOrganismos {
	@Getter
	List<Organismo> organismos = new ArrayList<>();

	public void cargarOrganismos() throws java.io.FileNotFoundException ,java.io.IOException, com.opencsv.exceptions.CsvValidationException {
		LectorCSV lector = new LectorCSV();
		RepositorioEntidades repositorioEntidades = RepositorioEntidades.instancia();
		RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.instancia();
		while(!lector.getDatosOrganismos().isEmpty()){
			String[] datos = lector.getDatosOrganismos().get(0);
			List<Integer> ids = getIds(datos);
			List<Entidad> entidades = repositorioEntidades.devolverPorIds(ids);
			Usuario responsable = repositorioUsuarios.devolverPorId(Integer.parseInt(datos[1]));
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
		organismos.add(new Organismo(nombre, responsable, email, entidades));
	}
}
