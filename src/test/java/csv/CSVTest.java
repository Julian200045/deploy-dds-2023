package csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.csv.LectorCSV;
import domain.repositorios.RepositorioEntidades;
import domain.repositorios.RepositorioOrganismos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CSVTest {


	@Test
	public void lectorLeeAlIniciar() throws java.io.IOException, com.opencsv.exceptions.CsvValidationException{
		LectorCSV lector = new LectorCSV();
		assertTrue(lector.getDatosOrganismos().size() > 0);
	}


	@Test
	public void getIdsFunciona() throws java.io.FileNotFoundException, java.io.IOException, com.opencsv.exceptions.CsvValidationException{
		LectorCSV lector = new LectorCSV();
		RepositorioOrganismos repo = new RepositorioOrganismos();

		System.out.println(repo.getIds(lector.getDatosOrganismos().get(0)));
	}

	@Test
	public void test() throws  java.io.IOException, com.opencsv.exceptions.CsvValidationException{
		RepositorioOrganismos repoOrganismos = new RepositorioOrganismos();
		RepositorioEntidades repoEntidad = RepositorioEntidades.instancia();
		repoEntidad.nuevaEntidad(1,"entidad1");
		repoEntidad.nuevaEntidad(2,"entidad2");
		repoEntidad.nuevaEntidad(3,"entidad3");
		repoOrganismos.cargarOrganismos();
		assertTrue(repoOrganismos.getOrganismos().size() > 0);
		assertTrue(repoOrganismos.getOrganismos().get(0).getNombre().equals("afip"));
	}


}
