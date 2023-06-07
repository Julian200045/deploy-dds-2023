package csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.entidades.Entidad;
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
		assertTrue(repo.getIds(datos).size() == 3);
	}

	@Test
	public void lasEntidadesPrestadorasSeCreanSegunCSV() throws  java.io.IOException, com.opencsv.exceptions.CsvValidationException{

		RepositorioEntidadesPrestadoras repositorioEntidadesPrestadoras = new RepositorioEntidadesPrestadoras(repoEntidades,repoUsuarios);

		LectorCSV lector = mock(LectorCSV.class);

		List<String[]> lista = new ArrayList<>();
		String[] array = {"afip","1","messi@hotmail.com","1","2","3"};
		lista.add(array);
		when(lector.getDatosEntidadesPrestadoras()).thenReturn(lista);

		repoEntidades.agregarEntidad(1,"entidad1");
		repoEntidades.agregarEntidad(2,"entidad2");
		repoEntidades.agregarEntidad(3,"entidad3");

		repoUsuarios.nuevoUsuario(1,"messi","1234");

		repositorioEntidadesPrestadoras.cargarEntidadesPrestadoras(lector);
		assertTrue(repositorioEntidadesPrestadoras.getEntidadesPrestadoras().size() == 1);
		assertTrue(repositorioEntidadesPrestadoras.getEntidadesPrestadoras().get(0).getNombre().equals("afip"));
		assertTrue(repositorioEntidadesPrestadoras.getEntidadesPrestadoras().get(0).getUsuario().getNombre().equals("messi"));
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
		assertTrue(repositorioOrganismoDeControl.getOrganismosDeControl().size() == 1);
		assertTrue(repositorioOrganismoDeControl.getOrganismosDeControl().get(0).getNombre().equals("afip"));
		assertTrue(repositorioOrganismoDeControl.getOrganismosDeControl().get(0).getUsuario().getNombre().equals("messi"));
	}






}
