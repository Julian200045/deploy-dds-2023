package csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.csv.LectorCSV;
import domain.repositorios.RepositorioEntidades;
import domain.repositorios.RepositorioOrganismos;
import domain.repositorios.RepositorioUsuarios;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

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
		String[] datos = {"nombre","usuario","email","1","2", "3"};
		assertTrue(repo.getIds(datos).size() == 3);
	}

	@Test
	public void losOrganismosSeCreanSegunCSV() throws  java.io.IOException, com.opencsv.exceptions.CsvValidationException{
		RepositorioOrganismos repoOrganismos = new RepositorioOrganismos();
		RepositorioEntidades repoEntidad = RepositorioEntidades.instancia();
		RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.instancia();
		LectorCSV lector = mock(LectorCSV.class);

		List<String[]> lista = new ArrayList<>();
		String[] array = {"afip","1","messi@hotmail.com","1","2","3"};
		lista.add(array);
		when(lector.getDatosOrganismos()).thenReturn(lista);

		repoEntidad.nuevaEntidad(1,"entidad1");
		repoEntidad.nuevaEntidad(2,"entidad2");
		repoEntidad.nuevaEntidad(3,"entidad3");

		repositorioUsuarios.nuevoUsuario(1,"messi","1234");

		repoOrganismos.cargarOrganismos(lector);
		assertTrue(repoOrganismos.getOrganismos().size() == 1);
		assertTrue(repoOrganismos.getOrganismos().get(0).getNombre().equals("afip"));
		assertTrue(repoOrganismos.getOrganismos().get(0).getUsuario().getNombre().equals("messi"));
	}




}
