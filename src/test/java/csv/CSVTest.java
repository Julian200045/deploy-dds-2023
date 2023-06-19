package csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.entidades.Entidad;
import domain.entidades.TipoEntidad;
import domain.repositorios.entidades.RepoEntidades;
import domain.repositorios.entidadesprestadoras.RepoEntidadesPrestadoras;
import domain.repositorios.entidadesprestadoras.RepositorioEntidadesPrestadoras;
import domain.repositorios.organismos.RepositorioOrganismoDeControl;
import domain.repositorios.servicios.RepoServicios;
import domain.repositorios.servicios.RepositorioServicios;
import domain.repositorios.usuarios.RepoUsuarios;
import domain.usuarios.Usuario;
import services.csv.LectorCSV;
import domain.repositorios.entidades.RepositorioEntidades;

import domain.repositorios.usuarios.RepositorioUsuarios;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class CSVTest {

	RepoEntidades repoEntidades = new RepositorioEntidades();
	RepoUsuarios repoUsuarios = new RepositorioUsuarios();
	RepoServicios repoServicios = new RepositorioServicios();
	RepoEntidadesPrestadoras repoEntidadesPrestadoras = new RepositorioEntidadesPrestadoras(repoEntidades, repoUsuarios);

	@Test
	public void lectorLeeAlIniciar() throws java.io.IOException, com.opencsv.exceptions.CsvValidationException{
		LectorCSV lector = new LectorCSV("src/main/resources/template/project.properties");
		assertTrue(lector.getDatosEntidadesPrestadoras().size() > 0);
	}


	@Test
	public void getIdsFunciona() throws java.io.FileNotFoundException, java.io.IOException, com.opencsv.exceptions.CsvValidationException{
		LectorCSV lector = new LectorCSV("src/main/resources/template/project.properties");
		RepositorioEntidadesPrestadoras repo = new RepositorioEntidadesPrestadoras(repoEntidades,repoUsuarios);
		String[] datos = {"nombre","usuario","email","1","2", "3"};
		assertEquals(3, repo.getIds(datos).size());
	}

	@Test
	public void lasEntidadesPrestadorasSeCreanSegunCSV() throws  java.io.IOException, com.opencsv.exceptions.CsvValidationException{

		RepositorioEntidadesPrestadoras repositorioEntidadesPrestadoras = new RepositorioEntidadesPrestadoras(repoEntidades,repoUsuarios);

		LectorCSV lector = mock(LectorCSV.class);

		List<String[]> lista = new ArrayList<>();
		String[] array = {"afip","1","messi@hotmail.com","1","2","3"};
		TipoEntidad tipoBancario = new TipoEntidad("Bancaria","Entidad bancaria",new ArrayList<>());
		lista.add(array);
		when(lector.getDatosEntidadesPrestadoras()).thenReturn(lista);

		repoEntidades.agregarEntidad(1,"entidad1",tipoBancario);
		repoEntidades.agregarEntidad(2,"entidad2",tipoBancario);
		repoEntidades.agregarEntidad(3,"entidad3",tipoBancario);

		repoUsuarios.nuevoUsuario(1,"messi","1234");

		repositorioEntidadesPrestadoras.cargarEntidadesPrestadoras(lector);
		assertEquals(1, repositorioEntidadesPrestadoras.getEntidadesPrestadoras().size());
		assertEquals("afip", repositorioEntidadesPrestadoras.getEntidadesPrestadoras().get(0).getNombre());
		assertEquals("messi", repositorioEntidadesPrestadoras.getEntidadesPrestadoras().get(0).getUsuario().getNombre());
	}

	@Test
	public void losOrganismosSeCreanSegunCSV() throws  java.io.IOException, com.opencsv.exceptions.CsvValidationException{

		RepositorioOrganismoDeControl repositorioOrganismoDeControl = new RepositorioOrganismoDeControl(repoEntidadesPrestadoras,repoUsuarios, repoServicios);

		LectorCSV lector = mock(LectorCSV.class);

		List<String[]> lista = new ArrayList<>();
		String[] array = {"afip","1","messi@hotmail.com","1","1"};
		lista.add(array);
		when(lector.getDatosOrganismosDeControl()).thenReturn(lista);

		repoEntidadesPrestadoras.agregarEntidadPrestadora(1,"nombre",new Usuario(1,"messi", "contraseña"), "messi", new ArrayList<Entidad>());
		repoServicios.agregarServicio(1, "transporte");

		repoUsuarios.nuevoUsuario(1,"messi","1234");

		repositorioOrganismoDeControl.cargarOrganismosDeControl(lector);
		assertEquals(1, repositorioOrganismoDeControl.getOrganismosDeControl().size());
		assertEquals("afip", repositorioOrganismoDeControl.getOrganismosDeControl().get(0).getNombre());
		assertEquals("messi", repositorioOrganismoDeControl.getOrganismosDeControl().get(0).getUsuario().getNombre());
	}






}
