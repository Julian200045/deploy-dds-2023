package csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.repositorios.entidades.RepoEntidades;
import domain.repositorios.usuarios.RepoUsuarios;
import services.csv.LectorCSV;
import domain.repositorios.entidades.RepositorioEntidades;
import domain.repositorios.organismos.RepositorioOrganismos;
import domain.repositorios.usuarios.RepositorioUsuarios;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class CSVTest {

	RepoEntidades repoEntidades = new RepositorioEntidades();
	RepoUsuarios repoUsuarios = new RepositorioUsuarios();

	@Test
	public void lectorLeeAlIniciar() throws java.io.IOException, com.opencsv.exceptions.CsvValidationException{
		LectorCSV lector = new LectorCSV("src/main/resources/template/project.properties");
		assertTrue(lector.getDatosOrganismos().size() > 0);
	}


	@Test
	public void getIdsFunciona() throws java.io.FileNotFoundException, java.io.IOException, com.opencsv.exceptions.CsvValidationException{
		LectorCSV lector = new LectorCSV("src/main/resources/template/project.properties");
		RepositorioOrganismos repo = new RepositorioOrganismos(repoEntidades,repoUsuarios);
		String[] datos = {"nombre","usuario","email","1","2", "3"};
		assertTrue(repo.getIds(datos).size() == 3);
	}

	@Test
	public void losOrganismosSeCreanSegunCSV() throws  java.io.IOException, com.opencsv.exceptions.CsvValidationException{

		RepositorioOrganismos repoOrganismos = new RepositorioOrganismos(repoEntidades,repoUsuarios);

		LectorCSV lector = mock(LectorCSV.class);

		List<String[]> lista = new ArrayList<>();
		String[] array = {"afip","1","messi@hotmail.com","1","2","3"};
		lista.add(array);
		when(lector.getDatosOrganismos()).thenReturn(lista);

		repoEntidades.agregarEntidad(1,"entidad1");
		repoEntidades.agregarEntidad(2,"entidad2");
		repoEntidades.agregarEntidad(3,"entidad3");

		repoUsuarios.nuevoUsuario(1,"messi","1234");

		repoOrganismos.cargarOrganismos(lector);
		assertTrue(repoOrganismos.getOrganismos().size() == 1);
		assertTrue(repoOrganismos.getOrganismos().get(0).getNombre().equals("afip"));
		assertTrue(repoOrganismos.getOrganismos().get(0).getUsuario().getNombre().equals("messi"));
	}




}
